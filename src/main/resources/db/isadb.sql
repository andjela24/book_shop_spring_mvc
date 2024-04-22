-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 22, 2024 at 02:11 PM
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
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `is_present` bit(1) NOT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`id`, `created_at`, `deleted_at`, `updated_at`, `version`, `is_present`, `lesson_id`, `student_id`) VALUES
(13, '2024-04-19 12:01:52.000000', NULL, NULL, 1, b'1', 1, 2),
(14, '2024-04-19 12:08:18.000000', NULL, NULL, 1, b'1', 1, 1),
(15, '2024-04-19 12:09:56.000000', NULL, NULL, 1, b'1', 4, 10),
(16, '2024-04-19 12:58:00.000000', NULL, NULL, 1, b'1', 8, 6),
(17, '2024-04-22 10:05:31.000000', NULL, NULL, 1, b'1', 5, 13),
(18, '2024-04-22 11:37:48.000000', NULL, '2024-04-22 11:45:58.000000', 2, b'1', 10, 2);

-- --------------------------------------------------------

--
-- Table structure for table `lesson`
--

CREATE TABLE `lesson` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
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
(1, '2024-04-18 14:44:43.423247', NULL, NULL, NULL, '2024-04-20 09:00:00.000000', 1, 1),
(2, '2024-04-18 14:44:43.431924', NULL, NULL, NULL, '2024-04-20 10:30:00.000000', 2, 2),
(3, '2024-04-18 14:44:43.435587', NULL, NULL, NULL, '2024-04-21 09:00:00.000000', 3, 3),
(4, '2024-04-18 14:44:43.440983', NULL, NULL, NULL, '2024-04-21 10:30:00.000000', 4, 4),
(5, '2024-04-18 14:44:43.444205', NULL, NULL, NULL, '2024-04-22 09:00:00.000000', 5, 5),
(6, '2024-04-18 14:44:43.447310', NULL, NULL, NULL, '2024-04-22 09:00:00.000000', 6, 6),
(7, '2024-04-18 14:44:43.451497', NULL, NULL, NULL, '2024-04-23 09:00:00.000000', 7, 7),
(8, '2024-04-18 14:44:43.454659', NULL, NULL, NULL, '2024-04-23 09:00:00.000000', 8, 8),
(9, '2024-04-18 14:44:43.457769', NULL, NULL, NULL, '2024-04-24 09:00:00.000000', 9, 9),
(10, '2024-04-18 14:44:43.462088', NULL, NULL, NULL, '2024-04-24 09:00:00.000000', 10, 10),
(11, '2024-04-22 10:51:25.000000', NULL, NULL, 1, '2024-04-22 10:51:25.000000', 1, 10),
(12, '2024-04-22 10:52:04.000000', NULL, NULL, 1, '2024-04-22 10:52:04.000000', 10, 1),
(13, '2024-04-22 10:53:09.000000', NULL, '2024-04-22 11:58:43.000000', 4, '2024-04-15 11:58:00.000000', 4, 10);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
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
(1, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student1@example.com', 'Marko', 'Petrović'),
(2, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student2@example.com', 'Ana', 'Jovanović'),
(3, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student3@example.com', 'Milica', 'Đorđević'),
(4, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student4@example.com', 'Stefan', 'Nikolić'),
(5, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student5@example.com', 'Jelena', 'Kostić'),
(6, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student6@example.com', 'Nikola', 'Ilić'),
(7, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student7@example.com', 'Sara', 'Stojanović'),
(8, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student8@example.com', 'Filip', 'Milanović'),
(9, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student9@example.com', 'Marija', 'Simić'),
(10, '2024-04-18 10:23:49.965324', NULL, NULL, NULL, 'student10@example.com', 'Luka', 'Pavlović'),
(13, '2024-04-19 14:44:48.000000', NULL, '2024-04-22 10:03:18.000000', 6, 'student1@example.comm', 'Markoo', 'Petrović');

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
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `created_at`, `deleted_at`, `updated_at`, `version`, `name`) VALUES
(1, '2024-04-18 14:42:33.420184', NULL, NULL, NULL, 'Matematika'),
(2, '2024-04-18 14:42:33.426770', NULL, NULL, NULL, 'Fizika'),
(3, '2024-04-18 14:42:33.431783', NULL, NULL, NULL, 'Srpski jezik'),
(4, '2024-04-18 14:42:33.434867', NULL, NULL, NULL, 'Istorija'),
(5, '2024-04-18 14:42:33.437765', NULL, NULL, NULL, 'Biologija'),
(6, '2024-04-18 14:42:33.440668', NULL, NULL, NULL, 'Engleski jezik'),
(7, '2024-04-18 14:42:33.444845', NULL, NULL, NULL, 'Geografija'),
(8, '2024-04-18 14:42:33.447746', NULL, NULL, NULL, 'Hemija'),
(9, '2024-04-18 14:42:33.450683', NULL, NULL, NULL, 'Likovno'),
(10, '2024-04-18 14:42:33.453755', NULL, NULL, NULL, 'Fizičko vaspitanje'),
(11, '2024-04-22 12:10:29.000000', NULL, '2024-04-22 12:12:39.000000', 2, 'Psihologijaa');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
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
(1, '2024-04-18 14:40:21.933475', NULL, NULL, NULL, 'marko@example.com', 'Marko', 'Marković', '+38160123456'),
(2, '2024-04-18 14:40:21.947505', NULL, NULL, NULL, 'ana@example.com', 'Ana', 'Anić', '+38161123456'),
(3, '2024-04-18 14:40:21.952517', NULL, NULL, NULL, 'stefan@example.com', 'Stefan', 'Stefanović', '+38162123456'),
(4, '2024-04-18 14:40:21.956895', NULL, NULL, NULL, 'marija@example.com', 'Marija', 'Marić', '+38163123456'),
(5, '2024-04-18 14:40:21.961194', NULL, NULL, NULL, 'petar@example.com', 'Petar', 'Petrović', '+38164123456'),
(6, '2024-04-18 14:40:21.965520', NULL, NULL, NULL, 'jelena@example.com', 'Jelena', 'Jelenić', '+38165123456'),
(7, '2024-04-18 14:40:21.969828', NULL, NULL, NULL, 'nikola@example.com', 'Nikola', 'Nikolić', '+38166123456'),
(8, '2024-04-18 14:40:21.974040', NULL, NULL, NULL, 'milica@example.com', 'Milica', 'Milić', '+38167123456'),
(9, '2024-04-18 14:40:21.978304', NULL, NULL, NULL, 'dusan@example.com', 'Dušan', 'Dušanić', '+38168123456'),
(10, '2024-04-18 14:40:21.982968', NULL, NULL, NULL, 'tamara@example.com', 'Tamara', 'Tamarović', '+38169123456');

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
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `lesson`
--
ALTER TABLE `lesson`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
