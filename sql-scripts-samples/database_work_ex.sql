CREATE DATABASE  IF NOT EXISTS `portfolio_db`;
USE `portfolio_db`;

DROP TABLE IF EXISTS `work_experience`;

CREATE TABLE `work_experience` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(101) NOT NULL,
  `job_title` varchar(101) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
--

INSERT INTO `work_experience` (`company_name`, `job_title`, `start_date`, `end_date`, `description`) VALUES
('Company A', 'Software Engineer', '2020-01-01', '2021-01-01', 'Worked on developing and maintaining web applications.'),
('Company B', 'Senior Developer', '2021-02-01', '2022-02-01', 'Led a team of developers in building scalable software solutions.'),
('Company C', 'Tech Lead', '2022-03-01', NULL, 'Overseeing technical projects and ensuring code quality and efficiency.');
