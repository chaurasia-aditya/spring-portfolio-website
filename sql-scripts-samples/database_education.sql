CREATE DATABASE IF NOT EXISTS `portfolio_db`;
USE `portfolio_db`;

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `university_name` VARCHAR(255) NOT NULL,
  `degree` VARCHAR(255) NOT NULL,
  `program` VARCHAR(255) DEFAULT NULL,
  `courses_taken` TEXT DEFAULT NULL,
  `start_date` DATE DEFAULT NULL,
  `end_date` DATE DEFAULT NULL,
  `image_path` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

--
--

INSERT INTO `education` (`university_name`, `degree`, `program`, `courses_taken`, `start_date`, `end_date`, `image_path`)
VALUES
('University A', 'Bachelor of Science', 'Computer Science', 'Algorithms, Data Structures, Operating Systems', '2015-09-01', '2019-06-01', 'university_a.png'),
('University B', 'Master of Science', 'Software Engineering', 'Software Design, Project Management, Machine Learning', '2019-09-01', '2021-06-01', 'university_b.png'),
('University C', 'Doctor of Philosophy', 'Artificial Intelligence', 'Advanced AI, Neural Networks, Robotics', '2021-09-01', NULL, 'university_c.png');