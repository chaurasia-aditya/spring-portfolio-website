CREATE DATABASE  IF NOT EXISTS `portfolio_db`;
USE `portfolio_db`;

DROP TABLE IF EXISTS `skills`;

CREATE TABLE `skills` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(101) NOT NULL,
  `skill` varchar(101) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `skills` (category, skill) VALUES 
('Languages', 'C'),
('Frameworks/Libraries', 'Spring'),
('DevOps/Tools', 'Postman'),
('Domains', 'Backend Development');
