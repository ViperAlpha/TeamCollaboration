-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 17, 2016 at 11:42 AM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `TeamCollaboration`
--

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
  `teamMessageChatId` int(11) NOT NULL,
  PRIMARY KEY (`teamMessageId`),
  UNIQUE KEY `teamMessageChatId` (`teamMessageChatId`),
  UNIQUE KEY `fromUserid` (`fromUserId`,`toTeamId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
