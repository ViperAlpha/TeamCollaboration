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

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `TeamCollaboration`
--
CREATE DATABASE IF NOT EXISTS `TeamCollaboration` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `TeamCollaboration`;

-- --------------------------------------------------------

--
-- Table structure for table `Team`
--

DROP TABLE IF EXISTS `Team`;
CREATE TABLE IF NOT EXISTS `Team` (
  `teamId` int(11) NOT NULL AUTO_INCREMENT,
  `teamName` varchar(20) NOT NULL,
  `teamDescription` varchar(100) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`teamId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Team`
--

INSERT INTO `Team` (`teamId`, `teamName`, `teamDescription`, `createdTime`) VALUES
(1, 'Team', 'Best team.', '2016-03-16 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `TeamInvitation`
--

DROP TABLE IF EXISTS `TeamInvitation`;
CREATE TABLE IF NOT EXISTS `TeamInvitation` (
  `teamInvitationId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(11) NOT NULL,
  `toUserId` int(11) NOT NULL,
  `invitationTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`teamInvitationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `TeamMember`
--

DROP TABLE IF EXISTS `TeamMember`;
CREATE TABLE IF NOT EXISTS `TeamMember` (
  `teamMemberId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `teamId` int(11) DEFAULT NULL,
  `teamMessageChatId` int(11) NOT NULL,
  PRIMARY KEY (`teamMemberId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `TeamMessage`
--

DROP TABLE IF EXISTS `TeamMessage`;
CREATE TABLE IF NOT EXISTS `TeamMessage` (
  `teamMessageId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(11) DEFAULT NULL,
  `toTeamId` int(11) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `messageTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`teamMessageId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `TeamMessage`
--

LOCK TABLES `TeamMessage` WRITE;
/*!40000 ALTER TABLE `TeamMessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamMessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamMessageChat`
--

DROP TABLE IF EXISTS `TeamMessageChat`;
CREATE TABLE IF NOT EXISTS `TeamMessageChat` (
  `teamMessageChatId` int(11) NOT NULL AUTO_INCREMENT,
  `teamId` int(11) NOT NULL,
  `chatTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`teamMessageChatId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `TeamMessageChat`
--

INSERT INTO `TeamMessageChat` (`teamMessageChatId`, `fromUserId`, `teamId`, `chatTime`, `members`) VALUES
(1, 1, 1, '2016-03-17 08:04:00', 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (12,'joe@gmail.com','$2a$10$JSRcaYBXM2MP5gOaNBJtAezsDtblv43feGcyDfwy3AjVbQh4CXtrK','Joe','Repo','(920)-728-7269'),(13,'dan@gmail.com','$2a$10$1IxJK.XiPn6W7oYd/2KAvu4TiZ6Rv9AIKCGI7xlk9WKMAy8UVMiRq','Dan','Jock','(920)-728-4269'),(14,'thao@uww.edu','$2a$10$XUbVaTerJI/ubhXkNYNAIu8.wfanXJRKKLJ/H1PZW9EZOAAdqUXZ.','cheng','thao','(920)-728-4269');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserInvitation`
--

DROP TABLE IF EXISTS `UserInvitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserInvitation` (
  `userInvitationId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(11) NOT NULL,
  `toUserId` int(11) NOT NULL,
  `message` varchar(100) DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`userInvitationId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserInvitation`
--

LOCK TABLES `UserInvitation` WRITE;
/*!40000 ALTER TABLE `UserInvitation` DISABLE KEYS */;
INSERT INTO `UserInvitation` VALUES (6,12,14,'Please come chat with me professor.','accepted'),(7,13,12,'Lets catchup buddy.','accepted'),(9,14,13,'second invite attempt','accepted');
/*!40000 ALTER TABLE `UserInvitation` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserMessage`
--

LOCK TABLES `UserMessage` WRITE;
/*!40000 ALTER TABLE `UserMessage` DISABLE KEYS */;
INSERT INTO `UserMessage` VALUES (38,14,12,'How are you doing joe.',5,'2016-03-20 01:24:25'),(39,12,13,'Hey man',6,'2016-03-20 01:25:37'),(40,12,14,'WE AREN\"T SENDING ATTACHMENTS BECAUSE WE DON\"T WANT TO DEAL WITH SETUP',5,'2016-03-20 01:25:59');
/*!40000 ALTER TABLE `UserMessage` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserMessageChat`
--

LOCK TABLES `UserMessageChat` WRITE;
/*!40000 ALTER TABLE `UserMessageChat` DISABLE KEYS */;
INSERT INTO `UserMessageChat` VALUES (5,14,12,'2016-03-20 01:24:25'),(6,12,13,'2016-03-20 01:25:37');
/*!40000 ALTER TABLE `UserMessageChat` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRole`
--

LOCK TABLES `UserRole` WRITE;
/*!40000 ALTER TABLE `UserRole` DISABLE KEYS */;
INSERT INTO `UserRole` VALUES (11,12,'ROLE_USER'),(12,13,'ROLE_USER'),(13,14,'ROLE_USER');
/*!40000 ALTER TABLE `UserRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserUploadedFile`
--

DROP TABLE IF EXISTS `UserUploadedFile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserUploadedFile` (
  `userUploadedFileKey` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `fileName` varchar(100) NOT NULL,
  `filePath` varchar(500) NOT NULL,
  `chatId` int(11) NOT NULL,
  PRIMARY KEY (`userUploadedFileKey`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserUploadedFile`
--

LOCK TABLES `UserUploadedFile` WRITE;
/*!40000 ALTER TABLE `UserUploadedFile` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserUploadedFile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-19 20:36:46
