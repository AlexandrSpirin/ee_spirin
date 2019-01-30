package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.customer.Customer;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderFlowers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface OrderBusinessService {
    List<Order> getAllOrders() throws InternalException;
    List<OrderFlowers> getAllOrderFlowers() throws InternalException;
    Order findOrder(Long id) throws InternalException;
    List<Order> findOrders(String status) throws InternalException;
    List<Order> findOrders(Customer customer) throws InternalException;
    List<Order> findOrdersCreateDate(Date createDate) throws InternalException;
    List<Order> findOrdersCloseDate(Date closeDate) throws InternalException;
    List<Order> findOrders(BigDecimal finalPrice) throws InternalException;
    List<Order> findOrders(Integer discount) throws InternalException;
    boolean insertOrderFlowers(Order order, Flower flower, Integer flowerCount) throws InternalException;
    Order insertOrder(Customer customer, String status, List<OrderFlowers> orderFlowersList, Date createDate, Date closeDate, Integer discount, BigDecimal finalPrice) throws InternalException;
    boolean updateOrder(Order order) throws InternalException;
}
