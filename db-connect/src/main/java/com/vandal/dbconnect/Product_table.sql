CREATE TABLE products (
    productId SERIAL PRIMARY KEY,
    partNumber INT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    isForSale BOOLEAN,
    price DOUBLE
);