CREATE DATABASE IF NOT EXISTS `portfolio_db`;
USE `portfolio_db`;

DROP TABLE IF EXISTS `research`;

CREATE TABLE `research` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `title` varchar(255) NOT NULL,
  `authors` varchar(255) DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  `reference_number` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `published_date` date DEFAULT NULL,
  `description` text,
  `link` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `research` (type, title, authors, venue, reference_number, status, published_date, description, link) VALUES
('Patent',
 'Example Patent Title',
 'Example Inventor One, Example Inventor Two',
 'Example Assignee',
 'US X,XXX,XXX',
 'Published',
 '2023-03-15',
 'Brief summary of the patented invention.',
 'https://example.com/patent');