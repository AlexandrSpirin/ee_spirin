package com.accenture.flowershop.be;

public class InternalException extends Exception{
    private static final long serialVersionUID = -2397012533045245997L;

    public static final String ERROR_DAO_ACCOUNTS_GET_ALL = "(DAO)Exception occcured while get all Accounts";
    public static final String ERROR_DAO_ACCOUNT_FIND_ID = "(DAO)Exception occcured while finding Account via ID";
    public static final String ERROR_DAO_ACCOUNT_FIND_LOGIN = "(DAO)Exception occcured while finding Account via Login";
    public static final String ERROR_DAO_ACCOUNT_INSERT = "(DAO)Exception occured while inserting new Account";

    public static final String ERROR_DAO_CUSTOMERS_GET_ALL = "(DAO)Exception occcured while get all Customers";
    public static final String ERROR_DAO_CUSTOMER_FIND_ID = "(DAO)Exception occcured while finding Customer via ID";
    public static final String ERROR_DAO_CUSTOMER_FIND_ACCOUNT = "(DAO)Exception occcured while finding Customer via Account";
    public static final String ERROR_DAO_CUSTOMERS_FIND_NAME = "(DAO)Exception occcured while finding Customer via First, Middle, Last name";
    public static final String ERROR_DAO_CUSTOMERS_FIND_EMAIL = "(DAO)Exception occcured while finding Customer via Email";
    public static final String ERROR_DAO_CUSTOMERS_FIND_PHONE_NUMBER = "(DAO)Exception occcured while finding Customer via Phone Number";
    public static final String ERROR_DAO_CUSTOMERS_FIND_MONEY = "(DAO)Exception occcured while finding Customer via Money";
    public static final String ERROR_DAO_CUSTOMERS_FIND_MIN_MONEY = "(DAO)Exception occcured while finding Customer via Min Money";
    public static final String ERROR_DAO_CUSTOMERS_FIND_MAX_MONEY = "(DAO)Exception occcured while finding Customer via Max Money";
    public static final String ERROR_DAO_CUSTOMERS_FIND_RANGE_MONEY = "(DAO)Exception occcured while finding Customer via Range Money";
    public static final String ERROR_DAO_CUSTOMERS_FIND_DISCOUNT = "(DAO)Exception occcured while finding Customer via Discount";
    public static final String ERROR_DAO_CUSTOMERS_FIND_MIN_DISCOUNT = "(DAO)Exception occcured while finding Customer via Min Discount";
    public static final String ERROR_DAO_CUSTOMERS_FIND_MAX_DISCOUNT = "(DAO)Exception occcured while finding Customer via Max Discount";
    public static final String ERROR_DAO_CUSTOMERS_FIND_RANGE_DISCOUNT = "(DAO)Exception occcured while finding Customer via Range Discount";
    public static final String ERROR_DAO_CUSTOMER_INSERT = "(DAO)Exception occured while inserting new Customer";

    public static final String ERROR_DAO_FLOWERS_GET_ALL = "(DAO)Exception occcured while get all Flowers";
    public static final String ERROR_DAO_FLOWER_FIND_ID = "(DAO)Exception occcured while finding Flower via ID";
    public static final String ERROR_DAO_FLOWER_FIND_NAME = "(DAO)Exception occcured while finding Flower via Name";
    public static final String ERROR_DAO_FLOWERS_FIND_NAME = "(DAO)Exception occcured while finding Flowers via Name(Like)";
    public static final String ERROR_DAO_FLOWERS_FIND_NAME_AND_RANGE_COST = "(DAO)Exception occcured while finding Flowers via Name(Like) and Range Cost";
    public static final String ERROR_DAO_FLOWERS_FIND_COST = "(DAO)Exception occcured while finding Flowers via Cost";
    public static final String ERROR_DAO_FLOWERS_FIND_MIN_COST = "(DAO)Exception occcured while finding Flowers via Min Cost";
    public static final String ERROR_DAO_FLOWERS_FIND_MAX_COST = "(DAO)Exception occcured while finding Flowers via Max Cost";
    public static final String ERROR_DAO_FLOWERS_FIND_RANGE_COST = "(DAO)Exception occcured while finding Flowers via Range Cost";
    public static final String ERROR_DAO_FLOWER_INSERT = "(DAO)Exception occured while inserting new Flower";

    public static final String ERROR_DAO_FLOWER_STOCKS_GET_ALL = "(DAO)Exception occcured while get all Flower Stocks";
    public static final String ERROR_DAO_FLOWER_STOCK_FIND_ID = "(DAO)Exception occcured while finding Flower Stock via ID";
    public static final String ERROR_DAO_FLOWER_STOCKS_FIND_FLOWER = "(DAO)Exception occcured while finding Flower Stocks via Flower";
    public static final String ERROR_DAO_FLOWER_STOCKS_FIND_FLOWER_COUNT = "(DAO)Exception occcured while finding Flower Stocks via Flower Count";
    public static final String ERROR_DAO_FLOWER_STOCKS_FIND_MIN_FLOWER_COUNT = "(DAO)Exception occcured while finding Flower Stocks via Min Flower Count";
    public static final String ERROR_DAO_FLOWER_STOCKS_FIND_MAX_FLOWER_COUNT = "(DAO)Exception occcured while finding Flower Stocks via Max Flower Count";
    public static final String ERROR_DAO_FLOWER_STOCKS_FIND_RANGE_FLOWER_COUNT = "(DAO)Exception occcured while finding Flower Stocks via Range Flower Count";
    public static final String ERROR_DAO_FLOWER_STOCK_INSERT = "(DAO)Exception occured while inserting new Flower Stock";

    public static final String ERROR_DAO_ORDERS_GET_ALL = "(DAO)Exception occcured while get all Orders";
    public static final String ERROR_DAO_ORDER_FIND_ID = "(DAO)Exception occcured while finding Order via ID";
    public static final String ERROR_DAO_ORDERS_FIND_STATUS = "(DAO)Exception occcured while finding Order via Status";
    public static final String ERROR_DAO_ORDERS_FIND_CREATE_DATE = "(DAO)Exception occcured while finding Order via Create Date";
    public static final String ERROR_DAO_ORDERS_FIND_CLOSE_DATE = "(DAO)Exception occcured while finding Order via Close Date";
    public static final String ERROR_DAO_ORDERS_FIND_FINAL_PRICE = "(DAO)Exception occcured while finding Order via Final Price";
    public static final String ERROR_DAO_ORDERS_FIND_DISCOUNT = "(DAO)Exception occcured while finding Order via Discount";
    public static final String ERROR_DAO_ORDER_INSERT = "(DAO)Exception occured while inserting new Order";




    public static final String ERROR_SERVICE_ACCOUNTS_GET_ALL = "(Service)Exception occured while get all Accounts";
    public static final String ERROR_SERVICE_ACCOUNT_FIND_ID = "(Service)Exception occured while finding Account via Id";
    public static final String ERROR_SERVICE_ACCOUNT_FIND_LOGIN = "(Service)Exception occured while finding Account via Login";
    public static final String ERROR_SERVICE_ACCOUNT_LOGIN = "(Service)Exception occured while login Account service";
    public static final String ERROR_SERVICE_ACCOUNT_REGISTER = "(Service)Exception occured while registration Account";
    public static final String ERROR_SERVICE_ACCOUNT_IS_ADMIN = "(Service)Exception occured while check Account type";

    public static final String ERROR_SERVICE_CUSTOMERS_GET_ALL = "(Service)Exception occcured while get all Customers";
    public static final String ERROR_SERVICE_CUSTOMER_FIND_ID = "(Service)Exception occcured while finding Customer via ID";
    public static final String ERROR_SERVICE_CUSTOMER_FIND_ACCOUNT = "(Service)Exception occcured while finding Customer via Account";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_NAME = "(Service)Exception occcured while finding Customer via First, Middle, Last name";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_EMAIL = "(Service)Exception occcured while finding Customer via Email";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_PHONE_NUMBER = "(Service)Exception occcured while finding Customer via Phone Number";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_MONEY = "(Service)Exception occcured while finding Customer via Money";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_MIN_MONEY = "(Service)Exception occcured while finding Customer via Min Money";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_MAX_MONEY = "(Service)Exception occcured while finding Customer via Max Money";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_RANGE_MONEY = "(Service)Exception occcured while finding Customer via Range Money";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_DISCOUNT = "(Service)Exception occcured while finding Customer via Discount";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_MIN_DISCOUNT = "(Service)Exception occcured while finding Customer via Min Discount";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_MAX_DISCOUNT = "(Service)Exception occcured while finding Customer via Max Discount";
    public static final String ERROR_SERVICE_CUSTOMERS_FIND_RANGE_DISCOUNT = "(Service)Exception occcured while finding Customer via Range Discount";
    public static final String ERROR_SERVICE_CUSTOMER_INSERT = "(Service)Exception occured while inserting new Customer";

    public static final String ERROR_SERVICE_FLOWERS_GET_ALL = "(Service)Exception occcured while get all Flowers";
    public static final String ERROR_SERVICE_FLOWER_FIND_ID = "(Service)Exception occcured while finding Flower via ID";
    public static final String ERROR_SERVICE_FLOWER_FIND_NAME = "(Service)Exception occcured while finding Flower via Name";
    public static final String ERROR_SERVICE_FLOWERS_FIND_NAME = "(Service)Exception occcured while finding Flowers via Name(Like)";
    public static final String ERROR_SERVICE_FLOWERS_FIND_NAME_AND_RANGE_COST = "(Service)Exception occcured while finding Flowers via Name(Like) and Range Cost";
    public static final String ERROR_SERVICE_FLOWERS_FIND_COST = "(Service)Exception occcured while finding Flowers via Cost";
    public static final String ERROR_SERVICE_FLOWERS_FIND_MIN_COST = "(Service)Exception occcured while finding Flowers via Min Cost";
    public static final String ERROR_SERVICE_FLOWERS_FIND_MAX_COST = "(Service)Exception occcured while finding Flowers via Max Cost";
    public static final String ERROR_SERVICE_FLOWERS_FIND_RANGE_COST = "(Service)Exception occcured while finding Flowers via Range Cost";
    public static final String ERROR_SERVICE_FLOWER_INSERT = "(Service)Exception occured while inserting new Flower";

    public static final String ERROR_SERVICE_FLOWER_STOCKS_GET_ALL = "(Service)Exception occcured while get all Flower Stocks";
    public static final String ERROR_SERVICE_FLOWER_STOCK_FIND_ID = "(Service)Exception occcured while finding Flower Stock via ID";
    public static final String ERROR_SERVICE_FLOWER_STOCKS_FIND_FLOWER = "(Service)Exception occcured while finding Flower Stocks via Flower";
    public static final String ERROR_SERVICE_FLOWER_STOCKS_FIND_FLOWER_COUNT = "(Service)Exception occcured while finding Flower Stocks via Flower Count";
    public static final String ERROR_SERVICE_FLOWER_STOCKS_FIND_MIN_FLOWER_COUNT = "(Service)Exception occcured while finding Flower Stocks via Min Flower Count";
    public static final String ERROR_SERVICE_FLOWER_STOCKS_FIND_MAX_FLOWER_COUNT = "(Service)Exception occcured while finding Flower Stocks via Max Flower Count";
    public static final String ERROR_SERVICE_FLOWER_STOCKS_FIND_RANGE_FLOWER_COUNT = "(Service)Exception occcured while finding Flower Stocks via Range Flower Count";
    public static final String ERROR_SERVICE_FLOWER_STOCK_INSERT = "(Service)Exception occured while inserting new Flower Stock";

    public static final String ERROR_SERVICE_ORDERS_GET_ALL = "(Service)Exception occcured while get all Orders";
    public static final String ERROR_SERVICE_ORDER_FIND_ID = "(Service)Exception occcured while finding Order via ID";
    public static final String ERROR_SERVICE_ORDERS_FIND_STATUS = "(Service)Exception occcured while finding Order via Status";
    public static final String ERROR_SERVICE_ORDERS_FIND_CREATE_DATE = "(Service)Exception occcured while finding Order via Create Date";
    public static final String ERROR_SERVICE_ORDERS_FIND_CLOSE_DATE = "(Service)Exception occcured while finding Order via Close Date";
    public static final String ERROR_SERVICE_ORDERS_FIND_FINAL_PRICE = "(Service)Exception occcured while finding Order via Final Price";
    public static final String ERROR_SERVICE_ORDERS_FIND_DISCOUNT = "(Service)Exception occcured while finding Order via Discount";
    public static final String ERROR_SERVICE_ORDER_INSERT = "(Service)Exception occured while inserting new Order";






    public InternalException(String message, Throwable cause){
        super(message, cause);
    }
}
