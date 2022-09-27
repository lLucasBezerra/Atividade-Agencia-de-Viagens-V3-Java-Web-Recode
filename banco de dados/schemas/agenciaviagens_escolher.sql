CREATE DATABASE  IF NOT EXISTS `agenciaviagens` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `agenciaviagens`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: agenciaviagens
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `escolher`
--

DROP TABLE IF EXISTS `escolher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `escolher` (
  `fk_codCli` int DEFAULT NULL,
  `fk_codDest` int DEFAULT NULL,
  `fk_codVoo` int DEFAULT NULL,
  KEY `fk_codCli` (`fk_codCli`),
  KEY `fk_codDest` (`fk_codDest`),
  KEY `fk_codVoo` (`fk_codVoo`),
  CONSTRAINT `escolher_ibfk_1` FOREIGN KEY (`fk_codCli`) REFERENCES `cliente` (`codCli`),
  CONSTRAINT `escolher_ibfk_2` FOREIGN KEY (`fk_codDest`) REFERENCES `destinos` (`codDest`),
  CONSTRAINT `escolher_ibfk_3` FOREIGN KEY (`fk_codVoo`) REFERENCES `voo` (`codVoo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escolher`
--

LOCK TABLES `escolher` WRITE;
/*!40000 ALTER TABLE `escolher` DISABLE KEYS */;
INSERT INTO `escolher` VALUES (10,3,2),(11,7,2),(27,1,4),(28,7,1),(29,5,1),(30,1,4),(31,1,1),(33,4,2),(33,4,2),(34,5,2),(34,5,2),(34,5,2),(34,5,2),(36,7,2),(36,7,2),(37,2,3),(38,2,1),(39,2,1),(40,5,1),(41,4,1),(42,2,2),(43,6,4),(44,5,1);
/*!40000 ALTER TABLE `escolher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-26 21:42:54
