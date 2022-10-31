CREATE TABLE orders (
    id SERIAL NOT NULL PRIMARY KEY,
    paid BOOLEAN,
    value NUMERIC(10, 2)
);