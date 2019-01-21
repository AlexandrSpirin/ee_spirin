package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getAllOrders() throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM orders o", Order.class);
            List<Order> foundOrders = q.getResultList();
            return foundOrders;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public Order findOrder(long id) throws InternalException {
        try {
            return entityManager.find(Order.class, id);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(long flowerId) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM orders o WHERE o.flowerId = :f", Order.class);
            q.setParameter("f", flowerId);
            List<Order> foundOrders = q.getResultList();
            return foundOrders;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND_FLOWER_ID, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(Integer discount) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM orders o WHERE o.discount = :d", Order.class);
            q.setParameter("d", discount);
            List<Order> foundOrders = q.getResultList();
            return foundOrders;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND_DISCOUNT, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public boolean insertOrder(HashMap<BigInteger, Integer> flowers, Integer discount)
            throws InternalException {
        try {
            entityManager.persist(new Order(flowers, discount));
            return true;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_INSERT, new Throwable(e));
        }
    }
}
