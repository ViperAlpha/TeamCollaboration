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
INSERT INTO `Team` VALUES (1,'SE101',12,'Software Engineering Group','2016-04-14 15:16:14'),(2,'TestTeam',12,'The Testing Team','2016-04-28 14:27:36');
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
INSERT INTO `TeamInvitation` VALUES (1,12,14,1,'2016-04-14 20:16:33','Hey man accept this invitation','accepted'),(2,12,14,2,'2016-04-28 19:27:58','Invitaiton To Test Team','accepted'),(3,12,13,2,'2016-04-28 19:28:09','Invitaiton To Test Team','accepted');
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
INSERT INTO `TeamMember` VALUES (1,12,1),(2,14,1),(3,12,2),(4,13,2),(5,14,2);
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
INSERT INTO `TeamMessage` VALUES (1,12,1,'team file message test','2016-04-14 20:25:06'),(2,12,1,'abc','2016-04-14 20:26:28'),(3,12,1,'abv','2016-04-14 20:27:43'),(4,12,1,'avc','2016-04-14 20:28:25'),(5,12,1,'message','2016-04-22 15:31:14'),(6,12,1,'added file','2016-04-22 15:31:41'),(7,12,2,'Hey man you see my messages in test team','2016-04-28 19:28:23'),(8,14,2,'yes :)','2016-04-28 19:28:42'),(9,13,2,'sup','2016-04-28 19:28:45'),(10,13,2,'what','2016-04-28 19:29:37'),(11,13,2,'whaaaaaaaaat','2016-04-28 19:30:22');
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
INSERT INTO `TeamMessageChat` VALUES (1,1,'2016-04-14 20:16:14'),(2,2,'2016-04-28 19:27:36');
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
INSERT INTO `TeamUploadedFile` VALUES (1,12,'IMG-20160404-WA0006.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/team-uploaded-files/Egntsfpc',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserMessage`
--

LOCK TABLES `UserMessage` WRITE;
/*!40000 ALTER TABLE `UserMessage` DISABLE KEYS */;
INSERT INTO `UserMessage` VALUES (38,14,12,'How are you doing joe.',5,'2016-03-20 01:24:25'),(39,12,13,'Hey man',6,'2016-03-20 01:25:37'),(40,12,14,'WE AREN\"T SENDING ATTACHMENTS BECAUSE WE DON\"T WANT TO DEAL WITH SETUP',5,'2016-03-20 01:25:59'),(41,14,12,'Hey how u doin',5,'2016-04-10 04:26:43'),(42,12,14,'hey',5,'2016-04-10 04:26:53'),(43,12,14,'what about it',5,'2016-04-10 04:27:00'),(44,14,12,'Hey man',5,'2016-04-10 04:27:07'),(45,12,14,'file test',5,'2016-04-10 04:27:41'),(46,12,14,'file test',5,'2016-04-10 04:27:44'),(47,12,14,'file test',5,'2016-04-10 04:27:45'),(48,12,14,'file test',5,'2016-04-10 04:27:48'),(49,12,14,'file test',5,'2016-04-10 04:27:48'),(50,12,14,'file test',5,'2016-04-10 04:28:11'),(51,14,12,'How you doin',5,'2016-04-10 04:29:25'),(52,12,14,'How are you doing man?',5,'2016-04-14 19:12:15'),(53,14,12,'good, how are you?',5,'2016-04-14 19:12:33'),(54,12,14,'great',5,'2016-04-14 19:12:43'),(55,12,0,'d',7,'2016-04-14 19:56:11'),(56,12,14,'Sending File',5,'2016-04-14 20:35:16'),(57,12,14,'sending a message.',5,'2016-04-22 02:35:07'),(58,12,14,'message',5,'2016-04-22 02:35:13'),(59,12,14,'added file',5,'2016-04-22 15:32:04'),(60,12,14,'added f',5,'2016-04-22 15:32:17'),(61,12,14,'Hey man',5,'2016-04-28 19:11:40'),(62,14,12,'hello',5,'2016-04-28 19:11:52'),(63,12,14,'Sent mesage',5,'2016-04-28 19:25:20'),(64,14,12,'hi :*',5,'2016-04-28 19:25:28'),(65,12,14,'Hello World',5,'2016-04-28 19:25:36'),(66,12,14,'Hello World Attach File',5,'2016-04-28 19:26:02'),(67,12,14,'Hello World Attach File',5,'2016-04-28 19:26:02'),(68,14,12,'file sending',5,'2016-04-28 19:26:41');
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
INSERT INTO `UserMessageChat` VALUES (5,14,12,'2016-03-20 01:24:25'),(6,12,13,'2016-03-20 01:25:37'),(7,12,0,'2016-04-14 19:56:11');
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
INSERT INTO `UserUploadedFile` VALUES (6,12,'IMG-20160408-WA0002.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160408-WA0002.jpg',45),(7,12,'IMG-20160408-WA0002.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160408-WA0002.jpg',46),(8,12,'IMG-20160408-WA0002.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160408-WA0002.jpg',47),(9,12,'IMG-20160408-WA0002.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160408-WA0002.jpg',48),(10,12,'IMG-20160408-WA0002.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160408-WA0002.jpg',49),(11,12,'IMG-20160404-WA0006.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160404-WA0006.jpg',50),(12,12,'README.md','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/README.md',56),(13,12,'Install_PrestaShop.html','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/Install_PrestaShop.html',60),(14,12,'IMG-20160404-WA0006.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160404-WA0006.jpg',67),(15,12,'IMG-20160404-WA0006.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160404-WA0006.jpg',66),(16,14,'IMG-20160404-WA0006.jpg','/home/horvste/IdeaProjects/TeamCollaborationClone/TeamCollaborationClone/user-uploaded-files/IMG-20160404-WA0006.jpg',68);
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
INSERT INTO `Wiki` VALUES (1,12,'# TeamCollaboration\n> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering\n\n# Pre-requisits\n\nTo run this project you must ensure:\n- Java JDK 8 is installed and in your path.\n- Mysql is installed and its address is `mysql://localhost:3306/`\n- You have created a database with name `TeamCollaboration`\n    - Can be done through mysql command prompt \n```sql\nCREATE DATABASE TeamCollaboration;\n```\n- You have a username and password in the mysql database with username `root` and with password `root`\n- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**\n    - Note that the Linux version is included in the `apache/linux/*` folder\n- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**\n\n*Note that many of these properties can be changed within the application.properties file in this spring project. \nObviously any changes to that file shouldn\'t be committed to break others project.*\n\n# Instructions\n\nAfter ensuring that you have all the above done here is a step by step process:\n1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:\n```\nmysql> USE TeamCollaboration;           # Selecting the proper database to run this project in\nmysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory\nmysql> SHOW TABLES;                     # Ensure that the tables were created\n```\n2. Open up this project in Intellij IDEA or STS Bundle\n    - For Intellij:\n        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)\n    - For STS Bundle:\n        - TODO: Documentation. I don\'t use eclipse so I don\'t know\n\n# Testing\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: joe@gmail.com\npassword: joe@gmail.com\n\nusername: dan@gmail.com\npassword: dan@gmail.com\n\nusername: thao@uww.edu\npassword: thao@uww.edu\n```\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: admin@gmail.com\npassword: admin@gmail.com\n\n```\nYou will have to change `resources/application.properties` file\'s `messaging.user.download.directory` constant to\nan already created directory on your box if you want messages to be sent with `links/attachments`\n\n# Colaborators\n\n- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)\n- [Reinaldo Moreira](https://github.com/ViperAlpha)\n- [Steven Horvatin](https://github.com/horvste)','2016-04-10 02:10:45'),(2,12,'# TeamCollaboration\n> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering\n\n# Pre-requisits\n\nTo run this project you must ensure:\n- Java JDK 8 is installed and in your path.\n- Mysql is installed and its address is `mysql://localhost:3306/`\n- You have created a database with name `TeamCollaboration`\n    - Can be done through mysql command prompt \n```sql\nCREATE DATABASE TeamCollaboration;\n```\n- You have a username and password in the mysql database with username `root` and with password `root`\n- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**\n    - Note that the Linux version is included in the `apache/linux/*` folder\n- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**\n\n*Note that many of these properties can be changed within the application.properties file in this spring project. \nObviously any changes to that file shouldn\'t be committed to break others project.*\n\n# Instructions\n\nAfter ensuring that you have all the above done here is a step by step process:\n1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:\n```\nmysql> USE TeamCollaboration;           # Selecting the proper database to run this project in\nmysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory\nmysql> SHOW TABLES;                     # Ensure that the tables were created\n```\n2. Open up this project in Intellij IDEA or STS Bundle\n    - For Intellij:\n        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)\n    - For STS Bundle:\n        - TODO: Documentation. I don\'t use eclipse so I don\'t know\n\n# Testing\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: joe@gmail.com\npassword: joe@gmail.com\n\nusername: dan@gmail.com\npassword: dan@gmail.com\n\nusername: thao@uww.edu\npassword: thao@uww.edu\n```\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: admin@gmail.com\npassword: admin@gmail.com\n\n```\nYou will have to change `resources/application.properties` file\'s `messaging.user.download.directory` constant to\nan already created directory on your box if you want messages to be sent with `links/attachments`\n\n# Colaborators\n\n- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)\n- [Reinaldo Moreira](https://github.com/ViperAlpha)\n- [Steven Horvatin](https://github.com/horvste)','2016-04-10 02:10:52'),(3,12,'# TeamCollaboration\n> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering\n\n# Pre-requisits\n\nTo run this project you must ensure:\n- Java JDK 8 is installed and in your path.\n- Mysql is installed and its address is `mysql://localhost:3306/`\n- You have created a database with name `TeamCollaboration`\n    - Can be done through mysql command prompt \n```sql\nCREATE DATABASE TeamCollaboration;\n```\n- You have a username and password in the mysql database with username `root` and with password `root`\n- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**\n    - Note that the Linux version is included in the `apache/linux/*` folder\n- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**\n\n*Note that many of these properties can be changed within the application.properties file in this spring project. \nObviously any changes to that file shouldn\'t be committed to break others project.*\n\n# Instructions\n\nAfter ensuring that you have all the above done here is a step by step process:\n1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:\n```\nmysql> USE TeamCollaboration;           # Selecting the proper database to run this project in\nmysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory\nmysql> SHOW TABLES;                     # Ensure that the tables were created\n```\n2. Open up this project in Intellij IDEA or STS Bundle\n    - For Intellij:\n        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)\n    - For STS Bundle:\n        - TODO: Documentation. I don\'t use eclipse so I don\'t know\n\n# Testing\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: joe@gmail.com\npassword: joe@gmail.com\n\nusername: dan@gmail.com\npassword: dan@gmail.com\n\nusername: thao@uww.edu\npassword: thao@uww.edu\n```\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: admin@gmail.com\npassword: admin@gmail.com\n\n```\nYou will have to change `resources/application.properties` file\'s `messaging.user.download.directory` constant to\nan already created directory on your box if you want messages to be sent with `links/attachments`\n\n# Collaborators\n\n- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)\n- [Reinaldo Moreira](https://github.com/ViperAlpha)\n- [Steven Horvatin](https://github.com/horvste)','2016-04-10 02:12:04'),(4,12,'# TeamCollaboration\n> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering\n\n# Pre-requisits\n\nTo run this project you must ensure:\n- Java JDK 8 is installed and in your path.\n- Mysql is installed and its address is `mysql://localhost:3306/`\n- You have created a database with name `TeamCollaboration`\n    - Can be done through mysql command prompt \n```sql\nCREATE DATABASE TeamCollaboration;\n```\n- You have a username and password in the mysql database with username `root` and with password `root`\n- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**\n    - Note that the Linux version is included in the `apache/linux/*` folder\n- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**\n\n*Note that many of these properties can be changed within the application.properties file in this spring project. \nObviously any changes to that file shouldn\'t be committed to break others project.*\n\n# Instructions\n\nAfter ensuring that you have all the above done here is a step by step process:\n1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:\n```\nmysql> USE TeamCollaboration;           # Selecting the proper database to run this project in\nmysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory\nmysql> SHOW TABLES;                     # Ensure that the tables were created\n```\n2. Open up this project in Intellij IDEA or STS Bundle\n    - For Intellij:\n        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)\n    - For STS Bundle:\n        - TODO: Documentation. I don\'t use eclipse so I don\'t know\n\n# Testing\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: joe@gmail.com\npassword: joe@gmail.com\n\nusername: dan@gmail.com\npassword: dan@gmail.com\n\nusername: thao@uww.edu\npassword: thao@uww.edu\n```\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: admin@gmail.com\npassword: admin@gmail.com\n\n```\nYou will have to change `resources/application.properties` file\'s `messaging.user.download.directory` constant to\nan already created directory on your box if you want messages to be sent with `links/attachments`\n\n# Collaborators\n\n- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)\n- [Reinaldo Moreira](https://github.com/ViperAlpha)\n- [Steven Horvatin](https://github.com/horvste)','2016-04-10 03:29:42'),(5,12,'# TeamCollaboration\n> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering\n\n# Pre-requisits\n\nTo run this project you must ensure:\n- Java JDK 8 is installed and in your path.\n- Mysql is installed and its address is `mysql://localhost:3306/`\n- You have created a database with name `TeamCollaboration`\n    - Can be done through mysql command prompt \n```sql\nCREATE DATABASE TeamCollaboration;\n```\n- You have a username and password in the mysql database with username `root` and with password `root`\n- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**\n    - Note that the Linux version is included in the `apache/linux/*` folder\n- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**\n\n*Note that many of these properties can be changed within the application.properties file in this spring project. \nObviously any changes to that file shouldn\'t be committed to break others project.*\n\n# Instructions\n\nAfter ensuring that you have all the above done here is a step by step process:\n1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:\n```\nmysql> USE TeamCollaboration;           # Selecting the proper database to run this project in\nmysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory\nmysql> SHOW TABLES;                     # Ensure that the tables were created\n```\n2. Open up this project in Intellij IDEA or STS Bundle\n    - For Intellij:\n        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)\n    - For STS Bundle:\n        - TODO: Documentation. I don\'t use eclipse so I don\'t know\n\n# Testing\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: joe@gmail.com\npassword: joe@gmail.com\n\nusername: dan@gmail.com\npassword: dan@gmail.com\n\nusername: thao@uww.edu\npassword: thao@uww.edu\n```\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: admin@gmail.com\npassword: admin@gmail.com\n\n```\nYou will have to change `resources/application.properties` file\'s `messaging.user.download.directory` constant to\nan already created directory on your box if you want messages to be sent with `links/attachments`\n\n# Collaborators\n\n- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)\n- [Reinaldo Moreira](https://github.com/ViperAlpha)\n- [Steven Horvatin](https://github.com/horvste)','2016-04-10 03:29:45'),(6,12,'# TeamCollaboration\n> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering\n\n# Pre-requisits\n\nTo run this project you must ensure:\n- Java JDK 8 is installed and in your path.\n- Mysql is installed and its address is `mysql://localhost:3306/`\n- You have created a database with name `TeamCollaboration`\n    - Can be done through mysql command prompt \n```sql\nCREATE DATABASE TeamCollaboration;\n```\n- You have a username and password in the mysql database with username `root` and with password `root`\n- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**\n    - Note that the Linux version is included in the `apache/linux/*` folder\n- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**\n\n*Note that many of these properties can be changed within the application.properties file in this spring project. \nObviously any changes to that file shouldn\'t be committed to break others project.*\n\n# Instructions\n\nAfter ensuring that you have all the above done here is a step by step process:\n1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:\n```\nmysql> USE TeamCollaboration;           # Selecting the proper database to run this project in\nmysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory\nmysql> SHOW TABLES;                     # Ensure that the tables were created\n```\n2. Open up this project in Intellij IDEA or STS Bundle\n    - For Intellij:\n        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)\n    - For STS Bundle:\n        - TODO: Documentation. I don\'t use eclipse so I don\'t know\n\n# Testing\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: joe@gmail.com\npassword: joe@gmail.com\n\nusername: dan@gmail.com\npassword: dan@gmail.com\n\nusername: thao@uww.edu\npassword: thao@uww.edu\n```\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: admin@gmail.com\npassword: admin@gmail.com\n\n```\nYou will have to change `resources/application.properties` file\'s `messaging.user.download.directory` constant to\nan already created directory on your box if you want messages to be sent with `links/attachments`\n\n# Collaborators\n\n- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)\n- [Reinaldo Moreira](https://github.com/ViperAlpha)\n- [Steven Horvatin](https://github.com/horvste)','2016-04-10 03:29:47'),(7,12,'# TeamCollaboration\n> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering\n\n# Pre-requisits\n\nTo run this project you must ensure:\n- Java JDK 8 is installed and in your path.\n- Mysql is installed and its address is `mysql://localhost:3306/`\n- You have created a database with name `TeamCollaboration`\n    - Can be done through mysql command prompt \n```sql\nCREATE DATABASE TeamCollaboration;\n```\n- You have a username and password in the mysql database with username `root` and with password `root`\n- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**\n    - Note that the Linux version is included in the `apache/linux/*` folder\n- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**\n\n*Note that many of these properties can be changed within the application.properties file in this spring project. \nObviously any changes to that file shouldn\'t be committed to break others project.*\n\n# Instructions\n\nAfter ensuring that you have all the above done here is a step by step process:\n1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:\n```\nmysql> USE TeamCollaboration;           # Selecting the proper database to run this project in\nmysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory\nmysql> SHOW TABLES;                     # Ensure that the tables were created\n```\n2. Open up this project in Intellij IDEA or STS Bundle\n    - For Intellij:\n        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)\n    - For STS Bundle:\n        - TODO: Documentation. I don\'t use eclipse so I don\'t know\n\n# Testing\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: joe@gmail.com\npassword: joe@gmail.com\n\nusername: dan@gmail.com\npassword: dan@gmail.com\n\nusername: thao@uww.edu\npassword: thao@uww.edu\n```\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: admin@gmail.com\npassword: admin@gmail.com\n\n```\nYou will have to change `resources/application.properties` file\'s `messaging.user.download.directory` constant to\nan already created directory on your box if you want messages to be sent with `links/attachments`\n\n# Collaborators\n\n- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)\n- [Reinaldo Moreira](https://github.com/ViperAlpha)\n- [Steven Horvatin](https://github.com/horvste)','2016-04-10 03:29:49'),(8,12,'# TeamCollaboration\n> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering\n\n# Pre-requisits\n\nTo run this project you must ensure:\n- Java JDK 8 is installed and in your path.\n- Mysql is installed and its address is `mysql://localhost:3306/`\n- You have created a database with name `TeamCollaboration`\n    - Can be done through mysql command prompt \n```sql\nCREATE DATABASE TeamCollaboration;\n```\n- You have a username and password in the mysql database with username `root` and with password `root`\n- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**\n    - Note that the Linux version is included in the `apache/linux/*` folder\n- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**\n\n*Note that many of these properties can be changed within the application.properties file in this spring project. \nObviously any changes to that file shouldn\'t be committed to break others project.*\n\n# Instructions\n\nAfter ensuring that you have all the above done here is a step by step process:\n1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:\n```\nmysql> USE TeamCollaboration;           # Selecting the proper database to run this project in\nmysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory\nmysql> SHOW TABLES;                     # Ensure that the tables were created\n```\n2. Open up this project in Intellij IDEA or STS Bundle\n    - For Intellij:\n        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)\n    - For STS Bundle:\n        - TODO: Documentation. I don\'t use eclipse so I don\'t know\n\n# Testing\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: joe@gmail.com\npassword: joe@gmail.com\n\nusername: dan@gmail.com\npassword: dan@gmail.com\n\nusername: thao@uww.edu\npassword: thao@uww.edu\n```\n\nThese are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:\n\n```\nusername: admin@gmail.com\npassword: admin@gmail.com\n\n```\nYou will have to change `resources/application.properties` file\'s `messaging.user.download.directory` constant to\nan already created directory on your box if you want messages to be sent with `links/attachments`\n\n# Collaborators\n\n- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)\n- [Reinaldo Moreira](https://github.com/ViperAlpha)\n- [Steven Horvatin](https://github.com/horvste)','2016-04-10 03:29:55'),(9,12,'','2016-04-10 03:30:00'),(10,12,'','2016-04-10 03:30:01'),(11,12,'','2016-04-10 03:30:03'),(12,12,'','2016-04-10 03:30:04'),(13,12,'fas','2016-04-10 03:30:06'),(14,12,'fasdsa','2016-04-10 03:30:07'),(15,12,'fasdsa','2016-04-10 03:30:10'),(16,12,'fasdsafdasfda','2016-04-10 03:30:13'),(17,12,'fasdsafdasfda','2016-04-10 03:30:15'),(18,12,'fasdsafdassadffasfda','2016-04-10 03:30:19'),(19,12,'','2016-04-10 03:30:22'),(20,12,'dfgafds','2016-04-10 03:30:28'),(21,12,'fdgsdgdsgfd','2016-04-10 03:30:31'),(22,12,'sadgagsdgadsfasf','2016-04-10 03:30:35'),(23,12,'fgasdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff','2016-04-10 03:30:41'),(24,12,'new edit','2016-04-28 19:30:56'),(25,14,'new stuff going on','2016-04-28 19:32:06'),(26,13,'not new editing!!','2016-04-28 19:32:12');
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
