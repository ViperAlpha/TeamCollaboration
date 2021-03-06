-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: TeamCollaboration
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
  `teamLeader` int(11) NOT NULL,
  `teamDescription` varchar(200) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Team`
--

LOCK TABLES `Team` WRITE;
/*!40000 ALTER TABLE `Team` DISABLE KEYS */;
/*!40000 ALTER TABLE `Team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamInvitation`
--

DROP TABLE IF EXISTS `TeamInvitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamInvitation` (
  `teamInvitationId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(11) NOT NULL,
  `toUserId` int(11) NOT NULL,
  `toTeamId` int(11) NOT NULL,
  `invitationTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `message` varchar(100) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`teamInvitationId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamInvitation`
--

LOCK TABLES `TeamInvitation` WRITE;
/*!40000 ALTER TABLE `TeamInvitation` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamInvitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamMember`
--

DROP TABLE IF EXISTS `TeamMember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamMember` (
  `teamMemberId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `teamId` int(11) NOT NULL,
  PRIMARY KEY (`teamMemberId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamMember`
--

LOCK TABLES `TeamMember` WRITE;
/*!40000 ALTER TABLE `TeamMember` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamMember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamMessage`
--

DROP TABLE IF EXISTS `TeamMessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamMessage` (
  `teamMessageId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(11) DEFAULT NULL,
  `toTeamId` int(11) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `messageTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`teamMessageId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamMessageChat` (
  `teamMessageChatId` int(11) NOT NULL AUTO_INCREMENT,
  `teamId` int(11) NOT NULL,
  `chatTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`teamMessageChatId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamMessageChat`
--

LOCK TABLES `TeamMessageChat` WRITE;
/*!40000 ALTER TABLE `TeamMessageChat` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamMessageChat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamUploadedFile`
--

DROP TABLE IF EXISTS `TeamUploadedFile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamUploadedFile` (
  `teamUploadedFileKey` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `filePath` varchar(500) DEFAULT NULL,
  `teamMessageChatId` int(11) DEFAULT NULL,
  PRIMARY KEY (`teamUploadedFileKey`),
  KEY `TeamUploadedFile_User_userId_fk` (`userId`),
  KEY `TeamUploadedFile_TeamMessage_teamMessageId_fk` (`teamMessageChatId`),
  CONSTRAINT `TeamUploadedFile_TeamMessage_teamMessageId_fk` FOREIGN KEY (`teamMessageChatId`) REFERENCES `TeamMessage` (`teamMessageId`),
  CONSTRAINT `TeamUploadedFile_User_userId_fk` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamUploadedFile`
--

LOCK TABLES `TeamUploadedFile` WRITE;
/*!40000 ALTER TABLE `TeamUploadedFile` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamUploadedFile` ENABLE KEYS */;
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
  `lastLoggedIn` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `avatarPicture` text,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (12,'joe@gmail.com','$2a$10$JSRcaYBXM2MP5gOaNBJtAezsDtblv43feGcyDfwy3AjVbQh4CXtrK','Joe','Repo','(920)-728-7269','2016-04-28 19:32:11','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/pEFcwGRX'),(13,'dan@gmail.com','$2a$10$1IxJK.XiPn6W7oYd/2KAvu4TiZ6Rv9AIKCGI7xlk9WKMAy8UVMiRq','Dan','Jock','(920)-728-4269','2016-04-28 19:32:15',NULL),(14,'thao@uww.edu','$2a$10$XUbVaTerJI/ubhXkNYNAIu8.wfanXJRKKLJ/H1PZW9EZOAAdqUXZ.','cheng','thao','(920)-728-4269','2016-04-28 19:32:13',NULL),(15,'admin@gmail.com','$2a$10$kNx0HjX0iW1C751.TfH3kOjr086z2tOcUHeXxC.1GKm9T5cNtS/Na','FirstAdmin','LastAdmin','(920)-728-4269',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserMessage`
--

LOCK TABLES `UserMessage` WRITE;
/*!40000 ALTER TABLE `UserMessage` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserMessageChat`
--

LOCK TABLES `UserMessageChat` WRITE;
/*!40000 ALTER TABLE `UserMessageChat` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRole`
--

LOCK TABLES `UserRole` WRITE;
/*!40000 ALTER TABLE `UserRole` DISABLE KEYS */;
INSERT INTO `UserRole` VALUES (11,12,'ROLE_USER'),(12,13,'ROLE_USER'),(13,14,'ROLE_USER'),(14,15,'ROLE_ADMIN');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserUploadedFile`
--

LOCK TABLES `UserUploadedFile` WRITE;
/*!40000 ALTER TABLE `UserUploadedFile` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserUploadedFile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Wiki`
--

DROP TABLE IF EXISTS `Wiki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Wiki` (
  `wikiId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `content` text,
  `editTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`wikiId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Wiki`
--

LOCK TABLES `Wiki` WRITE;
/*!40000 ALTER TABLE `Wiki` DISABLE KEYS */;
/*!40000 ALTER TABLE `Wiki` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-28 15:23:02
