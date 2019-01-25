CREATE TABLE "accounts"
(
    "id" LONG PRIMARY KEY,
    "login" VARCHAR2(45 CHAR) NOT NULL,
    "password" VARCHAR2(45 CHAR) NOT NULL,
    "type" VARCHAR2(45 CHAR) NOT NULL
);




CREATE TABLE "customers"
(
    "id" LONG PRIMARY KEY,
    "account_id" LONG,
    FOREIGN KEY ("account_id") REFERENCES "accounts" ("id"),
    "first_name" VARCHAR2(45 CHAR) NOT NULL,
    "middle_name" VARCHAR2(45 CHAR),
    "last_name" VARCHAR2(45 CHAR) NOT NULL,
    "email" VARCHAR2(45 CHAR),
    "phone_number" VARCHAR2(20 CHAR),
    "money" NUMBER(10, 2) NOT NULL,
    "discount" NUMBER(2) NOT NULL
);




CREATE TABLE "flowers"
(
    "id" LONG PRIMARY KEY,
    "name" VARCHAR2(45 CHAR) NOT NULL,
    "cost" NUMBER(10, 2) NOT NULL
);




CREATE TABLE "flower_stocks"
(
    "id" LONG PRIMARY KEY,
    "flower_id" LONG,
    FOREIGN KEY ("flower_id") REFERENCES "flowers" ("id"),
    "flower_count" NUMBER(8, 0) NOT NULL,
    "flower_stock_id" LONG NOT NULL
);



CREATE TABLE "orders"
(
    "id" LONG PRIMARY KEY,
    "account_id" LONG NOT NULL,
    "status" VARCHAR2(45 CHAR) NOT NULL,
    "create_date" DATE,
    "close_date" DATE,
    "discount" NUMBER(2) NOT NULL,
    "final_price" NUMBER(10, 2) NOT NULL
);




CREATE TABLE "orders_flowers"
(
    "order_id" LONG NOT NULL,
    "flower_id" LONG NOT NULL,
    "flower_count" NUMBER(8, 0) NOT NULL,
    CONSTRAINT "pk_orders_flowers" PRIMARY KEY ("order_id", "flower_id")
);
