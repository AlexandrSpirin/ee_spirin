package com.accenture.flowershop.be.access;


import com.accenture.flowershop.be.entity.account.Account;

public interface AccountDAO {
    Account findAccount(long ID) throws AccountDAOException;
    Account findAccount(String login) throws AccountDAOException;
    boolean insertAccount(String login, String password, String type) throws AccountDAOException;
}
