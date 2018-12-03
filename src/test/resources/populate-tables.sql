INSERT INTO customers (name) values ('Marcio'); -- ID : 1
INSERT INTO customers (name) values ('Jonas'); -- ID : 2
INSERT INTO customers (name) values ('Laura'); -- ID : 3
INSERT INTO customers (name) values ('Scarlet'); -- ID : 4
INSERT INTO customers (name) values ('Bishop'); -- ID : 5

INSERT INTO phone_numbers (value) values ('123456'); -- ID : 1
INSERT INTO phone_numbers (value) values ('098765'); -- ID : 2
INSERT INTO phone_numbers (value) values ('333333'); -- ID : 3
INSERT INTO phone_numbers (value) values ('555555'); -- ID : 4
INSERT INTO phone_numbers (value) values ('777777'); -- ID : 5
INSERT INTO phone_numbers (value) values ('888888'); -- ID : 6

INSERT INTO customers_phones (customers_id, phone_numbers_id) values (1, 1);
INSERT INTO customers_phones (customers_id, phone_numbers_id) values (1, 4);
INSERT INTO customers_phones (customers_id, phone_numbers_id) values (3, 1);
INSERT INTO customers_phones (customers_id, phone_numbers_id) values (3, 6);

INSERT INTO products (name) values ('Java Book');
INSERT INTO products (name) values ('Baby Pram');
INSERT INTO products (name) values ('Ice Cream');
INSERT INTO products (name) values ('Pop Corn');
INSERT INTO products (name) values ('Mazda 3');

INSERT INTO orders (created_at, customer_id) values (NOW(), 2);
INSERT INTO items (order_id, product_id, quantity) values (1, 1, 5);
INSERT INTO items (order_id, product_id, quantity) values (1, 2, 3);
INSERT INTO items (order_id, product_id, quantity) values (1, 5, 2);
