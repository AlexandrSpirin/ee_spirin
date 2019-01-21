package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders() throws InternalException;
    Order findOrder(long id) throws InternalException;
    List<Order> findOrders(long flowerId) throws InternalException;
    List<Order> findOrders(Integer discount) throws InternalException;
    boolean insertOrder(long[] flowerId, Integer[] flowerCount, Integer discount) throws InternalException;
}
