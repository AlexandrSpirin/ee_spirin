CREATE TABLE "accounts"
(
    "ID" NUMBER(10,0) PRIMARY KEY,
    "login" VARCHAR2(45 CHAR) NOT NULL,
    "password" VARCHAR2(45 CHAR) NOT NULL,
    "type" VARCHAR2(1 CHAR) NOT NULL
);




CREATE TABLE "customers"
(
    "ID" NUMBER(10,0) PRIMARY KEY,
    "account_ID" NUMBER(10,0) NOT NULL,
    "first_name" VARCHAR2(45 CHAR) NOT NULL,
    "middle_name" VARCHAR2(45 CHAR) NOT NULL,
    "last_name" VARCHAR2(45 CHAR) NOT NULL,
    "email" VARCHAR2(45 CHAR) NOT NULL,
    "phone_number" NUMBER(11, 0) NOT NULL,
    "money" NUMBER(9, 2) NOT NULL,
    "discount" NUMBER(2) NOT NULL
);




CREATE TABLE "flowers"
(
    "ID" NUMBER(10,0) PRIMARY KEY,
    "name" VARCHAR2(45 CHAR) NOT NULL,
    "cost" NUMBER(8, 2) NOT NULL,
);




CREATE TABLE "flower_pools"
(
    "ID" NUMBER(10,0) PRIMARY KEY,
    "flower_ID" NUMBER(10,0) NOT NULL,
    "flower_count" NUMBER(8, 0) NOT NULL,
);