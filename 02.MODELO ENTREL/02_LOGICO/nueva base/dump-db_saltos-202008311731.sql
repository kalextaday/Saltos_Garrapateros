-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: db_saltos
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `opcion`
--

DROP TABLE IF EXISTS `opcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opcion` (
  `ID_OPCION` int NOT NULL AUTO_INCREMENT,
  `ID_OPCION_PADRE` int DEFAULT NULL,
  `OPC_NOMBRE` varchar(128) DEFAULT NULL,
  `OPC_DESCRIPCION` varchar(256) DEFAULT NULL,
  `OPC_PAGINA` varchar(128) DEFAULT NULL,
  `OPC_ICONO` varchar(32) DEFAULT NULL,
  `OPC_INDEX` int DEFAULT NULL,
  `OPC_ESTATUS` char(3) DEFAULT NULL,
  PRIMARY KEY (`ID_OPCION`),
  KEY `FK_RECURSO_PADRE` (`ID_OPCION_PADRE`),
  CONSTRAINT `FK_RECURSO_PADRE` FOREIGN KEY (`ID_OPCION_PADRE`) REFERENCES `opcion` (`ID_OPCION`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcion`
--

LOCK TABLES `opcion` WRITE;
/*!40000 ALTER TABLE `opcion` DISABLE KEYS */;
INSERT INTO `opcion` VALUES (1,NULL,'Origen','','NOURL','fa fa-user-secret',9,'ORG'),(2,1,'Administracion','','NORUL','fa fa-bars',20,'MOD'),(3,2,'Administrar Permisos',NULL,'NOURL','fa fa-bars',400,'MOD'),(4,3,'Asignar Permisos',NULL,'/seg/asignar-permisos','fa fa-link',100,'APP'),(5,3,'Visor de Permisos',NULL,'/seg/visor-permisos','fa fa-eye',200,'APP'),(6,2,'Administrar Perfiles',NULL,'NOURL','fa fa-bars',500,'MOD'),(7,6,'Gestionar Perfiles',NULL,'/seg/super-perfil','fa fa-university',100,'APP'),(8,6,'Asignar Perfiles',NULL,'/seg/asignar-perfiles','fa fa-users',200,'APP'),(9,6,'Ver Usuarios Por Perfil',NULL,'/seg/ver-usuarios-perfil','fa fa-eye',300,'APP'),(10,2,'Administrar Personas',NULL,'NOURL','fa fa-bars',100,'MOD'),(11,10,'Gestionar Personas',NULL,'/seg/super-persona','fa fa-university',1000,'APP'),(12,10,'Agregar Personas',NULL,'/seg/agregar-persona','fa fa-user-plus',2000,'APP'),(13,2,'Administrar Opciones',NULL,'NOURL','fa fa-bars',200,'MOD'),(14,13,'Gestionar Opciones',NULL,'/seg/super-opcion','fa fa-university',100,'APP'),(15,2,'Administrar Usuarios',NULL,'NOURL','fa fa-bars',300,'MOD'),(16,15,'Gestionar Usuarios',NULL,'/seg/super-usuario-acceso','fa fa-university',100,'APP'),(17,15,'Agregar Usuarios',NULL,'/seg/agregar-usuario','fa fa-user-plus',200,'APP'),(18,15,'Configuración de Clave',NULL,'/usr/pagina-construccion','fa fa-sliders',300,'APP'),(19,2,'Bitacora',NULL,'NOURL','fa fa-bars',600,'MOD'),(20,19,'Revisar Todos los Logs',NULL,'/usr/pagina-construccion','fa fa-database',100,'APP'),(21,19,'Revisar Logs Por Usuario',NULL,'/usr/pagina-construccion','fa fa-user',200,'APP');
/*!40000 ALTER TABLE `opcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcion_perfil`
--

DROP TABLE IF EXISTS `opcion_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opcion_perfil` (
  `ID_PERMISO` int NOT NULL AUTO_INCREMENT,
  `ID_PERFIL` int DEFAULT NULL,
  `ID_OPCION` int DEFAULT NULL,
  `OPC_PER_ESTATUS` char(3) DEFAULT NULL,
  PRIMARY KEY (`ID_PERMISO`),
  KEY `FK_PERFIL_PERMISOS` (`ID_PERFIL`),
  KEY `FK_RECURSO_PERMISOS` (`ID_OPCION`),
  CONSTRAINT `FK_PERFIL_PERMISOS` FOREIGN KEY (`ID_PERFIL`) REFERENCES `perfil` (`ID_PERFIL`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_RECURSO_PERMISOS` FOREIGN KEY (`ID_OPCION`) REFERENCES `opcion` (`ID_OPCION`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcion_perfil`
--

LOCK TABLES `opcion_perfil` WRITE;
/*!40000 ALTER TABLE `opcion_perfil` DISABLE KEYS */;
INSERT INTO `opcion_perfil` VALUES (1,1,1,'OPA'),(2,1,2,'OPA'),(3,1,3,'OPA'),(4,1,4,'OPA'),(5,1,5,'OPA'),(6,1,6,'OPA'),(7,1,7,'OPA'),(8,1,8,'OPA'),(9,1,9,'OPA'),(10,1,10,'OPA'),(11,1,11,'OPA'),(12,1,12,'OPA'),(13,1,13,'OPA'),(14,1,14,'OPA'),(15,1,15,'OPA'),(16,1,16,'OPA'),(17,1,17,'OPA'),(18,1,18,'OPA'),(19,1,19,'OPA'),(20,1,20,'OPA'),(21,1,21,'OPA');
/*!40000 ALTER TABLE `opcion_perfil` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-31 17:31:39
