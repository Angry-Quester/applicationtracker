CREATE DATABASE  IF NOT EXISTS `applicationtrackerdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `applicationtrackerdb`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: applicationtrackerdb
-- ------------------------------------------------------
-- Server version	5.0.18-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `applicationId` bigint(20) NOT NULL,
  `givenName` varchar(255) collate utf8_unicode_ci default NULL,
  `middleName` varchar(255) collate utf8_unicode_ci default NULL,
  `familyName` varchar(255) collate utf8_unicode_ci default NULL,
  `birthDate` datetime default NULL,
  `creationDate` datetime default NULL,
  `lastModificationDate` datetime default NULL,
  PRIMARY KEY  (`applicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,'John','Doe','Layman','1980-09-09 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(2,'Cris','Boom','Prophet','1960-11-10 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(4,'Mary','Trinity','Tripman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(5,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(6,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(8,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(9,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(10,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(12,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(13,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(14,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(16,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(17,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(18,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(19,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(20,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(21,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(22,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(23,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(24,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(26,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(27,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(28,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(29,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(30,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(31,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(32,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(33,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(34,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(35,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(36,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(37,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(38,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(39,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(40,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(41,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(42,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(43,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(45,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(46,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(47,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(49,'John','Doe','Layman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuser`
--

DROP TABLE IF EXISTS `appuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuser` (
  `appUserId` bigint(20) NOT NULL,
  `username` varchar(255) collate utf8_unicode_ci default NULL,
  `password` varchar(255) collate utf8_unicode_ci default NULL,
  `accountNonExpired` bit(1) default NULL,
  `accountNonLocked` bit(1) default NULL,
  `credentialsNonExpired` bit(1) default NULL,
  `enabled` bit(1) default NULL,
  PRIMARY KEY  (`appUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuser`
--

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` VALUES (1,'core@mailbox.net','password','','','',''),(2,'test@mailbox.net','password','\0','\0','\0','\0'),(3,'arm@mailbox.net','password','\0','\0','\0','\0'),(4,'ocd@mailbox.net','password','\0','\0','\0','\0'),(5,'tear@mailbox.net','password','\0','\0','\0','\0'),(6,'bomb@mailbox.net','password','\0','\0','\0','\0'),(7,'cart@mailbox.net','password','\0','\0','\0','\0'),(8,'lock@mailbox.net','password','\0','\0','\0','\0'),(9,'stock@mailbox.net','password','\0','\0','\0','\0'),(10,'barrel@mailbox.net','password','\0','\0','\0','\0'),(11,'boom@mailbox.net','password','\0','\0','\0','\0'),(12,'myname@mailbox.net','password','\0','\0','\0','\0'),(13,'bam@mailbox.net','password','\0','\0','\0','\0'),(14,'taram@mailbox.net','password','\0','\0','\0','\0'),(15,'coo@mailbox.net','password','\0','\0','\0','\0'),(16,'yota@mailbox.net','password','\0','\0','\0','\0'),(17,'bota@mailbox.net','password','\0','\0','\0','\0'),(18,'coma@mailbox.net','password','\0','\0','\0','\0'),(19,'toska@maitoox.net','password','\0','\0','\0','\0'),(20,'groovy@mailbox.net','password','\0','\0','\0','\0'),(21,'movie@mailbox.net','password','\0','\0','\0','\0'),(22,'tima@mailbox.net','password','\0','\0','\0','\0'),(23,'nima@mailbox.net','password','\0','\0','\0','\0'),(24,'harder@mailbox.net','password','\0','\0','\0','\0');
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuserapplication`
--

DROP TABLE IF EXISTS `appuserapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuserapplication` (
  `appUserApplicationId` bigint(20) NOT NULL,
  `appUserId` bigint(20) NOT NULL,
  `applicationId` bigint(20) NOT NULL,
  PRIMARY KEY  (`appUserApplicationId`),
  KEY `FK8BF9C24F07C69A1` (`applicationId`),
  KEY `FK8BF9C24D65FF9F9` (`appUserId`),
  CONSTRAINT `FK8BF9C24D65FF9F9` FOREIGN KEY (`appUserId`) REFERENCES `appuser` (`appUserId`),
  CONSTRAINT `FK8BF9C24F07C69A1` FOREIGN KEY (`applicationId`) REFERENCES `application` (`applicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuserapplication`
--

LOCK TABLES `appuserapplication` WRITE;
/*!40000 ALTER TABLE `appuserapplication` DISABLE KEYS */;
INSERT INTO `appuserapplication` VALUES (1,1,1),(3,1,2),(4,1,4),(5,1,5);
/*!40000 ALTER TABLE `appuserapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuseruserrole`
--

DROP TABLE IF EXISTS `appuseruserrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuseruserrole` (
  `appUserUserRoleId` bigint(20) NOT NULL,
  `appUserId` bigint(20) NOT NULL,
  `userRoleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`appUserUserRoleId`),
  KEY `FK2A0FC8DD65FF9F9` (`appUserId`),
  KEY `FK2A0FC8D5CADC657` (`userRoleId`),
  CONSTRAINT `FK2A0FC8D5CADC657` FOREIGN KEY (`userRoleId`) REFERENCES `userrole` (`userRoleId`),
  CONSTRAINT `FK2A0FC8DD65FF9F9` FOREIGN KEY (`appUserId`) REFERENCES `appuser` (`appUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuseruserrole`
--

LOCK TABLES `appuseruserrole` WRITE;
/*!40000 ALTER TABLE `appuseruserrole` DISABLE KEYS */;
INSERT INTO `appuseruserrole` VALUES (1,1,1),(3,12,41),(4,17,44),(6,24,49);
/*!40000 ALTER TABLE `appuseruserrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `userRoleId` bigint(20) NOT NULL,
  `authority` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`userRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_ADMIN'),(4,'ROLE_ADMIN'),(5,'ROLE_USER'),(6,'ROLE_USER'),(7,'ROLE_USER'),(8,'ROLE_USER'),(9,'ROLE_USER'),(11,'ROLE_USER'),(12,'ROLE_USER'),(13,'ROLE_USER'),(15,'ROLE_USER'),(16,'ROLE_USER'),(17,'ROLE_USER'),(19,'ROLE_USER'),(20,'ROLE_USER'),(21,'ROLE_USER'),(23,'ROLE_USER'),(24,'ROLE_USER'),(25,'ROLE_USER'),(26,'ROLE_USER'),(27,'ROLE_USER'),(28,'ROLE_USER'),(29,'ROLE_USER'),(30,'ROLE_USER'),(31,'ROLE_USER'),(32,'ROLE_USER'),(33,'ROLE_USER'),(34,'ROLE_USER'),(35,'ROLE_USER'),(36,'ROLE_USER'),(37,'ROLE_USER'),(39,'ROLE_USER'),(40,'ROLE_USER'),(41,'ROLE_USER'),(43,'ROLE_USER'),(44,'ROLE_USER'),(45,'ROLE_USER'),(47,'ROLE_USER'),(48,'ROLE_USER'),(49,'ROLE_USER'),(51,'ROLE_USER');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-17 16:20:50
