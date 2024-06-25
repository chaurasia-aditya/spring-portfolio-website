CREATE DATABASE IF NOT EXISTS `portfolio_db`;
USE `portfolio_db`;

DROP TABLE IF EXISTS `projects`;

CREATE TABLE `projects` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `start_date` DATE DEFAULT NULL,
  `end_date` DATE DEFAULT NULL,
  `project_link` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO `projects` (`name`, `description`, `start_date`, `end_date`, `project_link`)
VALUES 
('Project Alpha', 'Description of Project Alpha', '2021-01-01', '2021-06-30', 'https://example.com/alpha'),
('Project Beta', 'Description of Project Beta', NULL, NULL, 'https://example.com/beta');
