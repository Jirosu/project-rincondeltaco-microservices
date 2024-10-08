-- Orders Database
CREATE DATABASE Orders;
USE Orders;

CREATE TABLE orders (
	id_order INT AUTO_INCREMENT,
	date_order DATE NOT NULL,
	id_user INT NOT NULL,
	subtotal_order DECIMAL(10,2) NOT NULL,
	igv_order DECIMAL(10,2) NOT NULL,
	total_order DECIMAL(10,2) NOT NULL,
	phone VARCHAR(9) NOT NULL,
	address VARCHAR(100) NOT NULL,
	district VARCHAR(75) NOT NULL,
	CONSTRAINT PK_order PRIMARY KEY (id_order)
);

CREATE TABLE order_details (
	id_order INT NOT NULL,
	id_product INT NOT NULL,
	quantity INT NOT NULL,
	amount DECIMAL(10,2) NOT NULL,
	CONSTRAINT PK_order_detail PRIMARY KEY (id_order, id_product),
    CONSTRAINT FK_order FOREIGN KEY (id_order) REFERENCES orders (id_order)
);

DESCRIBE orders;
DESCRIBE order_details;
select * from orders;
select * from order_details;

insert into orders values (null, '2024-10-08', 1, 100, 18, 118, '999000111', 'Av. Principal 100', 'Distrito 1');
insert into order_details values (1, 1, 3, 118);