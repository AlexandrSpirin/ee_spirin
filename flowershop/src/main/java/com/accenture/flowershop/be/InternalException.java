package com.accenture.flowershop.be;

public class InternalException extends Exception{
    private static final long serialVersionUID = -2397012533045245997L;

    public static final String ERROR_DAO_ACCOUNT_GET_ALL = "(DAO)Exception occcured while get all Accounts";
    public static final String ERROR_DAO_ACCOUNT_FIND_ID = "(DAO)Exception occcured while finding Account via ID";
    public static final String ERROR_DAO_ACCOUNT_FIND_LOGIN = "(DAO)Exception occcured while finding Account via Login";
    public static final String ERROR_DAO_ACCOUNT_INSERT = "(DAO)Exception occured while inserting new Account";

    public static final String ERROR_DAO_FLOWER_GET_ALL = "(DAO)Exception occcured while get all Flowers";
    public static final String ERROR_DAO_FLOWER_FIND_ID = "(DAO)Exception occcured while finding Flower via ID";
    public static final String ERROR_DAO_FLOWER_FIND_NAME = "(DAO)Exception occcured while finding Flower via Name";
    public static final String ERROR_DAO_FLOWERS_FIND_NAME = "(DAO)Exception occcured while finding Flowers via Name(Like)";
    public static final String ERROR_DAO_FLOWERS_FIND_COST = "(DAO)Exception occcured while finding Flowers via Cost";
    public static final String ERROR_DAO_FLOWERS_FIND_MIN_COST = "(DAO)Exception occcured while finding Flowers via Min Cost";
    public static final String ERROR_DAO_FLOWERS_FIND_MAX_COST = "(DAO)Exception occcured while finding Flowers via Max Cost";
    public static final String ERROR_DAO_FLOWERS_FIND_RANGE_COST = "(DAO)Exception occcured while finding Flowers via Range Cost";
    public static final String ERROR_DAO_FLOWER_INSERT = "(DAO)Exception occured while inserting new Flower";

    public static final String ERROR_DAO_ORDER_GET_ALL = "(DAO)Exception occcured while get all Orders";
    public static final String ERROR_DAO_ORDER_FIND_ID = "(DAO)Exception occcured while finding Order via ID";
    public static final String ERROR_DAO_ORDER_FIND_STATUS = "(DAO)Exception occcured while finding Order via Status";
    public static final String ERROR_DAO_ORDER_FIND_CREATE_DATE = "(DAO)Exception occcured while finding Order via Create Date";
    public static final String ERROR_DAO_ORDER_FIND_CLOSE_DATE = "(DAO)Exception occcured while finding Order via Close Date";
    public static final String ERROR_DAO_ORDER_FIND_FINAL_PRICE = "(DAO)Exception occcured while finding Order via Final Price";
    public static final String ERROR_DAO_ORDER_FIND_DISCOUNT = "(DAO)Exception occcured while finding Order via Discount";
    public static final String ERROR_DAO_ORDER_INSERT = "(DAO)Exception occured while inserting new Order";


    public static final String ERROR_SERVICE_ACCOUNT_LOGIN = "(Service)Exception occured while login Account service";
    public static final String ERROR_SERVICE_ACCOUNT_REGISTER = "(Service)Exception occured while registration Account service";

    public static final String ERROR_SERVICE_FLOWER_GET_ALL = "(Service)Exception occcured while get all Flowers";
    public static final String ERROR_SERVICE_FLOWER_FIND_ID = "(Service)Exception occcured while finding Flower via ID";
    public static final String ERROR_SERVICE_FLOWER_FIND_NAME = "(Service)Exception occcured while finding Flower via Name";
    public static final String ERROR_SERVICE_FLOWERS_FIND_NAME = "(Service)Exception occcured while finding Flowers via Name(Like)";
    public static final String ERROR_SERVICE_FLOWERS_FIND_COST = "(Service)Exception occcured while finding Flowers via Cost";
    public static final String ERROR_SERVICE_FLOWERS_FIND_MIN_COST = "(Service)Exception occcured while finding Flowers via Min Cost";
    public static final String ERROR_SERVICE_FLOWERS_FIND_MAX_COST = "(Service)Exception occcured while finding Flowers via Max Cost";
    public static final String ERROR_SERVICE_FLOWERS_FIND_RANGE_COST = "(Service)Exception occcured while finding Flowers via Range Cost";
    public static final String ERROR_SERVICE_FLOWER_INSERT = "(Service)Exception occured while inserting new Flower";

    public static final String ERROR_SERVICE_ORDER_GET_ALL = "(Service)Exception occcured while get all Orders";
    public static final String ERROR_SERVICE_ORDER_FIND_ID = "(Service)Exception occcured while finding Order via ID";
    public static final String ERROR_SERVICE_ORDER_FIND_STATUS = "(Service)Exception occcured while finding Order via Status";
    public static final String ERROR_SERVICE_ORDER_FIND_CREATE_DATE = "(Service)Exception occcured while finding Order via Create Date";
    public static final String ERROR_SERVICE_ORDER_FIND_CLOSE_DATE = "(Service)Exception occcured while finding Order via Close Date";
    public static final String ERROR_SERVICE_ORDER_FIND_FINAL_PRICE = "(Service)Exception occcured while finding Order via Final Price";
    public static final String ERROR_SERVICE_ORDER_FIND_DISCOUNT = "(Service)Exception occcured while finding Order via Discount";
    public static final String ERROR_SERVICE_ORDER_INSERT = "(Service)Exception occured while inserting new Order";




    public InternalException(String message, Throwable cause){
        super(message, cause);
    }
}
