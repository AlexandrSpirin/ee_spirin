CREATE SEQUENCE account_seq;
CREATE SEQUENCE customer_seq;
CREATE SEQUENCE flower_seq;
CREATE SEQUENCE stock_seq;




INSERT INTO accounts (ID, login, password, type) VALUES (account_seq.NEXTVAL, 'admin','admin123','A');
INSERT INTO accounts (ID, login, password, type) VALUES (account_seq.NEXTVAL, 'eaglesss','qwerty','C');
INSERT INTO accounts (ID, login, password, type) VALUES (account_seq.NEXTVAL, 'oops','123456','C');




INSERT INTO customers (ID, account_ID, first_name, middle_name, last_name, email, phone_number, money, discount) VALUES (customer_seq.NEXTVAL, 2, 'Nikita','Aleksandrovich','Orlov', 'agamon@mail.ru', 89201542335, 100000.00, 10);
INSERT INTO customers (ID, account_ID, first_name, middle_name, last_name, email, phone_number, money, discount) VALUES (customer_seq.NEXTVAL, 3, 'Nikita','Igorevich','Popov', 'boooo@rambler.ru', 89209984445, 100.00, 1);




INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Chamomile', 10.00);
INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Jasmine', 50.00);
INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Chrysanthemums', 66.13);
INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Lilac', 23.42);
INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Tulips', 90.00);
INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Lotus', 141.50);
INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Roses', 115.00);




INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.NEXTVAL, 1, 0);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 2, 105);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 3, 266);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 4, 95);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 5, 576);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 6, 395);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 7, 976);

INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.NEXTVAL, 1, 810);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 2, 311);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 3, 1625);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 4, 6234);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 5, 0);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 6, 1132);
INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (stock_seq.CURVAL, 7, 5815);