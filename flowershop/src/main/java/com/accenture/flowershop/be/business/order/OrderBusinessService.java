package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.customer.Customer;
import com.accenture.flowershop.be.entity.order.Order;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface OrderBusinessService {
    List<Order> getAllOrders() throws InternalException;
    Order findOrder(Long id) throws InternalException;
    List<Order> findOrders(String status) throws InternalException;
    List<Order> findOrders(Customer customer) throws InternalException;
    List<Order> findOrdersCreateDate(Date createDate) throws InternalException;
    List<Order> findOrdersCloseDate(Date closeDate) throws InternalException;
    List<Order> findOrders(BigDecimal finalPrice) throws InternalException;
    List<Order> findOrders(Integer discount) throws InternalException;
    boolean insertOrder(Customer customer, String status, Date createDate, Date closeDate, Integer discount, BigDecimal finalPrice) throws InternalException;
}
