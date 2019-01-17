CREATE SEQUENCE account_seq;
CREATE SEQUENCE customer_seq;
CREATE SEQUENCE flower_seq;
CREATE SEQUENCE flower_pool_seq;




INSERT INTO accounts (ID, login, password, type) VALUES (account_seq.NEXTVAL, 'admin','admin123','admin');


INSERT INTO accounts (ID, login, password, type) VALUES (account_seq.NEXTVAL, 'eaglesss','qwerty','customer');

INSERT INTO customers (ID, account_ID, first_name, middle_name, last_name, email, phone_number, money, discount) VALUES (customer_seq.NEXTVAL, account_seq.CURRVAL, 'Nikita','Aleksandrovich','Orlov', 'agamon@mail.ru', 89201542335, 100000.00, 10);


INSERT INTO accounts (ID, login, password, type) VALUES (account_seq.NEXTVAL, 'oops','123456','customer');

INSERT INTO customers (ID, account_ID, first_name, middle_name, last_name, email, phone_number, money, discount) VALUES (customer_seq.NEXTVAL, account_seq.CURRVAL, 'Nikita','Igorevich','Popov', 'boooo@rambler.ru', 89209984445, 100.00, 1);




INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Chamomile', 10.00);

INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (flower_pool_seq.NEXTVAL, flower_seq.CURRVAL, 810);


INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Jasmine', 50.00);

INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (flower_pool_seq.NEXTVAL, 2, 311);


INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Chrysanthemums', 66.13);

INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (flower_pool_seq.NEXTVAL, 3, 1625);


INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Lilac', 23.42);

INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (flower_pool_seq.NEXTVAL, 4, 6234);


INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Tulips', 90.00);

INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (flower_pool_seq.NEXTVAL, 5, 0);


INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Lotus', 141.50);

INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (flower_pool_seq.NEXTVAL, 6, 1132);


INSERT INTO flowers (ID, name, cost) VALUES (flower_seq.NEXTVAL, 'Roses', 115.00);

INSERT INTO flower_pools (ID, flower_ID, flower_count) VALUES (flower_pool_seq.NEXTVAL, 7, 5815);












