CREATE TABLE IF NOT EXISTS customers
(
    customer_id serial
        constraint customers primary key,
    name        varchar(50)
);