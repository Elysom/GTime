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


-- Volcando estructura de base de datos para admin2
CREATE DATABASE IF NOT EXISTS `admin2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `admin2`;

-- Volcando estructura para tabla admin2.plan_academico
CREATE TABLE IF NOT EXISTS `plan_academico` (
  `IDPlan` int(11) NOT NULL AUTO_INCREMENT,
  `IDUsuario` int(11) NOT NULL,
  `nombrePlan` varchar(200) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `color` varchar(7) NOT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `asignatura` varchar(20) NOT NULL,
  `curso` varchar(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDPlan`),
  KEY `fk_PLANACADEMICO_USUARIO` (`IDUsuario`),
  CONSTRAINT `fk_PLANACADEMICO_USUARIO` FOREIGN KEY (`IDUsuario`) REFERENCES `usuario` (`IDusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla admin2.plan_academico: ~11 rows (aproximadamente)
REPLACE INTO `plan_academico` (`IDPlan`, `IDUsuario`, `nombrePlan`, `fecha`, `color`, `tipo`, `asignatura`, `curso`, `descripcion`) VALUES
	(18, 1, 'Examen Aplicacion Ofimatica', '2024-11-29 12:00:00', '#FFFFFF', 'Examen', 'APLOF', '1.º SMR', 'Examen Final Aplof'),
	(19, 1, 'Examen BD', '2024-11-19 13:00:00', '#4D001A', 'Examen', 'BD', '1.º DAM', 'Examen'),
	(21, 1, 'ejemplo', '2024-11-27 14:14:00', '#FF8080', 'Cursos', 'ASO', '2.º SMR', 'mwqdf'),
	(22, 1, 'a', '2024-11-07 10:00:00', '#FF9980', 'Cursos', 'FOL', '1.º DAM', 'aaaaa'),
	(23, 1, 'saas', '2024-11-07 00:00:00', '#FFCCE6', 'Excursion', 'PMDM', '2.º DAM', 'asd'),
	(24, 1, 'ejemplo', '2024-11-20 15:10:00', '#FFFFFF', 'Proyecto', 'PRO', '1.º DAM', '2'),
	(25, 1, 'gimnasia', '2024-11-27 09:10:00', '#FFFFFF', 'Excursion', 'BD', '1.º DAM', 'aaaaaaaaaaaaas'),
	(26, 1, 'Salida De Convivencia', '2024-11-30 10:00:00', '#FFFFFF', 'Excursion', 'MOMAE', '1.º SMR', 'Salida al parque maria luisa donde habra juegos '),
	(27, 1, 'a', '2024-11-30 00:00:00', '#FFFFFF', 'Proyecto', 'ED', '1.º DAM', 'Primera línea\\nSegunda línea'),
	(28, 1, 'a', '2024-11-27 16:00:00', '#FFFFFF', 'Proyecto', 'ED', '1.º DAM', 'hola\na\ntodos'),
	(29, 1, 'ejemplo30', '2024-11-29 00:00:00', '#FFFFFF', 'Proyecto', 'SGE', '2.º DAM', 'hola a todos hoy \nejemplo 30');

-- Volcando estructura para tabla admin2.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `IDusuario` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(40) NOT NULL,
  `nombreReal` varchar(40) NOT NULL,
  `apellidos` varchar(90) NOT NULL,
  `contrasenia` varchar(30) NOT NULL,
  `curso` varchar(20) NOT NULL,
  `fotoPerfil` blob DEFAULT NULL,
  `nombreUsuario` varchar(90) NOT NULL,
  PRIMARY KEY (`IDusuario`),
  UNIQUE KEY `nombreUsuario` (`nombreUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla admin2.usuario: ~0 rows (aproximadamente)
REPLACE INTO `usuario` (`IDusuario`, `mail`, `nombreReal`, `apellidos`, `contrasenia`, `curso`, `fotoPerfil`, `nombreUsuario`) VALUES
	(1, 'admin@gtime.com', 'Pepita', 'Perez', 'Contrasenia12.', '2.º SMR', NULL, 'admin2');


-- Volcando estructura de base de datos para listausuarios
CREATE DATABASE IF NOT EXISTS `listausuarios` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `listausuarios`;

-- Volcando estructura para tabla listausuarios.plan_academico
CREATE TABLE IF NOT EXISTS `plan_academico` (
  `IDPlan` int(11) NOT NULL AUTO_INCREMENT,
  `nombrePlan` varchar(200) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `color` varchar(7) NOT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `asignatura` varchar(20) NOT NULL,
  `curso` varchar(20) NOT NULL,
  `descripcion` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDPlan`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla listausuarios.plan_academico: ~9 rows (aproximadamente)
REPLACE INTO `plan_academico` (`IDPlan`, `nombrePlan`, `fecha`, `color`, `tipo`, `asignatura`, `curso`, `descripcion`) VALUES
	(1, 'a', '2024-11-06 14:00:00', '#FFFFFF', 'Excursion', 'LMSGI', '1.º DAM', 'aaaa'),
	(2, 'Examen Aplicacion Ofimatica', '2024-11-29 12:00:00', '#FFFFFF', 'Examen', 'APLOF', '1.º SMR', 'Examen Final Aplof'),
	(3, 'Examen BD', '2024-11-19 13:00:00', '#4D001A', 'Examen', 'BD', '1.º DAM', 'Examen'),
	(4, 'ejemplo', '2024-11-27 14:14:00', '#FF8080', 'Cursos', 'ASO', '2.º SMR', 'mwqdf'),
	(5, 'a', '2024-11-07 10:00:00', '#FF9980', 'Cursos', 'FOL', '1.º DAM', 'aaaaa'),
	(6, 'saas', '2024-11-07 00:00:00', '#FFCCE6', 'Excursion', 'PMDM', '2.º DAM', 'asd'),
	(7, 'ejemplo', '2024-11-20 15:10:00', '#FFFFFF', 'Proyecto', 'PRO', '1.º DAM', '2'),
	(8, 'gimnasia', '2024-11-27 09:10:00', '#FFFFFF', 'Excursion', 'BD', '1.º DAM', 'aaaaaaaaaaaaas'),
	(9, 'a', '2024-11-27 16:00:00', '#FFFFFF', 'Proyecto', 'ED', '1.º DAM', 'hola\na\ntodos');

-- Volcando estructura para tabla listausuarios.usuarioslista
CREATE TABLE IF NOT EXISTS `usuarioslista` (
  `IDusuario` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(40) NOT NULL,
  `nombreReal` varchar(40) NOT NULL,
  `apellidos` varchar(90) NOT NULL,
  `contrasenia` varchar(30) NOT NULL,
  `nombreUsuario` varchar(90) NOT NULL,
  `tipo` varchar(90) DEFAULT NULL,
  `curso` varchar(20) NOT NULL,
  PRIMARY KEY (`IDusuario`),
  UNIQUE KEY `nombreUsuario` (`nombreUsuario`),
  CONSTRAINT `chk_tipo` CHECK (`tipo` in ('Administrador','Usuario')),
  CONSTRAINT `chk_mail` CHECK (`mail` like '%@gtime.com'),
  CONSTRAINT `chk_contrasenia` CHECK (octet_length(`contrasenia`) >= 8 and `contrasenia` regexp '[A-Z]' and `contrasenia` regexp '[a-z]' and `contrasenia` regexp '[0-9]' and `contrasenia` regexp '[^A-Za-z0-9]')
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla listausuarios.usuarioslista: ~4 rows (aproximadamente)
REPLACE INTO `usuarioslista` (`IDusuario`, `mail`, `nombreReal`, `apellidos`, `contrasenia`, `nombreUsuario`, `tipo`, `curso`) VALUES
	(7, 'pikachu@gtime.com', 'pikachu', 'pikachu', 'Pikachu12.', 'pikachu', NULL, ''),
	(11, 'a@gtime.com', 'a', 'a', 'Contrasenia12.', 'a', NULL, '1.º SMR'),
	(12, 'admin@gtime.com', 'Pepita', 'Perez', 'Contrasenia12.', 'admin2', 'Administrador', '2.º SMR');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
