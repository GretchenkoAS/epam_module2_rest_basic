DROP TABLE IF EXISTS `gift_certificates`;
CREATE TABLE `gift_certificates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(80) DEFAULT NULL,
  `price` decimal NOT NULL,
  `duration` int(4) NOT NULL,
  `create_date` datetime NOT NULL,
  `last_update_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `tags_gift_certificates`;
CREATE TABLE `tags_gift_certificates` (
  `tag_id` bigint(20) NOT NULL,
  `gift_certificate_id` bigint(20) NOT NULL,
  PRIMARY KEY (`tag_id`, `gift_certificate_id`),
  FOREIGN KEY (`tag_id`)  REFERENCES `tags` (`id`),
  FOREIGN KEY (`gift_certificate_id`)  REFERENCES `gift_certificates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;