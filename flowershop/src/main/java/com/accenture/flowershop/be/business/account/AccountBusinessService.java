package com.accenture.flowershop.be.business.account;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.account.AccountType;

import java.util.List;


public interface AccountBusinessService {
    List<Account> getAllAccounts() throws InternalException;
    Account findAccount(Long id) throws InternalException;
    Account findAccount(String login) throws InternalException;
    boolean login(String login, String password) throws InternalException;
    boolean registration(String login, String password, AccountType type) throws InternalException;
    boolean isAdmin(String login) throws InternalException;
}
