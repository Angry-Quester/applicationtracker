CREATE DATABASE  IF NOT EXISTS `applicationtrackerdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `applicationtrackerdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: applicationtrackerdb
-- ------------------------------------------------------
-- Server version	5.6.16-log

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
-- Table structure for table `acl_class`
--

DROP TABLE IF EXISTS `acl_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_2` (`class`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_class`
--

LOCK TABLES `acl_class` WRITE;
/*!40000 ALTER TABLE `acl_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_entry`
--

DROP TABLE IF EXISTS `acl_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_4` (`acl_object_identity`,`ace_order`),
  KEY `foreign_fk_5` (`sid`),
  CONSTRAINT `foreign_fk_4` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`),
  CONSTRAINT `foreign_fk_5` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_entry`
--

LOCK TABLES `acl_entry` WRITE;
/*!40000 ALTER TABLE `acl_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_object_identity`
--

DROP TABLE IF EXISTS `acl_object_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_object_identity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) DEFAULT NULL,
  `owner_sid` bigint(20) DEFAULT NULL,
  `entries_inheriting` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_3` (`object_id_class`,`object_id_identity`),
  KEY `foreign_fk_1` (`parent_object`),
  KEY `foreign_fk_3` (`owner_sid`),
  CONSTRAINT `foreign_fk_1` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`),
  CONSTRAINT `foreign_fk_2` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
  CONSTRAINT `foreign_fk_3` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_object_identity`
--

LOCK TABLES `acl_object_identity` WRITE;
/*!40000 ALTER TABLE `acl_object_identity` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_object_identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_sid`
--

DROP TABLE IF EXISTS `acl_sid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_sid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_1` (`sid`,`principal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_sid`
--

LOCK TABLES `acl_sid` WRITE;
/*!40000 ALTER TABLE `acl_sid` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_sid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `applicationId` bigint(20) NOT NULL,
  `givenName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `middleName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `familyName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthDate` datetime DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `lastModificationDate` datetime DEFAULT NULL,
  `applicationTypeId` bigint(20) DEFAULT NULL,
  `parentApplicationId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`applicationId`),
  KEY `FK5CA40550374662B5` (`applicationTypeId`),
  KEY `FK5CA4055045464537` (`parentApplicationId`),
  CONSTRAINT `FK5CA40550374662B5` FOREIGN KEY (`applicationTypeId`) REFERENCES `applicationtype` (`applicationTypeId`),
  CONSTRAINT `FK5CA4055045464537` FOREIGN KEY (`parentApplicationId`) REFERENCES `application` (`applicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (8,'Stram','Tinker','Bass','1960-10-12 00:00:00','2014-01-02 00:00:00','2014-01-13 00:00:00',2,NULL),(24,'Mira','Rina','Grovini','2002-02-02 00:00:00','2002-02-02 00:00:00','2002-02-02 00:00:00',2,NULL),(25,'Rik','Edvard','Klynt','2003-03-03 00:00:00','2003-03-03 00:00:00','2003-03-03 00:00:00',3,NULL),(26,'Ken','Timber','Lane','2005-05-05 00:00:00','2005-05-05 00:00:00','2005-05-05 00:00:00',5,NULL),(27,'Sam','Nolan','Tramper','2004-04-04 00:00:00','2004-04-04 00:00:00','2004-04-04 00:00:00',4,NULL),(28,'Katty','Ringer','Bates','2012-12-12 00:00:00','2012-12-12 00:00:00','2012-12-12 00:00:00',1,NULL);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationform1`
--

DROP TABLE IF EXISTS `applicationform1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationform1` (
  `applicationForm1Id` bigint(20) NOT NULL,
  `contractNumber` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`applicationForm1Id`),
  KEY `FKFEA9687DEBAD3A71` (`applicationForm1Id`),
  CONSTRAINT `FKFEA9687DEBAD3A71` FOREIGN KEY (`applicationForm1Id`) REFERENCES `norate` (`noRateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationform1`
--

LOCK TABLES `applicationform1` WRITE;
/*!40000 ALTER TABLE `applicationform1` DISABLE KEYS */;
INSERT INTO `applicationform1` VALUES (28,4444444444421);
/*!40000 ALTER TABLE `applicationform1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationform11`
--

DROP TABLE IF EXISTS `applicationform11`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationform11` (
  `applicationForm11Id` bigint(20) NOT NULL,
  PRIMARY KEY (`applicationForm11Id`),
  KEY `FKD683A75463651D0B` (`applicationForm11Id`),
  CONSTRAINT `FKD683A75463651D0B` FOREIGN KEY (`applicationForm11Id`) REFERENCES `fixedrate` (`fixedRateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationform11`
--

LOCK TABLES `applicationform11` WRITE;
/*!40000 ALTER TABLE `applicationform11` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicationform11` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationform12`
--

DROP TABLE IF EXISTS `applicationform12`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationform12` (
  `applicationForm12Id` bigint(20) NOT NULL,
  PRIMARY KEY (`applicationForm12Id`),
  KEY `FKD683A75595515709` (`applicationForm12Id`),
  CONSTRAINT `FKD683A75595515709` FOREIGN KEY (`applicationForm12Id`) REFERENCES `norate` (`noRateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationform12`
--

LOCK TABLES `applicationform12` WRITE;
/*!40000 ALTER TABLE `applicationform12` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicationform12` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationform13`
--

DROP TABLE IF EXISTS `applicationform13`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationform13` (
  `applicationForm13Id` bigint(20) NOT NULL,
  PRIMARY KEY (`applicationForm13Id`),
  KEY `FKD683A75695515ACA` (`applicationForm13Id`),
  CONSTRAINT `FKD683A75695515ACA` FOREIGN KEY (`applicationForm13Id`) REFERENCES `norate` (`noRateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationform13`
--

LOCK TABLES `applicationform13` WRITE;
/*!40000 ALTER TABLE `applicationform13` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicationform13` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationform14`
--

DROP TABLE IF EXISTS `applicationform14`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationform14` (
  `applicationForm14Id` bigint(20) NOT NULL,
  PRIMARY KEY (`applicationForm14Id`),
  KEY `FKD683A75795515E8B` (`applicationForm14Id`),
  CONSTRAINT `FKD683A75795515E8B` FOREIGN KEY (`applicationForm14Id`) REFERENCES `norate` (`noRateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationform14`
--

LOCK TABLES `applicationform14` WRITE;
/*!40000 ALTER TABLE `applicationform14` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicationform14` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationtype`
--

DROP TABLE IF EXISTS `applicationtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationtype` (
  `applicationTypeId` bigint(20) NOT NULL,
  `abbreviation` varchar(255) DEFAULT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `viewAttribute` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`applicationTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationtype`
--

LOCK TABLES `applicationtype` WRITE;
/*!40000 ALTER TABLE `applicationtype` DISABLE KEYS */;
INSERT INTO `applicationtype` VALUES (1,'Ф1','Форма 1','Контракт з науково-педагогічним працівником','Контракт з науково-педагогічним працівником',NULL),(2,'Ф1а','Форма 1а','Додаткова угода до контракту','Додаткова угода до контракту',NULL),(3,'Ф1б','Форма 1б','Доповнення до додаткової угоди','Доповнення до додаткової угоди',NULL),(4,'Ф1в','Форма 1в','Заява до контракту','Заява до контракту',NULL),(5,'Ф1п','Форма 1п','Додаткова угода до контракту','Додаткова угода до контракту',NULL);
/*!40000 ALTER TABLE `applicationtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuser`
--

DROP TABLE IF EXISTS `appuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuser` (
  `appUserId` bigint(20) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `accountNonExpired` bit(1) DEFAULT NULL,
  `accountNonLocked` bit(1) DEFAULT NULL,
  `credentialsNonExpired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`appUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuser`
--

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
  PRIMARY KEY (`appUserId`,`applicationId`),
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
INSERT INTO `appuserapplication` VALUES (2,8),(1,24),(1,25),(1,26),(1,27),(1,28);
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
  PRIMARY KEY (`appUserId`,`userRoleId`),
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
-- Table structure for table `fixedrate`
--

DROP TABLE IF EXISTS `fixedrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fixedrate` (
  `fixedRateId` bigint(20) NOT NULL,
  `rate` float DEFAULT NULL,
  PRIMARY KEY (`fixedRateId`),
  KEY `FK2C8CCE749FF28DA5` (`fixedRateId`),
  CONSTRAINT `FK2C8CCE749FF28DA5` FOREIGN KEY (`fixedRateId`) REFERENCES `application` (`applicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixedrate`
--

LOCK TABLES `fixedrate` WRITE;
/*!40000 ALTER TABLE `fixedrate` DISABLE KEYS */;
INSERT INTO `fixedrate` VALUES (24,0.75);
/*!40000 ALTER TABLE `fixedrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hourlyrate`
--

DROP TABLE IF EXISTS `hourlyrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hourlyrate` (
  `hourlyRateId` bigint(20) NOT NULL,
  `hours` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`hourlyRateId`),
  KEY `FK4F0D3AF12409CEE2` (`hourlyRateId`),
  CONSTRAINT `FK4F0D3AF12409CEE2` FOREIGN KEY (`hourlyRateId`) REFERENCES `application` (`applicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hourlyrate`
--

LOCK TABLES `hourlyrate` WRITE;
/*!40000 ALTER TABLE `hourlyrate` DISABLE KEYS */;
/*!40000 ALTER TABLE `hourlyrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `norate`
--

DROP TABLE IF EXISTS `norate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `norate` (
  `noRateId` bigint(20) NOT NULL,
  PRIMARY KEY (`noRateId`),
  KEY `FKC1F80641876F0632` (`noRateId`),
  CONSTRAINT `FKC1F80641876F0632` FOREIGN KEY (`noRateId`) REFERENCES `application` (`applicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `norate`
--

LOCK TABLES `norate` WRITE;
/*!40000 ALTER TABLE `norate` DISABLE KEYS */;
INSERT INTO `norate` VALUES (26),(27),(28);
/*!40000 ALTER TABLE `norate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `userRoleId` bigint(20) NOT NULL,
  `authority` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
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

-- Dump completed on 2014-05-02 11:52:06
