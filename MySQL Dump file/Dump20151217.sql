-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: recruitmentdb
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `jobpostingtb`
--

DROP TABLE IF EXISTS `jobpostingtb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobpostingtb` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(30) DEFAULT NULL,
  `JobTitle` varchar(30) DEFAULT NULL,
  `JobDescription` varchar(150) DEFAULT NULL,
  `Location` varchar(25) DEFAULT NULL,
  `DatePosted` datetime DEFAULT NULL,
  `Contact` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobpostingtb`
--

LOCK TABLES `jobpostingtb` WRITE;
/*!40000 ALTER TABLE `jobpostingtb` DISABLE KEYS */;
INSERT INTO `jobpostingtb` VALUES (8,'Dealer.com','Java Developer','Java Coding','Burlington','2015-12-16 07:02:01','3154503962'),(9,'IP','Web Developer','Web Development','Syr','2015-12-16 07:07:48','3154503962'),(10,'Engineer','Software','Engineer','CA','2015-12-16 07:21:06','900954568'),(11,'Microsoft','Software Developer','Microsoft','NY','2015-12-16 07:30:31','abc@gmail.com'),(12,'Microsoft','.Net Developer','Coding','Redmond','2015-12-16 07:38:38','jobs@gmail.com'),(13,'Google','Web Developer','Nice','CA','2015-12-16 08:06:31','g@gg.com'),(14,'Google','Web Developer','Java Coding','CA','2015-12-16 08:30:04','g@gg.com');
/*!40000 ALTER TABLE `jobpostingtb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobseekerskillstb`
--

DROP TABLE IF EXISTS `jobseekerskillstb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobseekerskillstb` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `JobSeekerId` int(11) NOT NULL,
  `Skills` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `JobSeekerId` (`JobSeekerId`),
  CONSTRAINT `jobseekerskillstb_ibfk_1` FOREIGN KEY (`JobSeekerId`) REFERENCES `jobseekertb` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobseekerskillstb`
--

LOCK TABLES `jobseekerskillstb` WRITE;
/*!40000 ALTER TABLE `jobseekerskillstb` DISABLE KEYS */;
INSERT INTO `jobseekerskillstb` VALUES (20,9,'java'),(21,9,'C#'),(22,10,'java'),(23,10,'sql'),(24,10,'web'),(25,11,'Java'),(26,11,'Web'),(27,11,'C#'),(28,12,'Java'),(29,12,'C#'),(30,13,'Java'),(31,13,'C#'),(32,14,'java'),(33,14,'web');
/*!40000 ALTER TABLE `jobseekerskillstb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobseekertb`
--

DROP TABLE IF EXISTS `jobseekertb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobseekertb` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Experience` varchar(10) DEFAULT NULL,
  `Phone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobseekertb`
--

LOCK TABLES `jobseekertb` WRITE;
/*!40000 ALTER TABLE `jobseekertb` DISABLE KEYS */;
INSERT INTO `jobseekertb` VALUES (9,'Prateek','Hiremath','prateek.hmath@gmail.com','2','9144797890'),(10,'Shreyas','Ramesh','shreyasramesh10@gmail.com','2','9144797800'),(11,'Roshan ','Kunjukunju','roshanvarghese46@gmail.com','3','9967890909'),(12,'Rosh','Var','roshanvarghese46@gmail.com','2','3153957211'),(13,'Prateek','S H','prateek.hmath@gmail.com','2','91447890'),(14,'Shreyas','Ramesh','shreyasramesh10@gmail.com','2','9144489');
/*!40000 ALTER TABLE `jobseekertb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-17 17:18:50
