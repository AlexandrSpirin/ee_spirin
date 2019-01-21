package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    @Qualifier("flowerDAOImpl")
    private OrderDAO orderDAO;

    @Override
    public List<Order> getAllOrders() throws InternalException {
        try {
            return orderDAO.getAllOrders();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_GET_ALL, new Throwable(e));
        }
    }


    @Override
    public Order findOrder(long id) throws InternalException {
        try {
            return orderDAO.findOrder(id);
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
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_FIND_STATUS, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrdersCreateDate(Date createDate) throws InternalException {
        try {
            return orderDAO.findOrdersCreateDate(createDate);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_FIND_CREATE_DATE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrdersCloseDate(Date closeDate) throws InternalException {
        try {
            return orderDAO.findOrdersCloseDate(closeDate);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_FIND_CLOSE_DATE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(BigDecimal finalPrice) throws InternalException {
        try {
            return orderDAO.findOrders(finalPrice);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_FIND_FINAL_PRICE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(Integer discount) throws InternalException {
        try {
            return orderDAO.findOrders(discount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_FIND_DISCOUNT, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public boolean insertOrder(String status, Date createDate, Date closeDate, Integer discount, BigDecimal finalPrice)
            throws InternalException {
        try {
            return orderDAO.insertOrder(status, createDate, closeDate, discount, finalPrice);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ORDER_INSERT, new Throwable(e));
        }
    }
}