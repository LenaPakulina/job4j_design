CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers(first_name, last_name, age, country)
VALUES ('Helen', 'Joy', 30, 'USA'),
('Gru', 'Vim', 35, 'USA'),
('Smit', 'Smit', 40, 'Russia'),
('Lena', 'Mil', 26, 'Russia'),
('Kuper', 'Gret', 20, 'China'),
('Ki', 'Ko', 39, 'China');

SELECT * FROM customers
WHERE customers.age = (SELECT MIN(customers.age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders
VALUES (1, 10, 2),
(2, 9, 3),
(3, 11, 4),
(4, 2, 4),
(6, 0, 1),
(7, 0, 6),
(5, 5, 5);

SELECT * FROM customers
WHERE customers.id IN (SELECT orders.customer_id FROM orders WHERE orders.amount = 0);