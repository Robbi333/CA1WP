-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2023 at 05:20 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `libraryapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `authorid` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `bookid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `Bookid` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `AuthorID` int(11) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `PublicationYear` int(11) NOT NULL,
  `GenreID` int(11) NOT NULL,
  `TotalCopies` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`Bookid`, `Title`, `AuthorID`, `ISBN`, `PublicationYear`, `GenreID`, `TotalCopies`, `Description`) VALUES
(1, 'Harry Potter and the Philosopher\'s Stone\r\n', 1, 2147483647, 1997, 1, 1200000, 'An 11-year-old orphan living with his unwelcoming aunt, uncle, and cousin, who learns of his own fame as a wizard known to have survived his parents\' murder at the hands of the dark wizard Lord Voldemort as an infant when he is accepted to Hogwarts School'),
(2, 'Harry Potter and the Chamber of Secrets', 1, 123589026, 1998, 2, 770000, 'Throughout the summer holidays after his first year at Hogwarts School of Witchcraft and Wizardry, Harry Potter has been receiving sinister warnings from a house-elf called Dobby. Now, back at school to start his second year, Harry hears unintelligible wh'),
(3, 'Harry Potter and the Prisoner of Azkaban', 1, 830827838, 1999, 3, 650000, 'Harry Potter & The Prisoner of Azkaban is about Harry\'s 3rd year at Hogwarts. Along with friends Ron and Hermione, Harry investigates the case of Sirius Black, an escaped prisoner from Azkaban, the wizard prison.'),
(4, 'Harry Potter and the Goblet of Fire', 1, 387086705, 2000, 4, 40000, 'It follows Harry Potter, a wizard in his fourth year at Hogwarts School of Witchcraft and Wizardry, and the mystery surrounding the entry of Harry\'s name into the Triwizard Tournament, in which he is forced to compete. The book was published in the United'),
(5, 'Harry Potter and the Order of the Phoenix', 1, 691461178, 2003, 5, 660000, 'Dark times have come to Hogwarts. After the Dementors\' attack on his cousin Dudley, Harry Potter knows that Voldemort will stop at nothing to find him. There are many who deny the Dark Lord\'s return, but Harry is not alone: a secret order gathers at Grimm'),
(6, 'Charlie and the Chocolate Factory', 6, 927491739, 1964, 6, 2600000, 'Charlie And The Chocolate Factory is a delightful children\'s book by Roald Dahl. It tells the story of a young boy named Charlie Bucket, who wins a golden ticket and gets the opportunity to tour the mysterious and magical chocolate factory owned by the ec');

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `genreid` int(11) NOT NULL,
  `genre1` varchar(255) NOT NULL,
  `genre2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `MemberID` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `First_Name` varchar(255) DEFAULT NULL,
  `Last_Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Address1` varchar(255) DEFAULT NULL,
  `Address2` varchar(255) DEFAULT NULL,
  `Eircode` varchar(10) DEFAULT NULL,
  `Phone_Number` varchar(20) DEFAULT NULL,
  `Registration_Date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`authorid`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`Bookid`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`genreid`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`MemberID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `authorid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `genreid` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
