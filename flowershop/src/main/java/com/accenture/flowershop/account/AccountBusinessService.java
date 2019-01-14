package com.accenture.flowershop.account;

public interface AccountBusinessService {
    boolean Login(String login, String password) throws AccountDAOException;
    Account Registration(String login, String password, String firstName, String lastName, String email) throws AccountDAOException;
}
