package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.Date;
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
    public List<Order> findOrders(String status) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM orders o WHERE o.status = :d", Order.class);
            q.setParameter("s", status);
            List<Order> foundOrders = q.getResultList();
            return foundOrders;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND_STATUS, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrdersCreateDate(Date createDate) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM orders o WHERE o.discount = :d", Order.class);
            q.setParameter("cD", createDate);
            List<Order> foundOrders = q.getResultList();
            return foundOrders;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND_CREATE_DATE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrdersCloseDate(Date closeDate) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM orders o WHERE o.discount = :d", Order.class);
            q.setParameter("cD", closeDate);
            List<Order> foundOrders = q.getResultList();
            return foundOrders;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND_CLOSE_DATE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(BigDecimal finalPrice) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM orders o WHERE o.final_price = :fP", Order.class);
            q.setParameter("fP", finalPrice);
            List<Order> foundOrders = q.getResultList();
            return foundOrders;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND_FINAL_PRICE, new Throwable(e));
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
    public boolean insertOrder(String status, Date createDate, Date closeDate, Integer discount, BigDecimal finalPrice)
            throws InternalException {
        try {
            entityManager.persist(new Order(status, createDate, closeDate, discount, finalPrice));
            return true;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_INSERT, new Throwable(e));
        }
    }
}
