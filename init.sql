/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `contacts` varchar(128) DEFAULT NULL,
  `status` varchar(128) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (12,'╨Ю╨▒╨╡╨┤','╨Ъ╤Г╨┐╨╕╤В╤М ╨║╨░╨┐╤Г╤Б╤В╤Г','2021-11-27 14:16:00','','ACTIVE'),(13,'╨Р╨╜╨│╨╗','╨У╤А╨░╨╝╨╝╨░╤В╨╕╨║╨░','2021-11-28 21:42:00','','COMPLETED'),(15,'╨г╨╢╨╕╨╜','╨Ч╨░╨║╨░╨╖╨░╤В╤М ╨┐╨╕╤Ж╤Ж╤Л ╨╕ ╤А╨╛╨╗╨╗╨╛╨▓','2021-11-28 21:42:00','89063056130','EXPIRED'),(19,'hbnj','dsaf','2021-11-27 14:15:00','','ACTIVE'),(21,'╨▓╤Ж╤Д╨░╤Л','╨▓╨░╤Л╨┐╨▓','2021-11-27 14:15:00','','EXPIRED'),(22,'wdafegtrh','fegrth','2021-11-27 14:15:00','','ACTIVE'),(24,'╨Ь╨░╨│╨░╨╖╨╕╨╜','╨Ъ╤Г╨┐╨╕╤В╤М ╤Е╨╗╨╡╨▒╤Г╤И╨╡╨║','2021-11-28 20:30:00','89063056130','COMPLETED'),(26,'╨в╨╡╨║╤Б╤В','╤Д╨▓╤Д╨▓╤Д╤Л╨▓╤Д','2021-11-27 14:15:00','','ACTIVE'),(30,'╨б╤В╨╕╤А╨║╨░','╨а╨░╨╖╨▓╨╡╤Б╨╕╤В╤М ╨▓╨╡╤Й╨╕','2021-11-27 14:15:00','╨║╨╗╤О╤З','ACTIVE'),(33,'╨в╨╡╨║╤Б╤В','╨Т╤Л╨▓╨╛╨┤ ╤В╨╡╨║╤Б╤В╨░ ╨▓ ╤Д╨░╨╣╨╗╤Л','2021-11-28 21:49:00','','ACTIVE'),(34,'╨б╤В╨╕╤А╨║╨░','╨Т╨╡╤Й╨╕','1111-11-11 11:11:00','','EXPIRED');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-04  2:35:58
