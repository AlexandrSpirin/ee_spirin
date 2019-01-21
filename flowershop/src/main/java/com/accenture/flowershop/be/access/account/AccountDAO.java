package com.accenture.flowershop.be.access.account;


import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.account.AccountType;

import java.util.List;

public interface AccountDAO {
    List<Account> getAllAccounts() throws InternalException;
    Account findAccount(long id) throws InternalException;
    Account findAccount(String login) throws InternalException;
    boolean insertAccount(String login, String password, AccountType type) throws InternalException;
}
