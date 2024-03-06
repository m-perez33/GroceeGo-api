--Insert into grocery list
INSERT INTO grocery_list (created_date) VALUES
	('2023-12-22');

--Insert into product
INSERT INTO product (name) VALUES
	('Product 1'),
	('Product 2'),
	('Product 3'),
	('Product 4');

--Insert into list_entry
INSERT INTO list_entry (quantity, cost, grocery_list_id, product_id, category) VALUES
	('3', '2.5', (Select grocery_list_id from grocery_list), (Select product_id from product where name = 'Product 1'), '1'),
	('2', '3', (Select grocery_list_id from grocery_list), (Select product_id from product where name = 'Product 2'), '2'),
	('10', '30', (Select grocery_list_id from grocery_list), (Select product_id from product where name = 'Product 3'), '2'),
	('1', '4', (Select grocery_list_id from grocery_list), (Select product_id from product where name = 'Product 4'), '1');
