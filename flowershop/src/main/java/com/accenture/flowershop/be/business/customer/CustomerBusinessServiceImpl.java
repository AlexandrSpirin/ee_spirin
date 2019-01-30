package com.accenture.flowershop.be.business.customer;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.access.customer.CustomerDAO;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class CustomerBusinessServiceImpl implements CustomerBusinessService {

    @Autowired
    @Qualifier("customerDAOImpl")
    private CustomerDAO customerDAO;


    @Override
    public List<Customer> getAllCustomers() throws InternalException{
        try {
            return customerDAO.getAllCustomers();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_GET_ALL, new Throwable(e));
        }
    }


    @Override
    public Customer findCustomer(Long id) throws InternalException{
        try {
            return customerDAO.findCustomer(id);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMER_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public Customer findCustomer(Account account) throws InternalException{
        try {
            return customerDAO.findCustomer(account);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMER_FIND_ACCOUNT, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomers(String firstName, String middleName, String lastName) throws InternalException{
        try {
            return customerDAO.findCustomers(firstName, middleName, lastName);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_NAME, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomers(String email) throws InternalException{
        try {
            return customerDAO.findCustomers(email);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_EMAIL, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByPhoneNumber(int phoneNumber) throws InternalException{
        try {
            return customerDAO.findCustomersByPhoneNumber(phoneNumber);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_PHONE_NUMBER, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMoney(BigDecimal money) throws InternalException{
        try {
            return customerDAO.findCustomersByMoney(money);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_MONEY, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMinMoney(BigDecimal minMoney) throws InternalException{
        try {
            return customerDAO.findCustomersByMinMoney(minMoney);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_MIN_MONEY, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMaxMoney(BigDecimal maxMoney) throws InternalException{
        try {
            return customerDAO.findCustomersByMaxMoney(maxMoney);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_MAX_MONEY, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByRangeMoney(BigDecimal minMoney, BigDecimal maxMoney) throws InternalException{
        try {
            return customerDAO.findCustomersByRangeMoney(minMoney, maxMoney);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_RANGE_MONEY, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByDiscount(int discount) throws InternalException{
        try {
            return customerDAO.findCustomersByDiscount(discount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_DISCOUNT, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMinDiscount(int minDiscount) throws InternalException{
        try {
            return customerDAO.findCustomersByMinDiscount(minDiscount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_MIN_DISCOUNT, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByMaxDiscount(int maxDiscount) throws InternalException{
        try {
            return customerDAO.findCustomersByMaxDiscount(maxDiscount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_MAX_DISCOUNT, new Throwable(e));
        }
    }

    @Override
    public List<Customer> findCustomersByRangeDiscount(int minDiscount, int maxDiscount) throws InternalException{
        try {
            return customerDAO.findCustomersByRangeDiscount(minDiscount, maxDiscount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMERS_FIND_RANGE_DISCOUNT, new Throwable(e));
        }
    }


    @Override
    public boolean insertCustomer(Account account, String firstName, String middleName, String lastName,
                           String email, String phoneNumber, BigDecimal money, int discount)throws InternalException{
        try {
            return customerDAO.insertCustomer(account, firstName, middleName, lastName, email, phoneNumber, money, discount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMER_INSERT, new Throwable(e));
        }
    }

    @Override
    public boolean updateCustomer(Customer customer)throws InternalException{
        try {
            return customerDAO.updateCustomer(customer);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_CUSTOMER_UPDATE, new Throwable(e));
        }
    }
}
