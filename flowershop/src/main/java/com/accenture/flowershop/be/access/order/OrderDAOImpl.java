package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.customer.Customer;
import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


@Component
public class OrderDAOImpl implements OrderDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getAllOrders() throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o", Order.class);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDERS_GET_ALL, new Throwable(e));
        }
    }


    @Override
    public Order findOrder(Long id) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o WHERE o.id = :id ", Order.class);
            q.setParameter("id", id);
            List<Order> foundOrder = q.getResultList();
            if(!foundOrder.isEmpty())
            {
                return foundOrder.get(0);
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(String status) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o WHERE o.status = :d", Order.class);
            q.setParameter("s", status);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDERS_FIND_STATUS, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(Customer customer) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o WHERE o.customer.id = :cId", Order.class);
            q.setParameter("cId", customer.getId());
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDERS_FIND_STATUS, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrdersCreateDate(Date createDate) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o WHERE o.createDate = :createD", Order.class);
            q.setParameter("createD", createDate);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDERS_FIND_CREATE_DATE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrdersCloseDate(Date closeDate) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o WHERE o.closeDate = :closeD", Order.class);
            q.setParameter("closeD", closeDate);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDERS_FIND_CLOSE_DATE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(BigDecimal finalPrice) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o WHERE o.final_price = :fP", Order.class);
            q.setParameter("fP", finalPrice);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDERS_FIND_FINAL_PRICE, new Throwable(e));
        }
    }

    @Override
    public List<Order> findOrders(Integer discount) throws InternalException {
        try {
            TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o WHERE o.discount = :d", Order.class);
            q.setParameter("d", discount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDERS_FIND_DISCOUNT, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public boolean insertOrder(Customer customer, String status, Date createDate, Date closeDate, Integer discount, BigDecimal finalPrice)
            throws InternalException {
        try {
            entityManager.persist(new Order(customer, status, createDate, closeDate, discount, finalPrice));
            return true;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ORDER_INSERT, new Throwable(e));
        }
    }
}
