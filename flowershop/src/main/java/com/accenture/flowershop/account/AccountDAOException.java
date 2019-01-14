package com.accenture.flowershop.account;

public class AccountDAOException extends Exception{
    private static final long serialVersionUID = -2397012533045245997L;
    public static final String ERROR_FIND_ID = "Exception occcured while finding com.accenture.flowershop.account via ID";
    public static final String ERROR_FIND_NAME = "Exception occcured while finding com.accenture.flowershop.account via FirstName, LastName";
    public static final String ERROR_FIND_LOGIN = "Exception occcured while finding com.accenture.flowershop.account via Login";
    public static final String ERROR_INSERT_ACCOUNT = "Exception occured while inserting new com.accenture.flowershop.account";

    public AccountDAOException(String message, Throwable cause){
        super(message, cause);
    }
}
