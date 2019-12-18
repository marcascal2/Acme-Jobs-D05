-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: acme-jobs
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2a5vcjo3stlfcwadosjfq49l1` (`user_account_id`),
  CONSTRAINT `FK_2a5vcjo3stlfcwadosjfq49l1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (6,0,5);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (1671,0,'2019-06-10 08:35:00.000000','http://www.github.com','Now you can create your own repository','Repositories announcement'),(1672,0,'2019-06-09 22:00:00.000000','','Buying 2 we give you some sunglasses','Glasses announcement'),(1673,0,'1900-10-10 19:24:44.000000','http://www.twitter.com','Enjoy the most popular social network','Social announcement'),(1674,0,'2019-10-10 08:35:00.000000','http://www.ev.us.es','All our computers at a 10% discount','Laptop announcement'),(1675,0,'2019-11-01 09:35:00.000000','','This is an announcement of doors','Doors announcement');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anonymous`
--

DROP TABLE IF EXISTS `anonymous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anonymous` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6lnbc6fo3om54vugoh8icg78m` (`user_account_id`),
  CONSTRAINT `FK_6lnbc6fo3om54vugoh8icg78m` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anonymous`
--

LOCK TABLES `anonymous` WRITE;
/*!40000 ALTER TABLE `anonymous` DISABLE KEYS */;
INSERT INTO `anonymous` VALUES (2,0,1);
/*!40000 ALTER TABLE `anonymous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `justification` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  `reference_number` varchar(255) DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `job_id` int(11) NOT NULL,
  `worker_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rf84q38qr35ymh5nn0dcxfdue` (`reference_number`),
  KEY `IDXg54pxa1gngqheaipukeg8jypk` (`moment`),
  KEY `FKoa6p4s2oyy7tf80xwc4r04vh6` (`job_id`),
  KEY `FKmbjdoxi3o93agxosoate4sxbt` (`worker_id`),
  CONSTRAINT `FKmbjdoxi3o93agxosoate4sxbt` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`),
  CONSTRAINT `FKoa6p4s2oyy7tf80xwc4r04vh6` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1808,0,'','2019-11-22 09:35:00.000000','Average degree in web programming','EMP1-APP1:WOR1','PHP and web deployment','Job application for web development',0,1729,1707),(1809,0,'','2019-12-10 04:16:00.000000','Average degree in web programming','EMP1-APP2:WOR1','Web knowledge','Web functionality update',1,1730,1707),(1810,0,'Rejected','2019-12-11 10:35:00.000000','Informatics Engineering','EMP2-APP3:WOR2','Control capacity and group leadership','Project management',2,1731,1710),(1811,0,'','2019-12-05 09:00:00.000000','Python or Master in artificial vision / artificial intelligence','EMP2-APP4:WOR2','Vision and artificial intelligence tasks','R and D project',0,1732,1710),(1812,0,'','2019-11-30 09:00:00.000000','Business Management Dregree','EMP1-APP5:WOR3','Business leadership, financial analysis and accounting balances','Business Analysis',0,1733,1713),(1813,0,'','2019-10-11 09:35:00.000000','Middle Grade Formative Cycle','EMP2-APP6:WOR3','Strategic alliances, databases and entrepreneurship','National expansion process of training and networking services',1,1731,1713),(1814,0,'','2019-03-22 09:00:00.000000','Certified Software Test Professional','EMP2-APP7:WOR1','Programming and testing knowledge','Unitary testing',0,1735,1707),(1815,0,'rejected','2019-11-19 09:00:00.000000','Compulsory Secondary Education','EMP1-APP8:WOR3','Dynamic person, with ambition for professional development, driving license and own vehicle.','Valet job',2,1733,1713),(1816,0,'','2019-11-04 13:35:00.000000','Average degree in web programming','EMP1-APP9:WOR3','PHP and web deployment','Job application for web development',2,1729,1713),(1817,0,'','2019-04-15 08:32:00.000000','Average degree in web programming','EMP1-AP10:WOR2','Web knowledge','Web functionality update',0,1730,1710),(1818,0,'','2018-12-02 22:08:00.000000','Informatics Engineering','EMP2-AP11:WOR3','Control capacity and group leadership','Project management',2,1731,1713),(1819,0,'','2018-10-04 11:40:00.000000','Python or Master in artificial vision / artificial intelligence','EMP2-AP12:WOR1','Vision and artificial intelligence tasks','R and D project',2,1732,1707),(1820,0,'','2019-11-23 11:56:00.000000','Business Management Dregree','EMP1-AP13:WOR1','Business leadership, financial analysis and accounting balances','Business Analysis',1,1733,1707),(1821,0,'','2019-11-11 08:16:00.000000','Middle Grade Formative Cycle','EMP2-AP14:WOR2','Strategic alliances, databases and entrepreneurship','National expansion process of training and networking services',0,1731,1710),(1822,0,'','2019-11-28 05:48:00.000000','Certified Software Test Professional','EMP2-AP15:WOR2','Programming and testing knowledge','Unitary testing',0,1735,1710),(1823,0,'','2019-07-19 18:04:00.000000','Compulsory Secondary Education','EMP1-AP16:WOR1','Dynamic person, with ambition for professional development, driving license and own vehicle.','Valet job',0,1733,1707),(1824,0,NULL,'2019-11-22 11:40:00.000000','Average degree in web programming','EMP1-AP17:WOR1','PHP and web deployment','Job application for web development',0,1729,1707),(1825,0,NULL,'2019-12-10 02:29:00.000000','Average degree in web programming','EMP1-AP18:WOR2','Web knowledge','Web functionality update',1,1730,1710),(1826,0,NULL,'2019-12-11 10:35:00.000000','Informatics Engineering','EMP2-AP19:WOR1','Control capacity and group leadership','Project management',2,1731,1707),(1827,0,NULL,'2019-12-05 11:30:00.000000','Python or Master in artificial vision / artificial intelligence','EMP2-AP20:WOR2','Vision and artificial intelligence tasks','R and D project',0,1732,1710),(1828,0,NULL,'2019-11-30 09:00:00.000000','Business Management Dregree','EMP1-AP21:WOR3','Business leadership, financial analysis and accounting balances','Business Analysis',0,1733,1713),(1829,0,NULL,'2019-12-11 10:35:00.000000','Middle Grade Formative Cycle','EMP2-AP22:WOR3','Strategic alliances, databases and entrepreneurship','National expansion process of training and networking services',1,1731,1713),(1830,0,NULL,'2019-03-22 09:00:00.000000','Certified Software Test Professional','EMP2-AP23:WOR1','Programming and testing knowledge','Unitary testing',0,1735,1707),(1831,0,NULL,'2019-11-19 13:52:00.000000','Compulsory Secondary Education','EMP1-AP24:WOR3','Dynamic person, with ambition for professional development, driving license and own vehicle.','Valet job',2,1733,1713),(1832,0,NULL,'2019-11-04 14:56:00.000000','Average degree in web programming','EMP1-AP25:WOR3','PHP and web deployment','Job application for web development',2,1729,1713),(1833,0,NULL,'2019-11-25 12:32:00.000000','Average degree in web programming','EMP1-AP26:WOR2','Web knowledge','Web functionality update',0,1730,1710),(1834,0,NULL,'2018-12-02 08:08:00.000000','Informatics Engineering','EMP2-AP27:WOR3','Control capacity and group leadership','Project management',2,1734,1713),(1835,0,NULL,'2018-11-30 09:50:00.000000','Python or Master in artificial vision / artificial intelligence','EMP2-AP28:WOR1','Vision and artificial intelligence tasks','R and D project',2,1732,1707),(1836,0,NULL,'2019-11-23 12:06:00.000000','Business Management Dregree','EMP1-AP29:WOR1','Business leadership, financial analysis and accounting balances','Business Analysis',1,1733,1707),(1837,0,NULL,'2019-12-11 08:16:00.000000','Middle Grade Formative Cycle','EMP2-AP30:WOR2','Strategic alliances, databases and entrepreneurship','National expansion process of training and networking services',0,1731,1710),(1838,0,NULL,'2019-11-28 15:18:00.000000','Certified Software Test Professional','EMP2-AP31:WOR2','Programming and testing knowledge','Unitary testing',0,1735,1710),(1839,0,NULL,'2019-11-19 19:03:00.000000','Compulsory Secondary Education','EMP1-AP32:WOR1','Dynamic person, with ambition for professional development, driving license and own vehicle.','Valet job',0,1733,1707);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit_record`
--

DROP TABLE IF EXISTS `audit_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `creation_moment` datetime(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `auditor_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmryxi458u0xeo07s40poxshk6` (`auditor_id`),
  KEY `FKlbvbyimxf6pxvbhkdd4vfhlnd` (`job_id`),
  CONSTRAINT `FKlbvbyimxf6pxvbhkdd4vfhlnd` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `FKmryxi458u0xeo07s40poxshk6` FOREIGN KEY (`auditor_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_record`
--

LOCK TABLES `audit_record` WRITE;
/*!40000 ALTER TABLE `audit_record` DISABLE KEYS */;
INSERT INTO `audit_record` VALUES (1800,0,'Audit Record 1 for R and D project','2018-07-12 10:55:00.000000',1,'Audit record 1 of auditor 1',1737,1732),(1801,0,'Audit Record 2 for a web functionality updating','2018-07-12 10:55:00.000000',1,'Audit record 2 of auditor 1',1737,1730),(1802,0,'Audit Record 2 for R and D project','2018-07-12 10:55:00.000000',1,'Audit record 1 of auditor 2',1740,1732),(1803,0,'Audit Record for a project management','2018-07-12 10:55:00.000000',0,'Audit record 3 of auditor 1',1737,1731),(1804,0,'Audit Record for web development','2018-07-12 10:55:00.000000',0,'Audit record 2 of auditor 2',1740,1729),(1805,0,'Audit Record for a valet job','2018-07-12 10:55:00.000000',0,'Audit record 3 of auditor 2',1740,1736);
/*!40000 ALTER TABLE `audit_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditor`
--

DROP TABLE IF EXISTS `auditor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `firm` varchar(255) DEFAULT NULL,
  `responsability_statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clqcq9lyspxdxcp6o4f3vkelj` (`user_account_id`),
  CONSTRAINT `FK_clqcq9lyspxdxcp6o4f3vkelj` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditor`
--

LOCK TABLES `auditor` WRITE;
/*!40000 ALTER TABLE `auditor` DISABLE KEYS */;
INSERT INTO `auditor` VALUES (1738,0,1737,'Auditor1','Project supervision responsibility statement'),(1741,0,1740,'Auditor2','Statement of responsibility for checking compliance with schedules');
/*!40000 ALTER TABLE `auditor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditor_record`
--

DROP TABLE IF EXISTS `auditor_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditor_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `creation_moment` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `auditor_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ck5stk38bbuyidbdwsm7nndj` (`auditor_id`),
  KEY `FKcpwoo69w5dhtr8nvg0xhl9qv9` (`job_id`),
  CONSTRAINT `FK2ck5stk38bbuyidbdwsm7nndj` FOREIGN KEY (`auditor_id`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FKcpwoo69w5dhtr8nvg0xhl9qv9` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditor_record`
--

LOCK TABLES `auditor_record` WRITE;
/*!40000 ALTER TABLE `auditor_record` DISABLE KEYS */;
INSERT INTO `auditor_record` VALUES (3172,0,'Auditor Record 1 for R and D project','2018-07-12 10:55:00.000000','draft','Auditor record 1 of auditor 1',3135,3130),(3173,0,'Auditor Record 2 for a web functionality updating','2018-07-12 10:55:00.000000','draft','Auditor record 2 of auditor 1',3135,3128),(3174,0,'Auditor Record 2 for R and D project','2018-07-12 10:55:00.000000','draft','Auditor record 1 of auditor 2',3138,3130),(3175,0,'Auditor Record for a project management','2018-07-12 10:55:00.000000','published','Auditor record 3 of auditor 1',3135,3129),(3176,0,'Auditor Record for web development','2018-07-12 10:55:00.000000','published','Auditor record 2 of auditor 2',3138,3127),(3177,0,'Auditor Record for a valet job','2018-07-12 10:55:00.000000','published','Auditor record 3 of auditor 2',3138,3134);
/*!40000 ALTER TABLE `auditor_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditors_request`
--

DROP TABLE IF EXISTS `auditors_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditors_request` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `accepted` bit(1) DEFAULT NULL,
  `firm` varchar(255) DEFAULT NULL,
  `responsability_statement` varchar(255) DEFAULT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo9yoxw3isaapgkwfhg252g5yv` (`user_account_id`),
  CONSTRAINT `FKo9yoxw3isaapgkwfhg252g5yv` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditors_request`
--

LOCK TABLES `auditors_request` WRITE;
/*!40000 ALTER TABLE `auditors_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `auditors_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authenticated`
--

DROP TABLE IF EXISTS `authenticated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authenticated` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h52w0f3wjoi68b63wv9vwon57` (`user_account_id`),
  CONSTRAINT `FK_h52w0f3wjoi68b63wv9vwon57` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authenticated`
--

LOCK TABLES `authenticated` WRITE;
/*!40000 ALTER TABLE `authenticated` DISABLE KEYS */;
INSERT INTO `authenticated` VALUES (4,0,3),(7,0,5),(1708,0,1706),(1711,0,1709),(1714,0,1712),(1717,0,1715),(1720,0,1718),(1739,0,1737),(1742,0,1740),(1765,0,1763),(1768,0,1766),(1771,0,1769);
/*!40000 ALTER TABLE `authenticated` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `bronze_goal` varchar(255) DEFAULT NULL,
  `bronze_reward_amount` double DEFAULT NULL,
  `bronze_reward_currency` varchar(255) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gold_goal` varchar(255) DEFAULT NULL,
  `gold_reward_amount` double DEFAULT NULL,
  `gold_reward_currency` varchar(255) DEFAULT NULL,
  `silver_goal` varchar(255) DEFAULT NULL,
  `silver_reward_amount` double DEFAULT NULL,
  `silver_reward_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXnr284tes3x8hnd3h716tmb3fr` (`deadline`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge`
--

LOCK TABLES `challenge` WRITE;
/*!40000 ALTER TABLE `challenge` DISABLE KEYS */;
INSERT INTO `challenge` VALUES (1676,0,'Low quality video',50,'EUR','2019-11-26 11:45:00.000000','This is the description of a simple challenge','High quality video',200,'EUR','Medium quality video',100,'EUR','Video challenge'),(1677,0,'Eat 3 dishes of food',150.5,'EUR','2020-01-09 12:00:00.000000','This is the description of a medium challenge','Eat 5 dishes of food',350,'EUR','Eat 4 dishes of food',200,'EUR','Food challenge'),(1678,0,'Get 2K views',100.78,'EUR','2020-01-17 20:30:00.000000','This is the description of a difficult challenge','Get 10K views',500,'EUR','Get 5K views',220.65,'EUR','Musical challenge');
/*!40000 ALTER TABLE `challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commercial_banner`
--

DROP TABLE IF EXISTS `commercial_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commercial_banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `credit_card` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd0k52g7lcacefcp62kb4p9aor` (`sponsor_id`),
  CONSTRAINT `FKd0k52g7lcacefcp62kb4p9aor` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commercial_banner`
--

LOCK TABLES `commercial_banner` WRITE;
/*!40000 ALTER TABLE `commercial_banner` DISABLE KEYS */;
INSERT INTO `commercial_banner` VALUES (1694,1,'5313694966604077','https://www.dropbox.com/s/27pgcxdp7717lgu/banner4.jpg?dl=0','Commercial banner 1','http://www.github.com/',1767),(1695,1,'5313694966604077','https://www.dropbox.com/s/jx64uaxs1yh8nqs/banner5.jpg?dl=0','Commercial banner 2','http://www.dropbox.com/',1770),(1696,1,'5313694966604077','https://www.dropbox.com/s/qy602gkel4v8jl0/banner6.jpg?dl=0','Commercial banner 3','http://www.ev.us.es/',1770),(1697,1,'5313694966604077','https://www.dropbox.com/s/27pgcxdp7717lgu/banner4.jpg?dl=0','Commercial banner 4','http://www.github.com/',1767),(1698,1,'5313694966604077','https://www.dropbox.com/s/jx64uaxs1yh8nqs/banner5.jpg?dl=0','Commercial banner 5','http://www.dropbox.com/',1764),(1699,1,'5313694966604077','https://www.dropbox.com/s/qy602gkel4v8jl0/banner6.jpg?dl=0','Commercial banner 6','http://www.ev.us.es/',1764);
/*!40000 ALTER TABLE `commercial_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_record`
--

DROP TABLE IF EXISTS `company_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `ceo_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `incorporated` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  `stars` double DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_record`
--

LOCK TABLES `company_record` WRITE;
/*!40000 ALTER TABLE `company_record` DISABLE KEYS */;
INSERT INTO `company_record` VALUES (1690,0,'Mark','Company for the music industry','markevans@gmail.com',_binary '\0','New Music','+1 (13) 123456','Musical sector',0,'http://www.youtube.com'),(1691,0,'Carlos','Company focused on sheet metal manufacturing','carloscasado10@gmail.com',_binary '\0','Sheet metal company','+12 (12) 1234567','Industrial sector',0,'http://www.sheetmetal.com'),(1692,0,'Eliot','Expert company in the real estate sector','eliotem@gmail.com',_binary '','RealS','+123 (123) 12345678','Real estate',0,'http://www.realstatesec.com'),(1693,0,'Fran','Vacuum packed food company','fran1999@gmail.com',_binary '','Fooder','+100 (9999) 1234567392','Food sector',5,'http://www.foodcompany.com');
/*!40000 ALTER TABLE `company_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6cyha9f1wpj0dpbxrrjddrqed` (`user_account_id`),
  CONSTRAINT `FK_6cyha9f1wpj0dpbxrrjddrqed` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `credit_card_number` varchar(255) DEFAULT NULL,
  `cvc` varchar(255) DEFAULT NULL,
  `month` varchar(255) DEFAULT NULL,
  `title_holder` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4cr95y27s8ti6otoyflmma6oy` (`sponsor_id`),
  CONSTRAINT `FK31l5hvh7p1nx1aw6v649gw3rc` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (1806,0,'5511316138220570','452','03','Anna Baker','2020',1770),(1807,0,'5313694966604077','759','10','Pol Hernández','2022',1764);
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descriptor`
--

DROP TABLE IF EXISTS `descriptor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `descriptor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgfulfilmwi4hhaquiu7fr5g0g` (`job_id`),
  CONSTRAINT `FKgfulfilmwi4hhaquiu7fr5g0g` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descriptor`
--

LOCK TABLES `descriptor` WRITE;
/*!40000 ALTER TABLE `descriptor` DISABLE KEYS */;
INSERT INTO `descriptor` VALUES (1721,1,'Descriptor job 1',1729),(1722,1,'Descriptor job 2',1730),(1723,1,'Descriptor job 3',1731),(1724,1,'Descriptor job 4',1732),(1725,1,'Descriptor job 5',1733),(1726,1,'Descriptor job 6',1734),(1727,1,'Descriptor job 7',1735),(1728,1,'Descriptor job 8',1736);
/*!40000 ALTER TABLE `descriptor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `duty`
--

DROP TABLE IF EXISTS `duty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `duty` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `percentage_time_for_week` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `descriptor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3cc3garl37bl7gswreqwr7pj4` (`descriptor_id`),
  CONSTRAINT `FK3cc3garl37bl7gswreqwr7pj4` FOREIGN KEY (`descriptor_id`) REFERENCES `descriptor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duty`
--

LOCK TABLES `duty` WRITE;
/*!40000 ALTER TABLE `duty` DISABLE KEYS */;
INSERT INTO `duty` VALUES (1743,0,'Research and planning of the system to be developed',20,'Research and planning',1721),(1744,0,'Create a site architecture map to visually demonstrate how site content and structure is organized',30,'Site architecture and content',1721),(1745,0,'What is known as the Information Architecture (AI) of the web application is defined',55.75,'Front-end',1722),(1746,0,'Work with the logical part of an application',44.25,'Back-end',1722),(1747,0,'Define in as much detail as possible the tasks to be performed and the necessary resources',10,'Detailed planning of the work to be done',1723),(1748,0,'Establishment of the work environment, assignment of planned tasks to available resources, execution of planned tasks...',75.5,'Project execution',1723),(1749,0,'Execution of planned actions and a record of the evolution of the project',50.67,'D (Do)',1724),(1750,0,'Analysis of the evolution of the project, review of objectives, deadlines and resources and a record of conclusions',30.48,'C (Check)',1724),(1751,0,'Taking measures according to the conclusions drawn in the verification and recording of the measures',18.85,'A (Act)',1724),(1752,0,'Review the sources of secondary data we have and first determine the method of obtaining information',7.3,'Obtaining the information',1725),(1753,0,'Web design stage: typefaces, colors, templates...',30,'Design and construction',1721),(1754,0,'Identify if more income is being achieved than expenses, you can hire staff to delegate repetitive tasks',100,'Regular operation',1726),(1755,0,'Examination of the procedural details of the code to evaluate',50,'White box or structural techniques',1727),(1756,0,'Perform tests on the interface of the program to be tested, understanding by interface the inputs and outputs of said program',50,'Black box or functional techniques',1727),(1757,0,'Condition the finished product and make it available to the customer',60.89,'Order preparation',1728),(1758,0,'Quality control, stock tracking and shipment thereof',39.11,'Delivery',1728),(1759,0,'Test system, test functionality, requirements, unit tests ...',20,'Testing',1721),(1760,0,'Follow-up of planned tasks and milestones, management of deliverables (including quality control) and incidents, generation of follow-up reports',14.5,'Work monitoring and control',1723),(1761,0,'Data processing, usually by creating a database',78.2,'Data processing and analysis',1725),(1762,0,'The statistical information must be interpreted and, subsequently, a report will be prepared with recommendations on the measures to be taken to achieve the proposed objectives',14.5,'Interpretation and presentation of results',1725);
/*!40000 ALTER TABLE `duty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_na4dfobmeuxkwf6p75abmb2tr` (`user_account_id`),
  CONSTRAINT `FK_na4dfobmeuxkwf6p75abmb2tr` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` VALUES (1716,0,1715,'Feeding company, Inc.','Feeding'),(1719,0,1718,'Public function company, Inc.','Public function');
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1840);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor_record`
--

DROP TABLE IF EXISTS `investor_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investor_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `investor_name` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  `stars` double DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor_record`
--

LOCK TABLES `investor_record` WRITE;
/*!40000 ALTER TABLE `investor_record` DISABLE KEYS */;
INSERT INTO `investor_record` VALUES (1679,0,'Industry','Industrial',4,'We are interesting in tech development'),(1680,0,'Jobbing','Laboral',4.5,'We would like to invest in new jobs'),(1681,0,'YourMusic','Musical',5,'We are interesting in new artists'),(1682,0,'AllMusic','Musical',3.9,'Our interest is focused on the record companies');
/*!40000 ALTER TABLE `investor_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `salary_amount` double DEFAULT NULL,
  `salary_currency` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `employer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7jmfdvs0b0jx7i33qxgv22h7b` (`reference`),
  KEY `FK3rxjf8uh6fh2u990pe8i2at0e` (`employer_id`),
  CONSTRAINT `FK3rxjf8uh6fh2u990pe8i2at0e` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1729,0,'2030-12-11 22:59:00.000000','Job for the development of a web application','http://www.myDevelopment.com','EMP1-JOB1',15000.98,'€',1,'Web development',1716),(1730,0,'2019-12-11 17:30:00.000000','Job for updating a system\'s functionality','http://www.funcionalityUpdates.com','EMP1-JOB2',25500,'€',0,'Web functionality update',1716),(1731,0,'2010-12-11 22:59:00.000000','Project management assuming the role of Project Manager','http://www.yourManagement.com','EMP2-JOB3',15000.98,'€',1,'Project management',1719),(1732,0,'2018-12-11 22:59:00.000000','Computer programmer for an R and D project.','http://www.RandDProjects.com','EMP2-JOB4',15000.98,'€',0,'R and D project',1719),(1733,0,'2023-12-11 22:59:00.000000','Analyze the organization of the company and the profitability of each one of its areas of activity','http://www.businessAnalysts.com','EMP1-JOB5',15000.98,'€',1,'Business Analysis',1716),(1734,0,'2010-12-11 22:59:00.000000','Integrate as delegates to a company in the process of national expansion of training and networking services','http://www.entrepreneurship.com','EMP2-JOB6',15000.98,'€',1,'National expansion process of training and networking services',1719),(1735,0,'2018-12-11 22:59:00.000000','Perform unit tests of a system','http://www.unitTest.com','EMP2-JOB7',15000.98,'€',0,'Unitary testing',1719),(1736,0,'2023-12-11 22:59:00.000000','Receipt and distribution of merchandise in warehouse and order preparation','http://www.merchandiseDistribution.com','EMP1-JOB8',15000.98,'€',1,'Valet job',1716);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `message_thread_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn5adlx3oqjna7aupm8gwg3fuj` (`message_thread_id`),
  CONSTRAINT `FKn5adlx3oqjna7aupm8gwg3fuj` FOREIGN KEY (`message_thread_id`) REFERENCES `message_thread` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1784,0,'This challenge is too hard','2018-12-11 22:59:00.000000','Games, Challenges','Very hard challenge',1772),(1785,0,'This challenge is too easy','2017-12-23 21:37:00.000000','Games, Challenges','Very easy challenge',1772),(1786,0,'I don\'t think it\'s good','2018-10-31 12:50:00.000000','Discussion, Price','Disagreement',1773),(1787,0,'The price fits the quality','2018-01-07 19:00:00.000000','Discussion, Price','Good price',1773),(1788,0,'This challenge is affordable','2018-07-22 00:34:00.000000','Games, Challenges','Normal challenge',1772),(1789,0,'This job is so confortable','2018-10-01 09:03:00.000000','Discussion, Job','Confortable job',1774),(1790,0,'I think it\'s a tiring job','2018-12-11 22:59:00.000000','Discussion, Job','Tiring',1774),(1791,0,'Job very easy','2018-12-11 22:59:00.000000','Discussion, Job','Very easy',1774),(1792,0,'Search for offers made successfully','2018-12-11 22:59:00.000000','Searches','Successful search',1772),(1793,0,'Job search failed','2018-12-11 22:59:00.000000','Searches','Search failed',1772),(1794,0,'Problem finding investors','2017-05-14 08:59:00.000000','Discussion, Problems','Investors problem',1772),(1795,0,'Problem when choosing ads','2018-05-17 20:54:00.000000','Discussion, Problems','Ads problem',1773),(1796,0,'This challenge is hard','2018-04-11 12:29:00.000000','Games, Challenges','Very hard challenge',1772),(1797,0,'This challenge is a little expensive','2019-03-11 03:30:00.000000','Discussion, Price','A little expensive',1773),(1798,0,'This is mi desired job','2019-09-16 10:59:00.000000','Discussion, Job','Desired job',1774),(1799,0,'Search for new investors not found','2018-02-20 14:22:00.000000','Searches','Search not found',1773);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_thread`
--

DROP TABLE IF EXISTS `message_thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message_thread` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_thread`
--

LOCK TABLES `message_thread` WRITE;
/*!40000 ALTER TABLE `message_thread` DISABLE KEYS */;
INSERT INTO `message_thread` VALUES (1772,0,'2018-12-11 22:59:00.000000','Opinions about the challenge'),(1773,0,'2018-12-11 22:59:00.000000','Prices'),(1774,0,'2018-12-11 22:59:00.000000','Jobs');
/*!40000 ALTER TABLE `message_thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_thread_user_account`
--

DROP TABLE IF EXISTS `message_thread_user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message_thread_user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `message_thread_id` int(11) DEFAULT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtchis3o5qij98x87mty6hdk4d` (`message_thread_id`),
  KEY `FK5lulj1y29jm6k2b4mle9218ap` (`user_account_id`),
  CONSTRAINT `FK5lulj1y29jm6k2b4mle9218ap` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FKtchis3o5qij98x87mty6hdk4d` FOREIGN KEY (`message_thread_id`) REFERENCES `message_thread` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_thread_user_account`
--

LOCK TABLES `message_thread_user_account` WRITE;
/*!40000 ALTER TABLE `message_thread_user_account` DISABLE KEYS */;
INSERT INTO `message_thread_user_account` VALUES (1775,0,1772,1737),(1776,0,1773,1769),(1777,0,1773,1766),(1778,0,1773,1763),(1779,0,1773,1715),(1780,0,1774,1715),(1781,0,1774,1766),(1782,0,1774,1709),(1783,0,1774,1706);
/*!40000 ALTER TABLE `message_thread_user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `non_commercial_banner`
--

DROP TABLE IF EXISTS `non_commercial_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_commercial_banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `jingle` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpcpr0xb5k7s4rxv5pulstt5v9` (`sponsor_id`),
  CONSTRAINT `FKpcpr0xb5k7s4rxv5pulstt5v9` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_commercial_banner`
--

LOCK TABLES `non_commercial_banner` WRITE;
/*!40000 ALTER TABLE `non_commercial_banner` DISABLE KEYS */;
INSERT INTO `non_commercial_banner` VALUES (1700,1,'https://www.dropbox.com/s/58dds8plb13pl63/jingle1.mp3?dl=0','https://www.dropbox.com/s/if086iscy3jpgbq/banner1.jpg?dl=0','Non commercial banner 1','http://www.github.com/',1764),(1701,1,'https://www.dropbox.com/s/h7xwlbz4fbj7dak/jingle2.mp3?dl=0','https://www.dropbox.com/s/oa2ytfbgoprzh2h/banner2.jpg?dl=0','Non commercial banner 2','http://www.dropbox.com/',1764),(1702,1,'https://www.dropbox.com/s/t5hdokera5zxy29/jingle3.mp3?dl=0','https://www.dropbox.com/s/7eaa0ls7u84on5i/banner3.jpg?dl=0','Non commercial banner 3','http://www.ev.us.es/',1767),(1703,1,'https://www.dropbox.com/s/t5hdokera5zxy29/jingle3.mp3?dl=0','https://www.dropbox.com/s/7eaa0ls7u84on5i/banner3.jpg?dl=0','Non commercial banner 4','http://www.ev.us.es/',1767),(1704,1,'https://www.dropbox.com/s/t5hdokera5zxy29/jingle3.mp3?dl=0','https://www.dropbox.com/s/7eaa0ls7u84on5i/banner3.jpg?dl=0','Non commercial banner 5','http://www.ev.us.es/',1767),(1705,1,'https://www.dropbox.com/s/t5hdokera5zxy29/jingle3.mp3?dl=0','https://www.dropbox.com/s/7eaa0ls7u84on5i/banner3.jpg?dl=0','Non commercial banner 6','http://www.ev.us.es/',1770);
/*!40000 ALTER TABLE `non_commercial_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `reward_amount` double DEFAULT NULL,
  `reward_currency` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iex7e8fs0fh89yxpcnm1orjkm` (`ticker`),
  KEY `IDXcp4664f36sgqsd0ihmirt0w0` (`ticker`),
  KEY `IDXq2o9psuqfuqmq59f0sq57x9uf` (`deadline`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (1669,0,'2029-04-20 10:45:00.000000','Offer of books for 50% discount','2019-02-10 09:45:00.000000',300,'EUR','OXXXX-99999','Offer of books'),(1670,0,'2029-04-12 12:45:00.000000','Food offer for the first home deliverys','2019-03-20 09:45:00.000000',200.5,'EUR','OAXXX-99999','Offer of food');
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` (`user_account_id`),
  CONSTRAINT `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `reward_amount` double DEFAULT NULL,
  `reward_currency` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9mxq3powq8tqctclj0fbi2nih` (`ticker`),
  KEY `IDXh9syauj4iixf18uts83saik5d` (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1666,0,'2020-05-22 22:00:00.000000','2019-06-10 09:39:00.000000',2000,'EUR','Request for the deployment of a new web page','RASDF-12345','Web request'),(1667,0,'2019-12-28 02:09:00.000000','2019-03-13 20:47:00.000000',1400,'EUR','Request for sending 50 packages via mail','RGHJK-67890','Comercial request'),(1668,0,'2020-01-01 12:00:00.000000','2018-10-10 16:20:00.000000',3600,'EUR','Request for the creation of a new database','RLZXC-52684','Storage request');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spam_word`
--

DROP TABLE IF EXISTS `spam_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spam_word` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `english_translation` varchar(255) DEFAULT NULL,
  `spam_threshold` double DEFAULT NULL,
  `spanish_translation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spam_word`
--

LOCK TABLES `spam_word` WRITE;
/*!40000 ALTER TABLE `spam_word` DISABLE KEYS */;
INSERT INTO `spam_word` VALUES (1683,0,'sex',50,'sexo'),(1684,0,'hard core',15,'hard core'),(1685,0,'viagra',30,'viagra'),(1686,0,'cialis',70,'cialis'),(1687,0,'nigeria',90,'nigeria'),(1688,0,'you´ve won',10,'has ganado'),(1689,0,'million dollar',80,'millón de dólares');
/*!40000 ALTER TABLE `spam_word` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `organisation_name` varchar(255) DEFAULT NULL,
  `credit_card_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28mvxtnmfjcwiw34vs8ryqkpa` (`credit_card_id`),
  KEY `FK_20xk0ev32hlg96kqynl6laie2` (`user_account_id`),
  CONSTRAINT `FK28mvxtnmfjcwiw34vs8ryqkpa` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`),
  CONSTRAINT `FK_20xk0ev32hlg96kqynl6laie2` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES (1764,1,1763,'Pepsico and NFL',1807),(1767,0,1766,'Ted y Rolex',NULL),(1770,1,1769,'TIFF y L’Oréal',1806);
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `identity_email` varchar(255) DEFAULT NULL,
  `identity_name` varchar(255) DEFAULT NULL,
  `identity_surname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,0,_binary '\0','john.doe@acme.com','John','Doe','$2a$05$q9uDjqJjDKyAy1vj1p27uO7B/yYBbyBm3xiGdIVPFx6NEDLfTjyxy','anonymous'),(3,0,_binary '','john.doe@acme.com','John','Doe','$2a$05$ltKPyPykoChrmj.cfSdREubMsMkG9f3PizEkRIdV8LvgloGltTRgW','authenticated'),(5,0,_binary '','administrator@acme.com','Administrator','Acme.com','$2a$05$/pBILjKtFCVg/2MK74f9nO5qa8hhoC6oCvWhWz0Tfcdtr3ucAUdc6','administrator'),(1706,0,_binary '','worker1@acme.com','Worker','One','$2a$05$YbT2J6YMYAfoYnz0ytK3XeKtmLPOwJ.3A8f1e5tWNocDWv18SH4B6','worker1'),(1709,0,_binary '','worker2@acme.com','Worker','Two','$2a$05$ibUztUCotejjq3EWRBDgv.527tJgVvHFQ.ZsH2F9iGx09SOwVo9Ye','worker2'),(1712,0,_binary '','worker3@acme.com','Worker','Three','$2a$05$zLr3cYpfQUpRs766uggTUeGDRD/q0wuSErkE1f9y.Ll3Q5/ifNWy6','worker3'),(1715,0,_binary '','employer1@acme.com','Employer','One','$2a$05$9Kgtm20LZVl67W80807X3O3DiFR2iR71VrTxqEP80l3rtuxsDuqEq','employer1'),(1718,0,_binary '','employer2@acme.com','Employer','Two','$2a$05$pjOvHX29puYgubQt1p.IjuRgvi.FaIkJGSpSRZ7P/0hSvRKlOwnhK','employer2'),(1737,0,_binary '','auditor1@gmail.com','Auditor','One','$2a$05$8wzUq0VHb2OqQ5N30ZxlteCvlM9Mwk7L.cHX0ynIakIGqYxGnEG6u','auditor1'),(1740,0,_binary '','auditor2@gmail.com','Auditor','Two','$2a$05$igH8vE6Y2Y.hCWXT5zmbguPF2LxbaSjNcvB3wnX0/2ZoNc0jX5daW','auditor2'),(1763,0,_binary '','sponsor1@gmail.com','Sponsor','One','$2a$05$xqamHIIgvwiK6sOGlw9Rh.gJXmrxqH4uYJ76srHIj5AsgxBEUT/3u','sponsor1'),(1766,0,_binary '','sponsor2@gmail.com','Sponsor','Two','$2a$05$zkj6aMTqc/2UEXZns5gFtep3FEnvfnEtstjciyYmfW67025maZht2','sponsor2'),(1769,0,_binary '','sponsor3@gmail.com','Sponsor','Three','$2a$05$29CCG7T4mQ8YlJyIV6qFTO6qgHQMUY03Ap2vjC9zfEKGOnxxPLiqm','sponsor3');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l5q1f33vs2drypmbdhpdgwfv3` (`user_account_id`),
  CONSTRAINT `FK_l5q1f33vs2drypmbdhpdgwfv3` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (1707,0,1706,'Degree in web programming','PHP and java knowledge'),(1710,0,1709,'Informatics Engineering','Ability to plan and execute any project'),(1713,0,1712,'Degree in business management and administration','Management and business management capacity. Ability to work autonomously, as well as in a team and expert level of Office tools management');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-18 21:02:57
