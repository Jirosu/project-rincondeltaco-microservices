-- Products Database
CREATE DATABASE Products;
USE Products;

CREATE TABLE products_category (
	id_category INT AUTO_INCREMENT,
	description_category VARCHAR(100) NOT NULL,
	CONSTRAINT PK_products_category PRIMARY KEY (id_category)
);

CREATE TABLE products (
	id_product INT AUTO_INCREMENT,
	name_product VARCHAR(100) NOT NULL,
	description_product VARCHAR(250) NOT NULL,
	price_product DECIMAL(10,2) NOT NULL,
	img_path VARCHAR(250) NOT NULL,
	id_category INT NOT NULL,
	is_enabled TINYINT(1) DEFAULT 1 NOT NULL,
	CONSTRAINT PK_product PRIMARY KEY (id_product),
    CONSTRAINT FK_products_category FOREIGN KEY (id_category) REFERENCES products_category (id_category)
);

DESCRIBE products;
DESCRIBE products_category;
select * from products;
select * from products_category;

insert into products_category values (null, 'category 1');
insert into products values (null, 'prod 1', 'description prod 1', 10.90, 'img/path', 1, 1);
insert into products values (null, 'prod 2', 'description prod 2', 10.90, 'img/path', 1, 0);