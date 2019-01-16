package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.AccountDAOException;

public interface AccountBusinessService {
    boolean Login(String login, String password) throws AccountDAOException;
    boolean Registration(String login, String password, String type) throws AccountDAOException;
}
