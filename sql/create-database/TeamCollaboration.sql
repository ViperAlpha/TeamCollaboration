-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: TeamCollaboration
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `Team`
--

DROP TABLE IF EXISTS `Team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Team` (
  `teamId` int(11) NOT NULL AUTO_INCREMENT,
  `teamName` varchar(20) NOT NULL,
  `teamDescription` varchar(100) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Team`
--


--
-- Table structure for table `TeamMember`
--

DROP TABLE IF EXISTS `TeamMember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamMember` (
  `teamMemberId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `teamId` int(11) DEFAULT NULL,
  PRIMARY KEY (`teamMemberId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamMember`
--

--
-- Table structure for table `TeamMessage`
--

DROP TABLE IF EXISTS `TeamMessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamMessage` (
  `teamMessageId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserid` int(11) DEFAULT NULL,
  `toTeamId` int(11) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`teamMessageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamMessage`
--

LOCK TABLES `TeamMessage` WRITE;
/*!40000 ALTER TABLE `TeamMessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamMessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `firstName` varchar(40) DEFAULT NULL,
  `lastName` varchar(40) DEFAULT NULL,
  `phoneNumber` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

--
-- Table structure for table `UserMessage`
--

DROP TABLE IF EXISTS `UserMessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserMessage` (
  `userMessageId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(11) DEFAULT NULL,
  `toUserId` int(11) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `userMessageChatId` int(11) DEFAULT NULL,
  `messageTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userMessageId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserMessage`
--


--
-- Table structure for table `UserMessageChat`
--

DROP TABLE IF EXISTS `UserMessageChat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserMessageChat` (
  `userMessageChatId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(11) DEFAULT NULL,
  `toUserId` int(11) DEFAULT NULL,
  `chatTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userMessageChatId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserMessageChat`
--
--
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `userRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(10) unsigned NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRole`
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-05 18:11:46
