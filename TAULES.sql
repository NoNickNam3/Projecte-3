CREATE TABLE `ubicaciones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `coordenada` varchar(100) NOT NULL,
  `observaciones` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `organizacion` int(10) unsigned DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `contraseña` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `usuario_FK` (`organizacion`),
  CONSTRAINT `usuario_FK` FOREIGN KEY (`organizacion`) REFERENCES `usuario` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `ubicaciones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `coordenada` varchar(100) DEFAULT NULL,
  `observaciones` varchar(300) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `rutas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usuario` int(10) unsigned NOT NULL,
  `momento_temporal` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `observacion` varchar(500) DEFAULT NULL,
  `fav` tinyint(1) DEFAULT 0,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rutas_FK` (`usuario`),
  CONSTRAINT `rutas_FK` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `puntos_de_ruta` (
  `ruta` int(10) unsigned NOT NULL,
  `coordenada` varchar(100) NOT NULL,
  `ordre` int(10) unsigned NOT NULL,
  `completado` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ruta`,`ordre`),
  CONSTRAINT `puntos_de_ruta_FK` FOREIGN KEY (`ruta`) REFERENCES `rutas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tracking` (
  `empleado` int(10) unsigned NOT NULL,
  `momento` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `coordenadas` float NOT NULL,
  PRIMARY KEY (`empleado`,`momento`),
  CONSTRAINT `tracking_FK` FOREIGN KEY (`empleado`) REFERENCES `usuario` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

