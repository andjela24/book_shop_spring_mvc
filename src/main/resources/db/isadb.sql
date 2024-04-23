-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 23, 2024 at 05:27 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `isadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `is_present` tinyint(1) DEFAULT 1,
  `lesson_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`id`, `created_at`, `deleted_at`, `updated_at`, `version`, `is_present`, `lesson_id`, `student_id`) VALUES
(1, '2024-04-23 10:56:26', NULL, '2024-04-23 12:56:26.000000', 2, 1, 1, 1),
(3, '2024-04-23 11:27:11', NULL, NULL, 1, 1, 2, 2),
(4, '2024-04-23 15:26:59', NULL, NULL, 1, 0, 3, 3),
(5, '2024-04-23 15:27:08', NULL, NULL, 1, 1, 5, 7),
(6, '2024-04-23 15:27:17', NULL, NULL, 1, 0, 4, 8),
(7, '2024-04-23 15:27:28', NULL, NULL, 1, 1, 3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `lesson`
--

CREATE TABLE `lesson` (
  `id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lesson`
--

INSERT INTO `lesson` (`id`, `created_at`, `deleted_at`, `updated_at`, `version`, `date`, `subject_id`, `teacher_id`) VALUES
(1, '2024-04-23 10:52:58', NULL, NULL, 1, '2024-04-15 12:52:00.000000', 10, 1),
(2, '2024-04-23 11:26:54', NULL, NULL, 1, '2024-04-09 13:26:00.000000', 8, 2),
(3, '2024-04-23 15:26:08', NULL, NULL, 1, '2024-04-11 14:25:00.000000', 8, 6),
(4, '2024-04-23 15:26:19', NULL, NULL, 1, '2024-04-20 17:26:00.000000', 7, 5),
(5, '2024-04-23 15:26:29', NULL, NULL, 1, '2024-04-15 17:26:00.000000', 2, 4),
(6, '2024-04-23 15:26:40', NULL, NULL, 1, '2024-04-17 17:26:00.000000', 5, 3);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `created_at`, `deleted_at`, `updated_at`, `version`, `email`, `first_name`, `last_name`) VALUES
(1, '2024-04-23 10:51:52', NULL, NULL, 2, 'student1@example.comm', 'Ana', 'Anic'),
(2, '2024-04-23 11:26:23', NULL, NULL, 2, 'student2@example.com', 'Nikola', 'Ilic'),
(3, '2024-04-23 11:29:34', NULL, NULL, 2, 'student3@example.com', 'Stefan', 'Nikolic'),
(4, '2024-04-23 11:30:24', NULL, NULL, 2, 'student4@example.com', 'Filip', 'Milanovic'),
(5, '2024-04-23 11:30:57', NULL, NULL, 2, 'student5@example.comm', 'Jelena', 'Kostic'),
(7, '2024-04-23 11:32:10', NULL, '2024-04-23 13:32:10.000000', 4, 'student6@example.com', 'Sara', 'Stojanovic'),
(8, '2024-04-23 15:18:57', NULL, NULL, 2, 'student7@example.comm', 'Marko', 'Petrovic');

-- --------------------------------------------------------

--
-- Table structure for table `student_subject`
--

CREATE TABLE `student_subject` (
  `student_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_subject`
--

INSERT INTO `student_subject` (`student_id`, `subject_id`) VALUES
(1, 2),
(1, 7),
(2, 8),
(2, 9),
(3, 4),
(3, 6),
(4, 3),
(4, 9),
(5, 2),
(5, 5),
(7, 1),
(7, 5),
(7, 9),
(8, 5),
(8, 6);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `created_at`, `deleted_at`, `updated_at`, `version`, `name`) VALUES
(1, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Matematika'),
(2, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Srpski jezik'),
(3, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Fizičko vaspitanje'),
(4, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Istorija'),
(5, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Biologija'),
(6, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Geografija'),
(7, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Engleski jezik'),
(8, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Likovno'),
(9, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Muzičko'),
(10, '2024-04-23 10:51:28', NULL, NULL, NULL, 'Informatika');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `created_at`, `deleted_at`, `updated_at`, `version`, `email`, `first_name`, `last_name`, `phone_number`) VALUES
(1, '2024-04-23 10:52:26', NULL, NULL, 2, 'marko@exapmle.com', 'Marko', 'Markovic', '+38161123456'),
(2, '2024-04-23 10:58:39', NULL, '2024-04-23 12:58:39.000000', 4, 'petar@example.com', 'Petar', 'Petrovic', '+38161123456'),
(3, '2024-04-23 15:24:01', NULL, NULL, 2, 'jovan@example.com', 'Jovan', 'Jovanovic', '+38161123456'),
(4, '2024-04-23 15:24:43', NULL, NULL, 2, 'milica@example.com', 'Milica', 'Milic', '+38161123456'),
(5, '2024-04-23 15:25:06', NULL, NULL, 2, 'stefan@example.com', 'Stefan', 'Stefanovic', '+38161123456'),
(6, '2024-04-23 15:25:46', NULL, NULL, 2, 'ivan@example.com', 'Ivan', 'Ivanovic', '+38161123456');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_subject`
--

CREATE TABLE `teacher_subject` (
  `teacher_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teacher_subject`
--

INSERT INTO `teacher_subject` (`teacher_id`, `subject_id`) VALUES
(1, 7),
(1, 10),
(2, 2),
(3, 6),
(3, 7),
(3, 9),
(4, 1),
(4, 3),
(4, 10),
(5, 6),
(5, 7),
(6, 5),
(6, 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKam01ddvne08oa3exny156v7al` (`lesson_id`),
  ADD KEY `FKnq6vm31it076obtjf2qp5coim` (`student_id`);

--
-- Indexes for table `lesson`
--
ALTER TABLE `lesson`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7ydr23s8y9j6lip5qrngoymx4` (`subject_id`),
  ADD KEY `FK9yhaoqrjxt5gwmn6icp1lf35n` (`teacher_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_fe0i52si7ybu0wjedj6motiim` (`email`);

--
-- Indexes for table `student_subject`
--
ALTER TABLE `student_subject`
  ADD KEY `FK5cvx0kd792xhvd99s3bsbygfq` (`subject_id`),
  ADD KEY `FKnhw926s5os3ei5wqfaq94j0mh` (`student_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_3kv6k1e64a9gylvkn3gnghc2q` (`email`);

--
-- Indexes for table `teacher_subject`
--
ALTER TABLE `teacher_subject`
  ADD KEY `FKdnhs9kxdlnrvhq5k111c87pna` (`subject_id`),
  ADD KEY `FK625xnjha5rs0qqynxsthk646k` (`teacher_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `lesson`
--
ALTER TABLE `lesson`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `FKam01ddvne08oa3exny156v7al` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`),
  ADD CONSTRAINT `FKnq6vm31it076obtjf2qp5coim` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `lesson`
--
ALTER TABLE `lesson`
  ADD CONSTRAINT `FK7ydr23s8y9j6lip5qrngoymx4` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FK9yhaoqrjxt5gwmn6icp1lf35n` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`);

--
-- Constraints for table `student_subject`
--
ALTER TABLE `student_subject`
  ADD CONSTRAINT `FK5cvx0kd792xhvd99s3bsbygfq` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKnhw926s5os3ei5wqfaq94j0mh` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `teacher_subject`
--
ALTER TABLE `teacher_subject`
  ADD CONSTRAINT `FK625xnjha5rs0qqynxsthk646k` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  ADD CONSTRAINT `FKdnhs9kxdlnrvhq5k111c87pna` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
