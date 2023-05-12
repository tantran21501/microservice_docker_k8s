DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS accounts;
CREATE TABLE `customer` (
                          `customer_id` bigint AUTO_INCREMENT PRIMARY KEY ,
                          `name` varchar(100) ,
                          `email` varchar(100)  ,
                          `mobile_number` varchar(20)  ,
                          `create_date`  Varchar(25) DEFAULT NULL
);
CREATE TABLE `accounts` (
                          `customer_id` int NOT NULL,
                          `account_number` bigint AUTO_INCREMENT PRIMARY KEY ,
                          `account_type` varchar(100) NOT NULL ,
                          `branch_address` varchar(100) NOT NULL ,
                          `create_date` Varchar(25) DEFAULT NULL
);

INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_date`)
VALUES (1, 123456, 'Checking', '123 Main St', '2022-05-01');
INSERT INTO `customer` (`customer_id`,`name`, `email`, `mobile_number`, `create_date`)
VALUES (1,'John Doe', 'john.doe@example.com', '555-1234', '2022-05-01');


