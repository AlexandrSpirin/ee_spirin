package com.accenture.flowershop.be.business.account;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.account.AccountType;


public interface AccountBusinessService {
    boolean login(String login, String password) throws InternalException;
    boolean registration(String login, String password, AccountType type) throws InternalException;
    boolean isAdmin(String login) throws InternalException;
}
