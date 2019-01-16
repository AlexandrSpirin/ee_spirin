CREATE TABLE "accounts"
(
    "ID" LONG PRIMARY KEY,
    "login" VARCHAR2(45 CHAR) NOT NULL,
    "password" VARCHAR2(45 CHAR) NOT NULL,
    "type" VARCHAR2(45 CHAR) NOT NULL
);




CREATE TABLE "customers"
(
    "ID" LONG PRIMARY KEY,
    "account_ID" LONG NOT NULL,
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
    "ID" LONG PRIMARY KEY,
    "name" VARCHAR2(45 CHAR) NOT NULL,
    "cost" NUMBER(10, 2) NOT NULL,
);




CREATE TABLE "flower_pools"
(
    "ID" LONG PRIMARY KEY,
    "flower_ID" LONG NOT NULL,
    "flower_count" NUMBER(8, 0) NOT NULL,
);
