CREATE TABLE IF NOT EXISTS customers
(
    customer_id serial
        constraint customers primary key,
    name        varchar(50)
);

INSERT INTO customers(customer_id, name) VALUES (1, 'Bogun Ivan'),(2, 'Avdeeva Lika'), (3, 'Rezin Ivan'), (4, 'Shukts Leo'), (5, 'Yulyashev Sergio');