package com.accenture.flowershop.account;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAccount(String firstName, String lastName) throws AccountDAOException;
    public Account findAccount(int id) throws AccountDAOException;
    public Account findAccount(String login) throws AccountDAOException;
    public boolean insertAccount(String login, String password, String firstName, String lastName, String email) throws AccountDAOException;
}
