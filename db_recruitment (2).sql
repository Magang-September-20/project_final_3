-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 19, 2020 at 05:36 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_recruitment`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `scheduleTest` (IN `Param1` VARCHAR(50))  BEGIN
SELECT date, start_time, end_time, location, tes.name, u.full_name, p.name
FROM tb_tr_schedule_test st JOIN tb_m_test tes ON (st.test=tes.id) JOIN tb_tr_program_apply apply ON (st.apply = apply.id)
JOIN tb_m_user u ON (st.pic=u.id) JOIN tb_m_program p ON (apply.program=p.id) JOIN tb_m_user o ON (apply.candidate=o.id)
WHERE o.email = Param1;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_education`
--

CREATE TABLE `tb_m_education` (
  `id` int(4) NOT NULL,
  `degree` enum('D3','S1','S2','S3') NOT NULL,
  `status` enum('Lulus','Belum Lulus') NOT NULL,
  `ipk` float(3,2) NOT NULL,
  `major` int(4) NOT NULL,
  `university` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_m_education`
--

INSERT INTO `tb_m_education` (`id`, `degree`, `status`, `ipk`, `major`, `university`) VALUES
(1, 'S1', 'Lulus', 3.00, 1, 1),
(2, 'S1', 'Lulus', 3.00, 2, 2),
(3, 'S2', 'Lulus', 3.00, 1, 3),
(4, 'S2', 'Lulus', 3.00, 2, 4),
(5, 'S3', 'Lulus', 3.00, 2, 5),
(6, 'S1', 'Lulus', 3.00, 1, 6),
(7, 'S1', 'Lulus', 3.00, 2, 7),
(8, 'D3', 'Lulus', 4.00, 1, 8),
(9, 'D3', 'Lulus', 4.00, 2, 9),
(10, 'S1', 'Lulus', 3.00, 6, 10),
(11, 'S1', 'Lulus', 3.00, 6, 11),
(12, 'S1', 'Lulus', 3.00, 1, 12),
(13, 'S2', 'Lulus', 3.00, 2, 13),
(14, 'D3', 'Belum Lulus', 3.00, 6, 14),
(15, 'D3', 'Belum Lulus', 3.00, 6, 1),
(16, 'D3', 'Lulus', 3.00, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_file`
--

CREATE TABLE `tb_m_file` (
  `id` int(4) NOT NULL,
  `cv` varchar(225) NOT NULL,
  `photo` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_m_file`
--

INSERT INTO `tb_m_file` (`id`, `cv`, `photo`) VALUES
(1, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(2, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(3, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(4, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(5, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(6, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(7, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(8, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(9, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(10, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(11, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(12, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(13, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(14, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(15, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz'),
(16, 'https://tinyurl.com/y5q4o7pd', 'https://tinyurl.com/y2ydfnxz');

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_major`
--

CREATE TABLE `tb_m_major` (
  `id` int(4) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_m_major`
--

INSERT INTO `tb_m_major` (`id`, `name`) VALUES
(1, 'Teknik Informatika'),
(2, 'Sistem Informasi'),
(3, 'Teknik Mesin'),
(4, 'Teknik Kimia'),
(5, 'Teknik Elektro'),
(6, 'Ilmu Komputer'),
(7, 'Kimia'),
(8, 'Matematika'),
(9, 'Akuntansi'),
(10, 'Sastra');

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_program`
--

CREATE TABLE `tb_m_program` (
  `id` int(4) NOT NULL,
  `name` varchar(100) NOT NULL,
  `hr` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_m_program`
--

INSERT INTO `tb_m_program` (`id`, `name`, `hr`) VALUES
(1, 'Internship', 2),
(2, 'Junior Progammer', 3),
(3, 'Senior Progammer', 2),
(4, 'Front end Progammer', 3),
(5, 'Back end Progammer', 4);

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_test`
--

CREATE TABLE `tb_m_test` (
  `id` int(4) NOT NULL,
  `name` varchar(25) NOT NULL,
  `passing_grade` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_m_test`
--

INSERT INTO `tb_m_test` (`id`, `name`, `passing_grade`) VALUES
(1, 'Psikotest', 80),
(2, 'Technical Test', 80),
(3, 'Interview', 80);

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_university`
--

CREATE TABLE `tb_m_university` (
  `id` int(4) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_m_university`
--

INSERT INTO `tb_m_university` (`id`, `name`) VALUES
(1, 'Universitas Diponegoro'),
(2, 'Universitas Sebelas Maret'),
(3, 'Universitas Negeri Semarang'),
(4, 'Universitas Jenderal Soedirman'),
(5, 'Universitas Katolik Soegijapranata'),
(6, 'Universitas Dian Nuswantoro'),
(7, 'Universitas Kristen Satya Wacana'),
(8, 'Universitas Islam Sultan Agung'),
(9, 'Universitas Muhammadiyah Surakarta'),
(10, 'Universitas PGRI Semarang'),
(11, 'Universitas Muhammadiyah Magelang'),
(12, 'Universitas Stikubank'),
(13, 'Universitas Duta Wacana'),
(14, 'Universitas Bina Nusantara');

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_user`
--

CREATE TABLE `tb_m_user` (
  `id` int(4) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(40) NOT NULL,
  `gender` enum('Laki-laki','Perempuan','','') NOT NULL,
  `birth_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_m_user`
--

INSERT INTO `tb_m_user` (`id`, `full_name`, `email`, `gender`, `birth_date`) VALUES
(1, 'Nathaniel Ray Raharjo', 'nathaniel.raharjo@gmail.com', 'Laki-laki', '1999-01-01'),
(2, 'Yosefa Oktaviana Dia', 'yosefadia27@gmail.com', 'Perempuan', '1998-01-01'),
(3, 'Olivia Michelle', 'michelleolivia301@gmail.com', 'Perempuan', '1999-06-10'),
(4, 'Novi Panna Vira', 'novipannavira@gmail.com', 'Laki-laki', '1999-03-03'),
(5, 'Gunawan Zega', 'faktagunawanzega@gmail.com', 'Laki-laki', '1999-04-04'),
(6, 'Jonathan Purnama Halim', 'jonathanpurnama13@gmail.com', 'Laki-laki', '1998-05-05'),
(7, 'Willy Chrisna', 'willychrisna77@gmail.com', 'Laki-laki', '1999-06-06'),
(8, 'Herul Syahwandi S', 'herul.syah@gmail.com', 'Laki-laki', '1999-07-07'),
(9, 'Bachtiar Nur Yogi P', 'bachtiarnuryogipratama@gmail.com', 'Laki-laki', '1999-07-07'),
(10, 'Muhamad Irfan Bernadius', 'muhamadi7696@gmail.com', 'Laki-laki', '1999-08-08'),
(11, 'gulugulu', 'gulu@gulu', 'Laki-laki', '2020-09-15'),
(12, 'Jonathan Purnama Halim', 'swejer1st@gmail.com', 'Laki-laki', '2020-10-09'),
(13, 'Nathannnn', 'asdasdasd@as', 'Perempuan', '2020-08-11'),
(14, 'jonathan purnama halimmmmm', 'asdasdas@asw', 'Perempuan', '2020-08-06'),
(15, 'jonathan purnama halimmmmm', 'asdasdas@aswa', 'Perempuan', '2020-08-06'),
(16, 'Olivvvvvv', 'michelleolivia68@gmail.com', 'Perempuan', '2020-09-16');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_program_apply`
--

CREATE TABLE `tb_tr_program_apply` (
  `id` int(4) NOT NULL,
  `candidate` int(4) NOT NULL,
  `note` varchar(200) NOT NULL,
  `hr` int(4) NOT NULL,
  `program` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_tr_program_apply`
--

INSERT INTO `tb_tr_program_apply` (`id`, `candidate`, `note`, `hr`, `program`) VALUES
(1, 9, 'saya lord yogi', 2, 1),
(2, 16, 'saya semangattt', 4, 5),
(3, 9, 'saya lordd', 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_result`
--

CREATE TABLE `tb_tr_result` (
  `id` int(4) NOT NULL,
  `grade` int(4) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  `is_passed` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_tr_result`
--

INSERT INTO `tb_tr_result` (`id`, `grade`, `note`, `is_passed`) VALUES
(1, 80, 'mantab', 1),
(2, 0, 'belum di isi', 0),
(3, 95, 'jago', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_schedule_test`
--

CREATE TABLE `tb_tr_schedule_test` (
  `id` int(4) NOT NULL,
  `date` date NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `location` varchar(100) NOT NULL,
  `test` int(4) NOT NULL,
  `apply` int(4) NOT NULL,
  `pic` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_tr_schedule_test`
--

INSERT INTO `tb_tr_schedule_test` (`id`, `date`, `start_time`, `end_time`, `location`, `test`, `apply`, `pic`) VALUES
(1, '2020-10-20', '17:15:25', '21:15:25', 'rumah', 1, 1, 2),
(2, '2020-10-20', '15:18:00', '16:17:00', 'neraka', 1, 2, 4),
(3, '2020-10-27', '16:19:00', '16:25:00', 'mantab', 1, 3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_m_education`
--
ALTER TABLE `tb_m_education`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_edu_major` (`major`),
  ADD KEY `fk_edu_university` (`university`);

--
-- Indexes for table `tb_m_file`
--
ALTER TABLE `tb_m_file`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_m_major`
--
ALTER TABLE `tb_m_major`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_m_program`
--
ALTER TABLE `tb_m_program`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hr` (`hr`);

--
-- Indexes for table `tb_m_test`
--
ALTER TABLE `tb_m_test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_m_university`
--
ALTER TABLE `tb_m_university`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_m_user`
--
ALTER TABLE `tb_m_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tr_program_apply`
--
ALTER TABLE `tb_tr_program_apply`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_apply_program` (`program`),
  ADD KEY `fk_apply_candidate` (`candidate`),
  ADD KEY `fk_apply_hr` (`hr`);

--
-- Indexes for table `tb_tr_result`
--
ALTER TABLE `tb_tr_result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tr_schedule_test`
--
ALTER TABLE `tb_tr_schedule_test`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_schedule_test` (`test`),
  ADD KEY `fk_schedule_apply` (`apply`),
  ADD KEY `fk_schedule_pic` (`pic`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_m_major`
--
ALTER TABLE `tb_m_major`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tb_m_university`
--
ALTER TABLE `tb_m_university`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `tb_tr_program_apply`
--
ALTER TABLE `tb_tr_program_apply`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_tr_schedule_test`
--
ALTER TABLE `tb_tr_schedule_test`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_m_education`
--
ALTER TABLE `tb_m_education`
  ADD CONSTRAINT `fk_edu_major` FOREIGN KEY (`major`) REFERENCES `tb_m_major` (`id`),
  ADD CONSTRAINT `fk_edu_university` FOREIGN KEY (`university`) REFERENCES `tb_m_university` (`id`),
  ADD CONSTRAINT `fk_user_edu` FOREIGN KEY (`id`) REFERENCES `tb_m_user` (`id`);

--
-- Constraints for table `tb_m_file`
--
ALTER TABLE `tb_m_file`
  ADD CONSTRAINT `fk_user_file` FOREIGN KEY (`id`) REFERENCES `tb_m_user` (`id`);

--
-- Constraints for table `tb_m_program`
--
ALTER TABLE `tb_m_program`
  ADD CONSTRAINT `tb_m_program_ibfk_1` FOREIGN KEY (`hr`) REFERENCES `tb_m_user` (`id`);

--
-- Constraints for table `tb_tr_program_apply`
--
ALTER TABLE `tb_tr_program_apply`
  ADD CONSTRAINT `fk_apply_candidate` FOREIGN KEY (`candidate`) REFERENCES `tb_m_user` (`id`),
  ADD CONSTRAINT `fk_apply_hr` FOREIGN KEY (`hr`) REFERENCES `tb_m_user` (`id`),
  ADD CONSTRAINT `fk_apply_program` FOREIGN KEY (`program`) REFERENCES `tb_m_program` (`id`);

--
-- Constraints for table `tb_tr_result`
--
ALTER TABLE `tb_tr_result`
  ADD CONSTRAINT `tb_tr_result_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_tr_schedule_test` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_tr_schedule_test`
--
ALTER TABLE `tb_tr_schedule_test`
  ADD CONSTRAINT `fk_schedule_apply` FOREIGN KEY (`apply`) REFERENCES `tb_tr_program_apply` (`id`),
  ADD CONSTRAINT `fk_schedule_pic` FOREIGN KEY (`pic`) REFERENCES `tb_m_user` (`id`),
  ADD CONSTRAINT `fk_schedule_test` FOREIGN KEY (`test`) REFERENCES `tb_m_test` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
