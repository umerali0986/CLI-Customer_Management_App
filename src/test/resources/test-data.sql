START TRANSACTION;

DROP TABLE IF EXISTS department, employee, project, project_employee, timesheet CASCADE;


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
('Test 1', 'Test1@gmail','7632221899','1990-08-12'),
('Test 2', 'Test2@gmail','52789076513','1985-01-23');

COMMIT;
