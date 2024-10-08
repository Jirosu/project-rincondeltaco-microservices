-- Users Database
CREATE DATABASE Users;
USE Users;

CREATE TABLE users (
	id_user INT AUTO_INCREMENT,
	name_user VARCHAR(100) NOT NULL,
	last_name_user VARCHAR(100) NOT NULL,
	email_user VARCHAR(100) NOT NULL,
	password_user VARCHAR(100) NOT NULL,
	rol_user VARCHAR(45) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (id_user)
);

DESCRIBE users;
select * from users;

insert into users values (null, 'Jimmy', 'N', 'jn@gmail.com', '123456', 'admin');