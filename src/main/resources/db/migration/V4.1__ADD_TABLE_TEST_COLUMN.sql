DROP TABLE IF EXISTS `test_column`;

CREATE TABLE `test_column` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(45)DEFAULT NULL,
`address` varchar(1000)DEFAULT NULL,
`desc` mediumtext,
`decimal_column` decimal(5, 2)DEFAULT NULL COMMENT '测试decimal行',
PRIMARY KEY(`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 3 DEFAULT CHARSET = utf8mb4;