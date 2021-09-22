CREATE TABLE `transaction_propagation_outside`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(64) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `transaction_propagation_inside`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(64) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4;