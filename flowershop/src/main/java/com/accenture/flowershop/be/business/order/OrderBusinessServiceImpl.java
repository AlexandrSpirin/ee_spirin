package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.access.customer.CustomerDAO;
import com.accenture.flowershop.be.access.flowerStock.FlowerStockDAO;
import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.entity.customer.Customer;
import com.accenture.flowershop.be.entity.flowerStock.FlowerStock;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderFlowers;
import com.accenture.flowershop.be.entity.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    @Qualifier("orderDAOImpl")
    private OrderDAO orderDAO;

    @Autowired
    @Qualifier("customerDAOImpl")
    private CustomerDAO customerDAO;

    @Autowired
    @Qualifier("flowerStockDAOImpl")
    private FlowerStockDAO flowerStockDAO;

    @Override
    public List<Order> getAllOrders() throws InternalException {
        try {
            return orderDAO.getAllOrders();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDERS_GET_ALL, new Throwable(e));
        }
    }


    @Override
    public List<OrderFlowers> getAllOrderFlowers() throws InternalException {
        try {
            return orderDAO.getAllOrderFlowers();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDERS_GET_ALL, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public Order findOrder(Long id) throws InternalException {
        try {
            Order order = orderDAO.findOrder(id);
            order.getOrderFlowersList().size();
            return order;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(String status) throws InternalException {
        try {
            return orderDAO.findOrders(status);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDERS_FIND_STATUS, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public List<Order> findOrders(Customer customer) throws InternalException {
        try {
            List<Order> orders;
            if(customer == null) {
                orders = orderDAO.getAllOrders();
                orders.size();
                for (Order o: orders) {
                    o.getOrderFlowersList().size();
                }
                return orders;
            } else {
                orders = orderDAO.findOrders(customer);
                orders.size();
                for (Order o: orders) {
                    o.getOrderFlowersList().size();
                }
                return orders;
            }
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDERS_FIND_CUSTOMER, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrdersCreateDate(Date createDate) throws InternalException {
        try {
            return orderDAO.findOrdersCreateDate(createDate);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDERS_FIND_CREATE_DATE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrdersCloseDate(Date closeDate) throws InternalException {
        try {
            return orderDAO.findOrdersCloseDate(closeDate);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDERS_FIND_CLOSE_DATE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(BigDecimal finalPrice) throws InternalException {
        try {
            return orderDAO.findOrders(finalPrice);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDERS_FIND_FINAL_PRICE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(Integer discount) throws InternalException {
        try {
            return orderDAO.findOrders(discount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDERS_FIND_DISCOUNT, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public boolean createOrder(Order order)
            throws InternalException {
        try {
            Customer customer = customerDAO.findCustomer(order.getCustomer().getId());
            if(order.getFinalPrice().compareTo(customer.getMoney()) <= 0) {
                customer.setMoney(customer.getMoney().subtract(order.getFinalPrice()));
                customerDAO.updateCustomer(customer);
                for (OrderFlowers orderFlowers: order.getOrderFlowersList()) {
                    FlowerStock flowerStock = flowerStockDAO.findFlowerStocksByFlower(orderFlowers.getFlower()).get(0);
                    flowerStock.setFlowerCount(flowerStock.getFlowerCount() - orderFlowers.getFlowerCount());
                    flowerStockDAO.updateFlowerStock(flowerStock);
                }
                return orderDAO.insertOrder(order);
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_CREATE, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public boolean closeOrder(Order order)
            throws InternalException {
        try {
            order.setStatus(OrderStatus.CLOSE);
            order.setCloseDate(new Date(System.currentTimeMillis()));

            return orderDAO.updateOrder(order);

        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_CLOSE, new Throwable(e));
        }
    }
}
