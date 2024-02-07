--**************************************************************************
-- Step 1: Drop all database objects to start with an empty database
--**************************************************************************
DROP TABLE IF EXISTS list_entry;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS grocery_list;
DROP TABLE IF EXISTS users;



--CREATE TABLE users (
--	user_id SERIAL,
--	username varchar(50) NOT NULL UNIQUE,
--	password_hash varchar(200) NOT NULL,
--	role varchar(50) NOT NULL,
--	name varchar(50) NOT NULL,
--	address varchar(100) NULL,
--	city varchar(50) NULL,
--	state_code char(2) NULL,
--	zip varchar(5) NULL,
--	CONSTRAINT PK_user PRIMARY KEY (user_id)
--);

--**************************************************************************
-- Step 1: Create the grocery list table
--**************************************************************************/
-- Table grocery list
CREATE TABLE grocery_list(
	grocery_list_id serial NOT NULL,
	user_id int NULL,--make not null later
    created_date DATE NOT NULL DEFAULT CURRENT_DATE,
	CONSTRAINT PK_grocery_list PRIMARY KEY (grocery_list_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)

);




/**************************************************************************
-- Step 3: Create the product table
**************************************************************************/
-- Table product
CREATE TABLE product(
	product_id serial NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT PK_product PRIMARY KEY (product_id)
);


--**************************************************************************
-- Step 4: Create the list entry table
--**************************************************************************
-- Table list entry
CREATE TABLE list_entry(
	list_entry_id serial NOT NULL,
	quantity numeric(7,2) NOT NULL,
	cost numeric(7,2) NOT NULL,
	grocery_list_id int NOT NULL,
	product_id int NOT NULL,
	category int NOT NULL,
	CONSTRAINT PK_list_entry PRIMARY KEY (list_entry_id),
	CONSTRAINT FK_grocery_list FOREIGN KEY(grocery_list_id) REFERENCES grocery_list (grocery_list_id),
	CONSTRAINT FK_product FOREIGN KEY(product_id) REFERENCES product (product_id)
);




--**************************************************************************
-- Step 1a: Insert test data into the grocery list table
--**************************************************************************
INSERT INTO grocery_list (created_date) VALUES
	('2023-12-22');


--**************************************************************************
-- Step 2a: Insert test data into the product table
--**************************************************************************
INSERT INTO product (name) VALUES
	('Banana'),
	('Cereal'),
	('Pork loin'),
	('Toilet paper');


--**************************************************************************
-- Step 3a: Insert test data into the list entry table
--**************************************************************************
INSERT INTO list_entry (quantity, cost, grocery_list_id, product_id, category) VALUES
	('3', '2.5', (Select grocery_list_id from grocery_list), (Select product_id from product where name = 'Banana'), '1'),
	('2', '3', (Select grocery_list_id from grocery_list), (Select product_id from product where name = 'Cereal'), '2'),
	('10', '30', (Select grocery_list_id from grocery_list), (Select product_id from product where name = 'Toilet paper'), '2'),
	('1', '4', (Select grocery_list_id from grocery_list), (Select product_id from product where name = 'Pork loin'), '1');




