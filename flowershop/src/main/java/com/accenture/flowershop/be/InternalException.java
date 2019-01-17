package com.accenture.flowershop.be;

public class InternalException extends Exception{
    private static final long serialVersionUID = -2397012533045245997L;
    public static final String ERROR_DAO_FIND_ID = "Exception occcured while finding Account via ID";
    public static final String ERROR_DAO_FIND_LOGIN = "Exception occcured while finding Account via Login";
    public static final String ERROR_DAO_INSERT_ACCOUNT = "Exception occured while inserting new Account";
    public static final String ERROR_SERVICE_LOGIN = "Exception occured while login service";
    public static final String ERROR_SERVICE_REGISTER = "Exception occured while registration service";

    public InternalException(String message, Throwable cause){
        super(message, cause);
    }
}
