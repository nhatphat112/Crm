-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: crm
-- ------------------------------------------------------
-- Server version	8.0.32
create database if not exists crm;
use crm;
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

-- --
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `jobname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2368 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (2,'Thiết kế font-end'),(4,'Job Test'),(5,NULL),(6,NULL),(2275,'ok 3334'),(2287,'ok 3334'),(2291,'ok 3334'),(2293,'ok 3334'),(2297,'ok 3334'),(2298,''),(2299,'test3'),(2300,'Test 02'),(2301,'Tạo dự án'),(2302,'Quản lí hệ thống'),(2303,'Phát triển tính năng tìm kiếm'),(2304,'Tìm kiếm dữ liệu khách hàng'),(2310,'ok 3334 NVO'),(2312,'ok 3334 NVA'),(2313,'ok 3334'),(2314,'ok 333'),(2315,'ok 333'),(2317,'ok 33312'),(2318,'Test 1111ok'),(2321,'ok 333'),(2322,'ok 333'),(2323,'ok 333'),(2324,'ok 333'),(2325,'ok 333'),(2326,'ok 333'),(2327,'ok 333'),(2328,'ok 333'),(2329,'ok 333'),(2330,'ok 333'),(2331,'ok 333'),(2332,'ok 333'),(2333,'ok 333'),(2334,'ok 333'),(2336,'Test NVKS'),(2337,'ok 333'),(2338,'Test NVKS'),(2339,'Test'),(2341,'Test NVKS 1'),(2342,'Test NVKS 2'),(2344,'Test 122'),(2345,'Test NVKSS'),(2346,'Test PTHHMT 12'),(2348,'Thiết kế database	'),(2349,'123123'),(2350,'123123'),(2351,'Cong viec danh cho nguyen van a'),(2352,'Cong viec danh cho nguyen van l'),(2353,'Test CV NVB'),(2355,'Phân tích cơ sở dữ liệu'),(2356,'Phân tích cơ sở dữ liệu'),(2357,'Dựng cơ sở dữ liệu'),(2358,'Phân task dự án'),(2359,'Khởi tạo dữ án'),(2362,'Khởi tạo dự án'),(2364,'Cong viec danh cho nguyen van e'),(2365,'Cong viec danh cho nguyen van e'),(2367,'Cong viec danh cho NVE');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `projectname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `datebegin` timestamp NULL DEFAULT NULL,
  `dateend` timestamp NULL DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_project_user_id` (`user_id`),
  CONSTRAINT `FK_project_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (46,'Phát triển hệ thống','2021-02-22 15:19:05','2022-02-22 15:19:05',68),(47,'Dự Án CRM','2021-02-22 15:19:35','2023-02-22 15:19:35',68);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN','Quản trị hệ thống'),(2,'ROLE_MANAGER','Quản lý'),(3,'ROLE_USER','Thành Viên');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `statusname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Chưa thực hiện',NULL),(2,'Đang thực hiện',NULL),(3,'Đã hoàn thành',NULL);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `id` int NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `user_id` int NOT NULL,
  `job_id` int NOT NULL,
  `status_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` varchar(24) DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `country` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Users_role_id` (`role_id`),
  CONSTRAINT `FK_Users_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (66,'ADMIN','123456789',NULL,'admin@gmail.com','$2a$10$J.otk9dH9Cf5soJ2eMfZCeHnJjbXdopXZF3XCqMly8PhhRCbWtTKy',1,NULL,'Usa'),(67,'Nguyen Van A','123456789',NULL,'nguyenvana@gmail.com','$2a$10$mHzvQpoS3CA65RyuluP0neExCcBoW4Pi2XM2I300SrJJt41oblw.e',2,NULL,'India'),(68,'Nguyen Van B','123456789',NULL,'nguyenvanB@gmail.com','$2a$10$HbzenzPBT5WEYxueFwFTrORjWEoUxHrugT/V60vFoZcEcbuIroi5S',2,NULL,'Usa'),(69,'Nguyen Van C','123456789',NULL,'nguyenvanC@gmail.com','$2a$10$sDdksZ.l0VdGvYMLF8ICVuekN2WkeFI4iSZKAYJ/2eV3cIz.EDPUa',3,NULL,'Usa'),(70,'Nguyen Van D','123456789',NULL,'nguyenvand@gmail.com','$2a$10$fs7/2ZnDPzNX6UlAFNSez.hczIANEFFM/C8HoCvhjyZm7/GNLFlGy',3,NULL,'Usa'),(73,'Nguyen Van E','123456789',NULL,'nguyenvane@gmail.com','$2a$10$Z7fMUQfCTceqErjsbkadLeYPoa4GCo/z4870Av3QmEjkNtVNzNB62',3,NULL,'Usa');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workon`
--

DROP TABLE IF EXISTS `workon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workon` (
  `user_id` bigint NOT NULL,
  `project_id` bigint NOT NULL,
  `job_id` bigint NOT NULL,
  `status_id` bigint NOT NULL,
  `datebegin` timestamp NULL DEFAULT NULL,
  `dateend` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`,`project_id`,`job_id`,`status_id`),
  KEY `FK_workon_project_id` (`project_id`),
  KEY `FK_workon_job_id` (`job_id`),
  KEY `FK_workon_status_id` (`status_id`),
  CONSTRAINT `FK_workon_job_id` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_workon_project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_workon_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_workon_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workon`
--

LOCK TABLES `workon` WRITE;
/*!40000 ALTER TABLE `workon` DISABLE KEYS */;
INSERT INTO `workon` VALUES (66,47,2359,3,'2021-02-22 00:00:00','2022-02-22 00:00:00'),(68,46,2355,1,'2021-02-22 00:00:00','2022-02-22 00:00:00'),(68,47,2358,3,'2021-02-22 00:00:00','2022-02-22 00:00:00'),(69,46,2356,1,'2021-02-22 00:00:00','2022-02-22 00:00:00'),(70,46,2357,1,'2021-02-22 00:00:00','2022-02-22 00:00:00'),(73,47,2365,3,'2021-02-22 00:00:00','2022-02-22 00:00:00');
/*!40000 ALTER TABLE `workon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-24  7:38:14
