-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 05, 2025 at 05:26 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `geisternetz_projekt`
--

-- --------------------------------------------------------

--
-- Table structure for table `geisternetz`
--

CREATE TABLE `geisternetz` (
  `ID` int(11) NOT NULL,
  `breitengrad` double NOT NULL,
  `laengengrad` double NOT NULL,
  `groesse` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `erfassungsdatum` date DEFAULT NULL,
  `MeldendePersonID` int(11) DEFAULT NULL,
  `bergungsdatum` date DEFAULT NULL,
  `geborgendatum` date DEFAULT NULL,
  `verschollendatum` date DEFAULT NULL,
  `BergendePersonID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `geisternetz`
--

INSERT INTO `geisternetz` (`ID`, `breitengrad`, `laengengrad`, `groesse`, `status`, `erfassungsdatum`, `MeldendePersonID`, `bergungsdatum`, `geborgendatum`, `verschollendatum`, `BergendePersonID`) VALUES
(1, 46.235197, 8.015445, 100, 'Verschollen', '2008-07-01', 52, '2008-07-04', NULL, '2025-07-31', 52),
(2, 45.235197, 7.015445, 100, 'verschollen', '2009-07-01', 52, '2009-08-14', NULL, '2009-08-14', NULL),
(3, 44.235197, 6.015445, 100, 'geborgen', '2010-07-01', 52, '2010-07-04', '2010-07-29', NULL, NULL),
(1002, 46.121212, 8.121212, 1212, 'gemeldet', '2025-07-10', 452, NULL, NULL, NULL, NULL),
(1052, 34.999999, 90.999999, 99, 'gemeldet', '2025-07-10', 52, NULL, NULL, NULL, NULL),
(1102, 66.123456, 66.123456, 66, 'gemeldet', '2025-07-11', NULL, NULL, NULL, NULL, NULL),
(1152, 55.555555, 55.555555, 55, 'Bergung bevorstehend', '2025-07-11', NULL, '2025-07-30', NULL, NULL, 1102),
(1202, 44.444444, 44.444444, 44, 'Verschollen', '2025-07-11', 602, '2025-07-15', NULL, '2025-07-15', 1052),
(1302, 23.232323, 23.232323, 23, 'gemeldet', '2025-07-16', 1002, NULL, NULL, NULL, NULL),
(1352, 4.111111, 4.111111, 11, 'gemeldet', '2025-07-18', NULL, NULL, NULL, NULL, NULL),
(1402, 46.235188, 8.235188, 80, 'gemeldet', '2025-07-18', 1152, NULL, NULL, NULL, NULL),
(1452, 46.235122, 8.235222, 22, 'gemeldet', '2025-07-20', 52, NULL, NULL, NULL, NULL),
(1502, 46.121213, 8.121213, 101, 'gemeldet', '2025-07-20', 1202, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `geisternetz_seq`
--

CREATE TABLE `geisternetz_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `geisternetz_seq`
--

INSERT INTO `geisternetz_seq` (`next_val`) VALUES
(1651);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `telefonnr` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `telefonnr`, `name`) VALUES
(52, '01661233456', 'FischerMeerle '),
(202, '0177123456', 'FischerAnna'),
(252, '0188123456', 'FischerMax'),
(302, '01661233456', 'FischerMaria'),
(452, '0177888888', 'FischerPeter'),
(602, '0173000000', ''),
(802, '112334', 'AniTschu'),
(902, '1123342', 'AniTschu2'),
(1002, '111111', 'MuellerHansDieter'),
(1052, '012347777', 'MuellerLars'),
(1102, '012345677', 'MeierDieter'),
(1152, '0166123345', 'FischerMeerle'),
(1202, '0041 177 123456', 'MustermanMax'),
(1252, '+49 1661233456', 'FischerMeerle');

-- --------------------------------------------------------

--
-- Table structure for table `person_seq`
--

CREATE TABLE `person_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `person_seq`
--

INSERT INTO `person_seq` (`next_val`) VALUES
(1351);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `geisternetz`
--
ALTER TABLE `geisternetz`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `MeldendePersonFK` (`MeldendePersonID`),
  ADD KEY `BergendePersonFK` (`BergendePersonID`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `geisternetz`
--
ALTER TABLE `geisternetz`
  ADD CONSTRAINT `BergendePersonFK` FOREIGN KEY (`BergendePersonID`) REFERENCES `person` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `MeldendePersonFK` FOREIGN KEY (`MeldendePersonID`) REFERENCES `person` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
