CREATE SEQUENCE account_seq;
CREATE SEQUENCE customer_seq;
CREATE SEQUENCE flower_seq;
CREATE SEQUENCE flower_stock_seq;
CREATE SEQUENCE order_seq;




INSERT INTO accounts (id, login, password, type) VALUES (account_seq.NEXTVAL, 'admin','admin123','ADMIN');


INSERT INTO accounts (id, login, password, type) VALUES (account_seq.NEXTVAL, 'eaglesss','qwerty','CUSTOMER');

INSERT INTO customers (id, account_id, first_name, middle_name, last_name, email, phone_number, money, discount) VALUES (customer_seq.NEXTVAL, account_seq.CURRVAL, 'Nikita','Aleksandrovich','Orlov', 'agamon@mail.ru', '89201542335', 100000.00, 10);


INSERT INTO accounts (id, login, password, type) VALUES (account_seq.NEXTVAL, 'oops','123456','CUSTOMER');

INSERT INTO customers (id, account_id, first_name, middle_name, last_name, email, phone_number, money, discount) VALUES (customer_seq.NEXTVAL, account_seq.CURRVAL, 'Nikita','Igorevich','Popov', 'boooo@rambler.ru', '89209984445', 100.00, 1);




INSERT INTO flowers (id, name, cost) VALUES (flower_seq.NEXTVAL, 'Chamomile', 10.00);

INSERT INTO flower_stocks (id, flower_id, flower_count, flower_stock_id) VALUES (flower_stock_seq.NEXTVAL, flower_seq.CURRVAL, 810, 1);


INSERT INTO flowers (id, name, cost) VALUES (flower_seq.NEXTVAL, 'Jasmine', 50.00);

INSERT INTO flower_stocks (id, flower_id, flower_count, flower_stock_id) VALUES (flower_stock_seq.NEXTVAL, flower_seq.CURRVAL, 311, 1);


INSERT INTO flowers (id, name, cost) VALUES (flower_seq.NEXTVAL, 'Chrysanthemums', 66.13);

INSERT INTO flower_stocks (id, flower_id, flower_count, flower_stock_id) VALUES (flower_stock_seq.NEXTVAL, flower_seq.CURRVAL, 1625, 1);


INSERT INTO flowers (id, name, cost) VALUES (flower_seq.NEXTVAL, 'Lilac', 23.42);

INSERT INTO flower_stocks (id, flower_id, flower_count, flower_stock_id) VALUES (flower_stock_seq.NEXTVAL, flower_seq.CURRVAL, 6234, 1);


INSERT INTO flowers (id, name, cost) VALUES (flower_seq.NEXTVAL, 'Tulips', 90.00);

INSERT INTO flower_stocks (id, flower_id, flower_count, flower_stock_id) VALUES (flower_stock_seq.NEXTVAL, flower_seq.CURRVAL, 0, 1);


INSERT INTO flowers (id, name, cost) VALUES (flower_seq.NEXTVAL, 'Lotus', 141.50);

INSERT INTO flower_stocks (id, flower_id, flower_count, flower_stock_id) VALUES (flower_stock_seq.NEXTVAL, flower_seq.CURRVAL, 1132, 1);


INSERT INTO flowers (id, name, cost) VALUES (flower_seq.NEXTVAL, 'Roses', 115.00);

INSERT INTO flower_stocks (id, flower_id, flower_count, flower_stock_id) VALUES (flower_stock_seq.NEXTVAL, flower_seq.CURRVAL, 5815, 1);
