-- Orders Database
CREATE DATABASE Vouchers;
USE Vouchers;

CREATE TABLE vouchers (
	id_voucher INT AUTO_INCREMENT,
	date_voucher DATE NOT NULL,
	subtotal_voucher DECIMAL(10,2) NOT NULL,
	igv_voucher DECIMAL(10,2) NOT NULL,
	total_voucher DECIMAL(10,2) NOT NULL,
	id_user INT NOT NULL,
    document_type_Cli ENUM('DNI', 'RUC', 'CE')NOT NULL,
	/*document_type_Cli VARCHAR(3) NOT NULL,*/ /*DNI, RUC, CE*/
	num_document_Cli VARCHAR(11) NOT NULL,
    voucher_type ENUM('Boleta', 'Factura') NOT NULL,
	/*voucher_type VARCHAR(20) NOT NULL,*/ /* boleta, factura*/
	id_order INT NOT NULL,
	CONSTRAINT PK_voucher PRIMARY KEY (id_voucher)
);

DESCRIBE vouchers;
select * from vouchers;

insert into vouchers values (null, '2024-10-08', 100, 18, 118, 1, 1, '12345678', 1, 1);