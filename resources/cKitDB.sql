DROP DATABASE coronakitdb;
CREATE DATABASE coronakitdb;
USE coronakitdb;
CREATE TABLE products (
 pid INT PRIMARY KEY,
 pName varchar(50) NOT NULL,
 pCost DECIMAL NOT NULL,
 pDesc varchar(50) NOT NULL
);

INSERT INTO products (pid,pName,pCost,pDesc) VALUES
(1,"Mask","10","Face Mask"),
(2,"Gloves",20,"Hand Gloves(Set of 2)"),
(3,"Sanitizer","50","Sanitizer 100 ml"),
(4,"PPE","900","Full PPE Kit");

CREATE TABLE orderSummary(
oid INT,
kitId INT PRIMARY KEY AUTO_INCREMENT,
pid INT,
quantity INT,
amount DECIMAL,
personName varchar(50),
email varchar(50),
contactNo varchar(10),
address varchar(100),
orderDate DATE,
orderStatus varchar(50));
