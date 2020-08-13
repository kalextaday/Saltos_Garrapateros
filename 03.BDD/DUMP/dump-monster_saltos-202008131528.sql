-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: monster_saltos
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
-- Table structure for table `aeronave`
--

DROP TABLE IF EXISTS `aeronave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aeronave` (
  `ID_AERONAVE` int NOT NULL AUTO_INCREMENT,
  `NOMBREAER` varchar(100) DEFAULT NULL,
  `MODELOAER` varchar(14) DEFAULT NULL,
  `CAPACIDADTOTALAER` int DEFAULT NULL,
  PRIMARY KEY (`ID_AERONAVE`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aeronave`
--

LOCK TABLES `aeronave` WRITE;
/*!40000 ALTER TABLE `aeronave` DISABLE KEYS */;
INSERT INTO `aeronave` VALUES (1,'cesnna ','206',6),(16,'boeing','737',10),(17,'airbus','g-e',8);
/*!40000 ALTER TABLE `aeronave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `ID_CLIENTE` int NOT NULL AUTO_INCREMENT,
  `NOMBRESCLI` varchar(250) DEFAULT NULL,
  `APELLIDOSCLI` varchar(250) DEFAULT NULL,
  `CEDULACLI` varchar(10) DEFAULT NULL,
  `FECHANACIMIENTOCLI` timestamp NULL DEFAULT NULL,
  `CORREOCLI` varchar(100) DEFAULT NULL,
  `TELFCLI` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descuento`
--

DROP TABLE IF EXISTS `descuento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `descuento` (
  `ID_DESCUENTO` int NOT NULL AUTO_INCREMENT,
  `NOMBREDES` varchar(100) DEFAULT NULL,
  `DESCRIPCIONDES` varchar(250) DEFAULT NULL,
  `VALORDES` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`ID_DESCUENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descuento`
--

LOCK TABLES `descuento` WRITE;
/*!40000 ALTER TABLE `descuento` DISABLE KEYS */;
/*!40000 ALTER TABLE `descuento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_vuelo`
--

DROP TABLE IF EXISTS `detalle_vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_vuelo` (
  `ID_DETVUELO` int NOT NULL AUTO_INCREMENT,
  `PEDIDO_ID` int DEFAULT NULL,
  `VUELO_ID` int DEFAULT NULL,
  `COSTOTOTAL` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`ID_DETVUELO`),
  KEY `FK_LLEVA` (`VUELO_ID`),
  KEY `FK_REGISTRA` (`PEDIDO_ID`),
  CONSTRAINT `FK_LLEVA` FOREIGN KEY (`VUELO_ID`) REFERENCES `vuelo` (`ID_VUELO`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_REGISTRA` FOREIGN KEY (`PEDIDO_ID`) REFERENCES `pedido` (`ID_PEDIDO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_vuelo`
--

LOCK TABLES `detalle_vuelo` WRITE;
/*!40000 ALTER TABLE `detalle_vuelo` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_vuelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `ID_EMPLEADO` int NOT NULL AUTO_INCREMENT,
  `NOMBRESEMP` varchar(250) DEFAULT NULL,
  `APELLIDOSEMP` varchar(250) DEFAULT NULL,
  `CEDULAEMP` varchar(10) DEFAULT NULL,
  `TELFEMP` varchar(10) DEFAULT NULL,
  `CORREOEMP` varchar(100) DEFAULT NULL,
  `CARGOEMP` varchar(50) DEFAULT NULL,
  `FECHANACIMIENTO` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_EMPLEADO`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (2,'Kevin','Taday','1726213976','3070301','kataday@gmail.com','cajero','2020-08-03 05:00:00'),(4,'a','sdasd','asd','sd','sd','sd',NULL);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formacion_paracaidista`
--

DROP TABLE IF EXISTS `formacion_paracaidista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formacion_paracaidista` (
  `ID_FORPAR` int NOT NULL AUTO_INCREMENT,
  `NOMBRECURSO` varchar(100) DEFAULT NULL,
  `FECHAINICIOCURSO` timestamp NULL DEFAULT NULL,
  `FECHAFINCURSO` timestamp NULL DEFAULT NULL,
  `LUGARCURSO` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID_FORPAR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formacion_paracaidista`
--

LOCK TABLES `formacion_paracaidista` WRITE;
/*!40000 ALTER TABLE `formacion_paracaidista` DISABLE KEYS */;
/*!40000 ALTER TABLE `formacion_paracaidista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paracaidista`
--

DROP TABLE IF EXISTS `paracaidista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paracaidista` (
  `ID_PARACAIDISTA` int NOT NULL AUTO_INCREMENT,
  `FORPAR_ID` int DEFAULT NULL,
  `NOMBRESPAR` varchar(250) DEFAULT NULL,
  `APELLIDOSPAR` varchar(250) DEFAULT NULL,
  `TIPOPAR` varchar(32) DEFAULT NULL,
  `ESCUELAFORMACION` varchar(100) DEFAULT NULL,
  `NUMEROSALTOS` int DEFAULT NULL,
  `FECHAULTIMOSALTO` timestamp NULL DEFAULT NULL,
  `LICENCIA` tinyint(1) DEFAULT NULL,
  `FECHACADUCIDADLICENCIA` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_PARACAIDISTA`),
  KEY `FK_CURSO` (`FORPAR_ID`),
  CONSTRAINT `FK_CURSO` FOREIGN KEY (`FORPAR_ID`) REFERENCES `formacion_paracaidista` (`ID_FORPAR`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paracaidista`
--

LOCK TABLES `paracaidista` WRITE;
/*!40000 ALTER TABLE `paracaidista` DISABLE KEYS */;
/*!40000 ALTER TABLE `paracaidista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `ID_PEDIDO` int NOT NULL AUTO_INCREMENT,
  `CLIENTE_ID` int DEFAULT NULL,
  `SALTO_ID` int DEFAULT NULL,
  `PARACAIDISTA_ID` int DEFAULT NULL,
  `DESCUENTO_ID` int DEFAULT NULL,
  `SERVICIO_ID` int DEFAULT NULL,
  `FECHAPED` timestamp NULL DEFAULT NULL,
  `COSTOSALTO` decimal(6,2) DEFAULT NULL,
  `COSTOTOTALPED` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`ID_PEDIDO`),
  KEY `FK_PUEDE_AYUDAR` (`PARACAIDISTA_ID`),
  KEY `FK_PUEDE_TENER` (`DESCUENTO_ID`),
  KEY `FK_REALIZA` (`CLIENTE_ID`),
  KEY `FK_SOLICITA_UN` (`SALTO_ID`),
  KEY `FK_TIENE` (`SERVICIO_ID`),
  CONSTRAINT `FK_PUEDE_AYUDAR` FOREIGN KEY (`PARACAIDISTA_ID`) REFERENCES `paracaidista` (`ID_PARACAIDISTA`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PUEDE_TENER` FOREIGN KEY (`DESCUENTO_ID`) REFERENCES `descuento` (`ID_DESCUENTO`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_REALIZA` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `cliente` (`ID_CLIENTE`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_SOLICITA_UN` FOREIGN KEY (`SALTO_ID`) REFERENCES `salto` (`ID_SALTO`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_TIENE` FOREIGN KEY (`SERVICIO_ID`) REFERENCES `servicio_adicional` (`ID_SERVICIO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `ID_PERFIL` int NOT NULL AUTO_INCREMENT,
  `NOMBREPER` varchar(64) DEFAULT NULL,
  `ESTADOPER` char(3) DEFAULT NULL,
  `ESTADOPER2` char(3) DEFAULT NULL,
  `FECHACREACIONPER` timestamp NULL DEFAULT NULL,
  `FECHAMODPER` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_PERFIL`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'Administrador','ACT','ADM',NULL,'2020-08-03 05:00:00'),(2,'Usuario','ACT','USR',NULL,'2020-08-05 05:00:00'),(3,'Invitado','ACT','INV',NULL,'2020-08-02 05:00:00');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso`
--

DROP TABLE IF EXISTS `permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permiso` (
  `ID_PERMISO` int NOT NULL AUTO_INCREMENT,
  `RECURSO_ID` int DEFAULT NULL,
  `PERFIL_ID` int DEFAULT NULL,
  `NOMBREPER` varchar(50) DEFAULT NULL,
  `ESTADOPER` char(3) DEFAULT NULL,
  PRIMARY KEY (`ID_PERMISO`),
  KEY `FK_AUTORIZA` (`PERFIL_ID`),
  KEY `FK_PERMISO_RECURSO` (`RECURSO_ID`),
  CONSTRAINT `FK_AUTORIZA` FOREIGN KEY (`PERFIL_ID`) REFERENCES `perfil` (`ID_PERFIL`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PERMISO_RECURSO` FOREIGN KEY (`RECURSO_ID`) REFERENCES `recurso` (`ID_RECURSO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso`
--

LOCK TABLES `permiso` WRITE;
/*!40000 ALTER TABLE `permiso` DISABLE KEYS */;
INSERT INTO `permiso` VALUES (1,1,1,'Administrador - Gestion Aeronaves','ACT'),(2,2,1,'Administrador - Gestion Clientes','ACT'),(3,6,1,'Administrador - Gestion Descuentos','ACT'),(4,8,1,'Administrador - Gestion Empleados','ACT'),(5,10,1,'Administrador - Gestion Paracaidistas','ACT'),(6,11,1,'Administrador - Gestion Pedidos','ACT'),(7,13,1,'Administrador - Gestion Perfiles','ACT'),(8,15,1,'Administrador - Gestion Permisos','ACT'),(9,17,1,'Administrador - Gestion Pilotos','ACT'),(10,19,1,'Administrador - Gestion Recursos','ACT'),(11,20,1,'Administrador - Gestion Saltos','ACT'),(12,26,2,'Usuario - Registrar Clientes','ACT'),(13,27,2,'Usuario - Ver Detalle Vuelos','ACT'),(14,28,2,'Usuario - Registrar Descuentos','ACT'),(15,29,2,'Usuario - Registrar Pedidos','ACT'),(16,26,1,'Administrador -Registrar Clientes','ACT'),(17,27,1,'Administrador - Ver Detalle Vuelos','ACT'),(18,23,1,'Administrador - Gestion Usuarios','ACT'),(19,24,1,'Administrador - Gestion Vuelos','ACT'),(20,31,1,'Administrador - Asignar Recursos','ACT');
/*!40000 ALTER TABLE `permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piloto`
--

DROP TABLE IF EXISTS `piloto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piloto` (
  `ID_PILOTO` int NOT NULL AUTO_INCREMENT,
  `NOMBRESPIL` varchar(200) DEFAULT NULL,
  `CEDULAPIL` varchar(10) DEFAULT NULL,
  `TELFPIL` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID_PILOTO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piloto`
--

LOCK TABLES `piloto` WRITE;
/*!40000 ALTER TABLE `piloto` DISABLE KEYS */;
INSERT INTO `piloto` VALUES (2,'qweqwe','qweqwe','ewewew');
/*!40000 ALTER TABLE `piloto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recurso`
--

DROP TABLE IF EXISTS `recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recurso` (
  `ID_RECURSO` int NOT NULL AUTO_INCREMENT,
  `NOMBREREC` varchar(128) DEFAULT NULL,
  `PAGINAREC` varchar(250) DEFAULT NULL,
  `ICONOREC` varchar(32) DEFAULT NULL,
  `INDICEREC` int DEFAULT NULL,
  `ESTADOREC` char(3) DEFAULT NULL,
  PRIMARY KEY (`ID_RECURSO`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
INSERT INTO `recurso` VALUES (1,'Gestion de Aeronaves','/app/gestion-aeronaves.xhtml',NULL,1000,'ADM'),(2,'Gestion de Clientes','/app/gestion-clientes.xhtml',NULL,900,'ADM'),(6,'Gestion de Descuentos','/app/gestion-descuentos.xhtml',NULL,1200,'ADM'),(8,'Gestion de Empleados','/app/gestion-empleados.xhtml',NULL,100,'ADM'),(10,'Gestion de Paracaidistas','/app/gestion-paracaidistas.xhtml',NULL,700,'ADM'),(11,'Gestion de Pedidos','/app/gestion-pedidos.xhtml',NULL,1400,'ADM'),(13,'Gestion de Perfiles','/app/gestion-perfiles.xhtml',NULL,200,'ADM'),(15,'Gestion de Permisos','/app/gestion-permisos.xhtml',NULL,500,'ADM'),(17,'Gestion de Pilotos','/app/gestion-pilotos.xhtml',NULL,600,'ADM'),(19,'Gestion de Recursos','/app/gestion-recursos.xhtml',NULL,300,'ADM'),(20,'Gestion de Saltos','/app/gestion-saltos.xhtml',NULL,1100,'ADM'),(22,'Gestion de Servicios Adicionales','/app/gestion-servicios-adicionales.xhtml',NULL,800,'ADM'),(23,'Gestion de Usuarios','/app/gestion-usuarios.xhtml',NULL,400,'ADM'),(24,'Gestion de Vuelos','/app/gestion-vuelos.xhtml',NULL,1300,'ADM'),(25,'Gestion de Detalle de Vuelos','/app/gestion-detalles-vuelos.xhtml',NULL,1500,'ADM'),(26,'Registrar Clientes','/app/registrar-clientes.xhtml',NULL,1600,'USR'),(27,'Ver Detalle de Vuelos','/app/ver-detalles-vuelos.xhtml',NULL,1700,'USR'),(28,'Registrar Descuentos','/app/registrar-descuentos.xhtml',NULL,1800,'USR'),(29,'Registrar Pedidos','/app/registrar-pedidos.xhtml',NULL,1900,'USR'),(30,'Gestion Servicios Adicionales','/app/gestion-servicios-adicionales.xhtml',NULL,2000,'ADM'),(31,'Asignar Recursos','/app/asignar-recursos.xhtml',NULL,2100,'ADM');
/*!40000 ALTER TABLE `recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsable` (
  `ID_RESPONSABLE` int NOT NULL AUTO_INCREMENT,
  `NOMBRESRES` varchar(100) DEFAULT NULL,
  `CEDULARES` varchar(10) DEFAULT NULL,
  `EMAILRES` varchar(100) DEFAULT NULL,
  `TELFRES` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID_RESPONSABLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsable`
--

LOCK TABLES `responsable` WRITE;
/*!40000 ALTER TABLE `responsable` DISABLE KEYS */;
/*!40000 ALTER TABLE `responsable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salto`
--

DROP TABLE IF EXISTS `salto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salto` (
  `ID_SALTO` int NOT NULL AUTO_INCREMENT,
  `TIPOSALTO` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_SALTO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salto`
--

LOCK TABLES `salto` WRITE;
/*!40000 ALTER TABLE `salto` DISABLE KEYS */;
INSERT INTO `salto` VALUES (1,'Salto Libre'),(2,'Tandem');
/*!40000 ALTER TABLE `salto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio_adicional`
--

DROP TABLE IF EXISTS `servicio_adicional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio_adicional` (
  `ID_SERVICIO` int NOT NULL AUTO_INCREMENT,
  `RESPONSABLE_ID` int DEFAULT NULL,
  `NOMBRESER` varchar(100) DEFAULT NULL,
  `DESCRIPCIONSER` varchar(250) DEFAULT NULL,
  `COSTOSER` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`ID_SERVICIO`),
  KEY `FK_SE_ENCARGA` (`RESPONSABLE_ID`),
  CONSTRAINT `FK_SE_ENCARGA` FOREIGN KEY (`RESPONSABLE_ID`) REFERENCES `responsable` (`ID_RESPONSABLE`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio_adicional`
--

LOCK TABLES `servicio_adicional` WRITE;
/*!40000 ALTER TABLE `servicio_adicional` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio_adicional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `ID_USUARIO` int NOT NULL AUTO_INCREMENT,
  `EMPLEADO_ID` int DEFAULT NULL,
  `PERFIL_ID` int DEFAULT NULL,
  `NOMBREUSUARIO` varchar(100) DEFAULT NULL,
  `CLAVEUSU` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  KEY `FK_NECESARIAMENTE` (`PERFIL_ID`),
  KEY `FK_PODRA_TENER` (`EMPLEADO_ID`),
  CONSTRAINT `FK_NECESARIAMENTE` FOREIGN KEY (`PERFIL_ID`) REFERENCES `perfil` (`ID_PERFIL`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PODRA_TENER` FOREIGN KEY (`EMPLEADO_ID`) REFERENCES `empleado` (`ID_EMPLEADO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,2,1,'ka','12345'),(2,2,2,'user','12345'),(4,4,3,'invi','12345');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelo`
--

DROP TABLE IF EXISTS `vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelo` (
  `ID_VUELO` int NOT NULL AUTO_INCREMENT,
  `AERONAVE_ID` int DEFAULT NULL,
  `PILOTO_ID` int DEFAULT NULL,
  `HORASALIDA` timestamp NULL DEFAULT NULL,
  `HORALLEGADA` timestamp NULL DEFAULT NULL,
  `CAPACIDADPERSONAS` int DEFAULT NULL,
  PRIMARY KEY (`ID_VUELO`),
  KEY `FK_CONDUCE` (`PILOTO_ID`),
  KEY `FK_REALIZA_UN` (`AERONAVE_ID`),
  CONSTRAINT `FK_CONDUCE` FOREIGN KEY (`PILOTO_ID`) REFERENCES `piloto` (`ID_PILOTO`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_REALIZA_UN` FOREIGN KEY (`AERONAVE_ID`) REFERENCES `aeronave` (`ID_AERONAVE`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelo`
--

LOCK TABLES `vuelo` WRITE;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
INSERT INTO `vuelo` VALUES (1,NULL,NULL,'2020-08-03 05:00:00','2020-08-10 05:00:00',3),(2,1,2,'2020-08-04 05:00:00','2020-08-30 05:00:00',3);
/*!40000 ALTER TABLE `vuelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'monster_saltos'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-13 15:28:39
