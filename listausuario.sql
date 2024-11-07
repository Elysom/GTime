-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.32-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para listausuarios
CREATE DATABASE IF NOT EXISTS `listausuarios` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `listausuarios`;

-- Volcando estructura para tabla listausuarios.usuarioslista
CREATE TABLE IF NOT EXISTS `usuarioslista` (
  `IDusuario` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(40) NOT NULL,
  `nombreReal` varchar(40) NOT NULL,
  `apellidos` varchar(90) NOT NULL,
  `contrasenia` varchar(30) NOT NULL,
  `nombreUsuario` varchar(90) NOT NULL,
  `tipo` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`IDusuario`),
  UNIQUE KEY `nombreUsuario` (`nombreUsuario`),
  CONSTRAINT `chk_tipo` CHECK (`tipo` in ('Administrador','Usuario')),
  CONSTRAINT `chk_mail` CHECK (`mail` like '%@gtime.com'),
  CONSTRAINT `chk_contrasenia` CHECK (octet_length(`contrasenia`) >= 8 and `contrasenia` regexp '[A-Z]' and `contrasenia` regexp '[a-z]' and `contrasenia` regexp '[0-9]' and `contrasenia` regexp '[^A-Za-z0-9]')
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
