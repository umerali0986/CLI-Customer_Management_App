START TRANSACTION;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
customer_id serial,
customer_name varchar(30),
email varchar(30),
phone varchar(30),
birthdate Date,
CONSTRAINT fk_cutomer PRIMARY KEY (customer_id),
CONSTRAINT UQ_customer UNIQUE  (email)
);

INSERT INTO customer(customer_name, email, phone, birthdate) VALUES
('John Smith', 'johnsmith@gmail.com','7632221899','1990-08-12'),
('Mike Alex', 'mikelAlex@gmail.com','52789076513','1985-01-23');

COMMIT;