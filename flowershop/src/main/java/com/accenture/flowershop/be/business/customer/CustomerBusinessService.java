package com.accenture.flowershop.be.business.customer;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.customer.Customer;

import java.math.BigDecimal;
import java.util.List;


public interface CustomerBusinessService {
    List<Customer> getAllCustomers() throws InternalException;
    Customer findCustomer(Long id) throws InternalException;
    Customer findCustomer(Account account) throws InternalException;
    List<Customer> findCustomers(String firstName, String middleName, String lastName) throws InternalException;
    List<Customer> findCustomers(String email) throws InternalException;
    List<Customer> findCustomersByPhoneNumber(int phoneNumber) throws InternalException;
    List<Customer> findCustomersByMoney(BigDecimal money) throws InternalException;
    List<Customer> findCustomersByMinMoney(BigDecimal minMoney) throws InternalException;
    List<Customer> findCustomersByMaxMoney(BigDecimal maxMoney) throws InternalException;
    List<Customer> findCustomersByRangeMoney(BigDecimal minMoney, BigDecimal maxMoney) throws InternalException;
    List<Customer> findCustomersByDiscount(int discount) throws InternalException;
    List<Customer> findCustomersByMinDiscount(int minDiscount) throws InternalException;
    List<Customer> findCustomersByMaxDiscount(int maxDiscount) throws InternalException;
    List<Customer> findCustomersByRangeDiscount(int minDiscount, int maxDiscount) throws InternalException;
    boolean insertCustomer(Account account, String firstName, String middleName, String lastName,
                           String email, String phoneNumber, BigDecimal money, int discount)throws InternalException;
    boolean updateCustomer(Customer customer)throws InternalException;
}
