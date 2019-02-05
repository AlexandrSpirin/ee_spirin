package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.customer.Customer;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderFlowers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders() throws InternalException;
    List<OrderFlowers> getAllOrderFlowers() throws InternalException;
    Order findOrder(Long id) throws InternalException;
    List<Order> findOrders(String status) throws InternalException;
    List<Order> findOrders(Customer customer) throws InternalException;
    List<Order> findOrdersCreateDate(Date createDate) throws InternalException;
    List<Order> findOrdersCloseDate(Date closeDate) throws InternalException;
    List<Order> findOrders(BigDecimal finalPrice) throws InternalException;
    List<Order> findOrders(Integer discount) throws InternalException;
    boolean insertOrder(Order order) throws InternalException;
    boolean updateOrder(Order order) throws InternalException;
}
