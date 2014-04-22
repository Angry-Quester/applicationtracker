-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: applicationtrackerdbtest
-- ------------------------------------------------------
-- Server version   5.0.18-nt

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
-- Table structure for table `acl_class`
--

DROP TABLE IF EXISTS `acl_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_class` (
  `id` bigint(20) NOT NULL auto_increment,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unique_uk_2` (`class`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `acl_entry`
--

DROP TABLE IF EXISTS `acl_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_entry` (
  `id` bigint(20) NOT NULL auto_increment,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unique_uk_4` (`acl_object_identity`,`ace_order`),
  KEY `foreign_fk_5` (`sid`),
  CONSTRAINT `foreign_fk_4` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`),
  CONSTRAINT `foreign_fk_5` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `acl_object_identity`
--

DROP TABLE IF EXISTS `acl_object_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_object_identity` (
  `id` bigint(20) NOT NULL auto_increment,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) default NULL,
  `owner_sid` bigint(20) default NULL,
  `entries_inheriting` tinyint(1) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unique_uk_3` (`object_id_class`,`object_id_identity`),
  KEY `foreign_fk_1` (`parent_object`),
  KEY `foreign_fk_3` (`owner_sid`),
  CONSTRAINT `foreign_fk_1` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`),
  CONSTRAINT `foreign_fk_2` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
  CONSTRAINT `foreign_fk_3` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `acl_sid`
--

DROP TABLE IF EXISTS `acl_sid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_sid` (
  `id` bigint(20) NOT NULL auto_increment,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unique_uk_1` (`sid`,`principal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
INSERT INTO `application` VALUES (1,'John','Doe','Layman','1980-09-09 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(2,'Cris','Boom','Prophet','1960-11-10 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(4,'Mary','Trinity','Tripman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(5,'Trip','Snow','Karamel','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(6,'Rod','Iron','Pupm','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(8,'Stram','Tinker','Bass','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(9,'Gordon','Ruger','Every','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(10,'Kit','Apple','Stoleman','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(12,'Vik','Krammer','Domry','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(13,'Hamphry','Bruce','Stimmer','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00'),(14,'Emmet','Brother','Brown','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00');
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

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` VALUES (1,'core@mailbox.net','password','','','',''),(2,'test@mailbox.net','password','','','','');
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuserapplication`
--

DROP TABLE IF EXISTS `appuserapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuserapplication` (
  `appUserId` bigint(20) NOT NULL,
  `applicationId` bigint(20) NOT NULL,
  PRIMARY KEY  (`appUserId`,`applicationId`),
  KEY `FK8BF9C24F07C69A1` (`applicationId`),
  KEY `FK8BF9C24D65FF9F9` (`appUserId`),
  CONSTRAINT `FK8BF9C24D65FF9F9` FOREIGN KEY (`appUserId`) REFERENCES `appuser` (`appUserId`),
  CONSTRAINT `FK8BF9C24F07C69A1` FOREIGN KEY (`applicationId`) REFERENCES `application` (`applicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuserapplication`
--

LOCK TABLES `appuserapplication` WRITE;
/*!40000 ALTER TABLE `appuserapplication` DISABLE KEYS */;
INSERT INTO `appuserapplication` VALUES (1,1),(1,2),(1,4),(1,5),(2,6),(2,8);
/*!40000 ALTER TABLE `appuserapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuseruserrole`
--

DROP TABLE IF EXISTS `appuseruserrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuseruserrole` (
  `appUserId` bigint(20) NOT NULL,
  `userRoleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`appUserId`,`userRoleId`),
  KEY `FK2A0FC8DD65FF9F9` (`appUserId`),
  KEY `FK2A0FC8D5CADC657` (`userRoleId`),
  CONSTRAINT `FK2A0FC8D5CADC657` FOREIGN KEY (`userRoleId`) REFERENCES `userrole` (`userRoleId`),
  CONSTRAINT `FK2A0FC8DD65FF9F9` FOREIGN KEY (`appUserId`) REFERENCES `appuser` (`appUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuseruserrole`
--

LOCK TABLES `appuseruserrole` WRITE;
/*!40000 ALTER TABLE `appuseruserrole` DISABLE KEYS */;
INSERT INTO `appuseruserrole` VALUES (1,1),(2,2);
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

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;


/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-22 19:12:53
