package com.accenture.flowershop.be.access.customer;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.customer.Customer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;


@Component
public class CustomerDAOImpl implements CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Customer> getAllCustomers() throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public Customer findCustomer(Long id) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.id = :id ", Customer.class);
            q.setParameter("id", id);
            List<Customer> foundCustomer = q.getResultList();
            if(!foundCustomer.isEmpty())
            {
                return foundCustomer.get(0);
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMER_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public Customer findCustomer(Account account) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.account.id = :accId", Customer.class);
            q.setParameter("accId", account.getId());
            List<Customer> foundCustomer = q.getResultList();
            if(!foundCustomer.isEmpty())
            {
                return foundCustomer.get(0);
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMER_FIND_ACCOUNT, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomers(String firstName, String middleName, String lastName) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE UPPER (c.firstName) LIKE :n" +
                    "AND UPPER (c.middleName) LIKE :mN AND UPPER (c.lastName) LIKE :lN", Customer.class);
            q.setParameter("fN", "%" + firstName.toUpperCase() + "%");
            q.setParameter("mN", "%" + firstName.toUpperCase() + "%");
            q.setParameter("lN", "%" + firstName.toUpperCase() + "%");
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_NAME, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomers(String email) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE UPPER (c.email) LIKE :e", Customer.class);
            q.setParameter("e", email);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_EMAIL, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByPhoneNumber(int phoneNumber) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber LIKE :phN", Customer.class);
            q.setParameter("phN", phoneNumber);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_PHONE_NUMBER, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMoney(BigDecimal money) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.money = :m", Customer.class);
            q.setParameter("m", money);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_MONEY, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMinMoney(BigDecimal minMoney) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.money >= :minM", Customer.class);
            q.setParameter("minM", minMoney);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_MIN_MONEY, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMaxMoney(BigDecimal maxMoney) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.money <= :maxM", Customer.class);
            q.setParameter("maxM", maxMoney);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_MAX_MONEY, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByRangeMoney(BigDecimal minMoney, BigDecimal maxMoney) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.money >= :minM" +
                    "AND c.money <= :maxM", Customer.class);
            q.setParameter("minM", minMoney);
            q.setParameter("maxM", maxMoney);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_RANGE_MONEY, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByDiscount(int discount) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.discount = :d", Customer.class);
            q.setParameter("d", discount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_DISCOUNT, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMinDiscount(int minDiscount) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.discount >= :minD", Customer.class);
            q.setParameter("minD", minDiscount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_MIN_DISCOUNT, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMaxDiscount(int maxDiscount) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.discount <= :maxD", Customer.class);
            q.setParameter("maxD", maxDiscount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_MAX_DISCOUNT, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByRangeDiscount(int minDiscount, int maxDiscount) throws InternalException{
        try {
            TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.discount >= :minD" +
                    "AND c.discount <= :maxD", Customer.class);
            q.setParameter("minD", minDiscount);
            q.setParameter("maxD", maxDiscount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMERS_FIND_RANGE_DISCOUNT, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public boolean insertCustomer(Account account, String firstName, String middleName, String lastName,
                        String email, String phoneNumber, BigDecimal money, int discount)throws InternalException{
        try {
            if(findCustomer(account)==null) {
                entityManager.persist(new Customer(account, firstName, middleName, lastName, email, phoneNumber, money, discount));
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMER_INSERT, new Throwable(e));
        }
    }

    @Override
    @Transactional
    public boolean updateCustomer(Customer customer)throws InternalException{
        try {
            entityManager.merge(customer);
            return true;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_CUSTOMER_UPDATE, new Throwable(e));
        }
    }
}
