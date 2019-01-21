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
    "account_id" LONG NOT NULL,
    "first_name" VARCHAR2(45 CHAR) NOT NULL,
    "middle_name" VARCHAR2(45 CHAR) NOT NULL,
    "last_name" VARCHAR2(45 CHAR) NOT NULL,
    "email" VARCHAR2(45 CHAR) NOT NULL,
    "phone_number" NUMBER(11, 0) NOT NULL,
    "money" NUMBER(10, 2) NOT NULL,
    "discount" NUMBER(2) NOT NULL
);




CREATE TABLE "flowers"
(
    "id" LONG PRIMARY KEY,
    "name" VARCHAR2(45 CHAR) NOT NULL,
    "cost" NUMBER(10, 2) NOT NULL
);




CREATE TABLE "flower_pools"
(
    "id" LONG NOT NULL,
    "flower_id" LONG NOT NULL,
    "flower_count" NUMBER(8, 0) NOT NULL,
    CONSTRAINT "pk_flower_pools" PRIMARY KEY ("id", "flower_id")
);



CREATE TABLE "orders"
(
    "id" LONG PRIMARY KEY,
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
