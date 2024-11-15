-- Crear base de datos listausuarios
CREATE DATABASE `listausuarios` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `listausuarios`;

-- Crear tabla plan_academico
CREATE TABLE `plan_academico` (
  `IDPlan` int(11) NOT NULL AUTO_INCREMENT,
  `nombrePlan` varchar(200) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `color` varchar(7) NOT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `asignatura` varchar(20) NOT NULL,
  `curso` varchar(20) NOT NULL,
  `descripcion` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDPlan`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Crear tabla usuarioslista
CREATE TABLE `usuarioslista` (
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

-- Insertar datos en usuarioslista
INSERT INTO `usuarioslista` (IDusuario, mail, nombreReal, apellidos, contrasenia, nombreUsuario, tipo, curso) 
VALUES 
(7, 'pikachu@gtime.com', 'pikachu', 'pikachu', 'Pikachu12.', 'pikachu', NULL, ''),
(11, 'a@gtime.com', 'a', 'a', 'Contrasenia12.', 'a', NULL, '1.º SMR'),
(12, 'admin@gtime.com', 'Pepita', 'Perez', 'Contrasenia12.', 'admin2', 'Administrador', '2.º SMR');

-- Insertar datos en plan_academico
INSERT INTO `plan_academico` (IDPlan, nombrePlan, fecha, color, tipo, asignatura, curso, descripcion) 
VALUES 
(1, 'a', '2024-11-06 14:00:00.000', '#FFFFFF', 'Excursion', 'LMSGI', '1.º DAM', 'aaaa');

-- Crear base de datos admin2
CREATE DATABASE `admin2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `admin2`;

-- Crear tabla usuario en admin2
CREATE TABLE `usuario` (
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

-- Crear tabla plan_academico en admin2
CREATE TABLE `plan_academico` (
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Insertar datos en plan_academico y usuario en admin2
INSERT INTO `plan_academico` (IDPlan, IDUsuario, nombrePlan, fecha, color, tipo, asignatura, curso, descripcion) 
VALUES 
(1, 1, 'a', '2024-11-13 18:08:00.000', '#FF9999', 'Excursion', 'EOI', '2.º SMR', NULL),
(2, 1, 'ea', '2024-11-13 18:08:00.000', '#FF9999', 'Excursion', 'EOI', '2.º SMR', NULL);

INSERT INTO `usuario` (IDusuario, mail, nombreReal, apellidos, contrasenia, curso, fotoPerfil, nombreUsuario) 
VALUES 
(1, 'admin@gtime.com', 'Pepita', 'Perez', 'Contrasenia12.', '2.º SMR', NULL, 'admin2');

-- Crear base de datos pikachu
CREATE DATABASE `pikachu` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `pikachu`;

-- Crear tablas en pikachu
CREATE TABLE `usuario` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `plan_academico` (
  `IDPlan` int(11) NOT NULL AUTO_INCREMENT,
  `IDUsuario` int(11) NOT NULL,
  `nombrePlan` varchar(200) DEFAULT NULL,
  `horasPlan` varchar(7) DEFAULT NULL,
  `diasSemanas` enum('Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo') DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `color` varchar(7) NOT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `asignatura` varchar(20) NOT NULL,
  `curso` varchar(20) NOT NULL,
  PRIMARY KEY (`IDPlan`),
  KEY `fk_PLANACADEMICO_USUARIO` (`IDUsuario`),
  CONSTRAINT `fk_PLANACADEMICO_USUARIO` FOREIGN KEY (`IDUsuario`) REFERENCES `usuario` (`IDusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tarea` (
  `IDTarea` int(11) NOT NULL AUTO_INCREMENT,
  `IDUsuario` int(11) NOT NULL,
  `nombreTarea` varchar(100) NOT NULL,
  `fecha` datetime NOT NULL,
  `color` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDTarea`),
  KEY `fk_tarea_usuario` (`IDUsuario`),
  CONSTRAINT `fk_tarea_usuario` FOREIGN KEY (`IDUsuario`) REFERENCES `usuario` (`IDusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Crear base de datos a
CREATE DATABASE `a` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `a`;

-- Crear tablas en a
CREATE TABLE `plan_academico` (
  `IDPlan` int(11) NOT NULL AUTO_INCREMENT,
  `IDUsuario` int(11) NOT NULL,
  `nombrePlan` varchar(200) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `color` varchar(7) NOT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `asignatura` varchar(20) NOT NULL,
  `curso` varchar(20) NOT NULL,
  PRIMARY KEY (`IDPlan`),
  KEY `fk_PLANACADEMICO_USUARIO` (`IDUsuario`),
  CONSTRAINT `fk_PLANACADEMICO_USUARIO` FOREIGN KEY (`IDUsuario`) REFERENCES `usuario` (`IDusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tarea` (
  `IDTarea` int(11) NOT NULL AUTO_INCREMENT,
  `IDUsuario` int(11) NOT NULL,
  `nombreTarea` varchar(100) NOT NULL,
  `fecha` datetime NOT NULL,
  `color` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDTarea`),
  KEY `fk_tarea_usuario` (`IDUsuario`),
  CONSTRAINT `fk_tarea_usuario` FOREIGN KEY (`IDUsuario`) REFERENCES `usuario` (`IDusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario` (
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

-- Insertar datos en usuario de la base de datos a
INSERT INTO `usuario` (IDusuario, mail, nombreReal, apellidos, contrasenia, curso, fotoPerfil, nombreUsuario) 
VALUES (1, 'a@gtime.com', 'a', 'a', 'Contrasenia12.', '1.º SMR', NULL, 'a');
