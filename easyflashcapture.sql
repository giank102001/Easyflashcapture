
create database easyflashcapture;
use  easyflashcapture;


CREATE TABLE `alumnos` (
  `idAlumnos` int(15) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido_paterno` varchar(45) NOT NULL,
  `Apellido_materno` varchar(45) NOT NULL,
  `Correo_electronico` varchar(45) NOT NULL,
  `Numero_tel_padres` bigint(30) NOT NULL,
  `Grupos_idGrupos` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `calificaciones_alumno` (
  `Alumnos_idAlumnos` int(15) NOT NULL,
  `Alumnos_Grupos_idGrupos` varchar(5) NOT NULL,
  `Cursos_idCursos` varchar(30) NOT NULL,
  `Cursos_Maestros_idMaestros` int(11) NOT NULL,
  `Cursos_Salon_idSalon` varchar(10) NOT NULL,
  `Califi_1_parcial` int(20) NOT NULL,
  `Califi_2_parcial` int(20) NOT NULL,
  `Califi_3_parcial` int(20) NOT NULL,
  `Promedio_final` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `cursos` (
  `idCursos` varchar(30) NOT NULL,
  `Maestros_idMaestros` int(11) NOT NULL,
  `Salon_idSalon` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `grupos` (
  `idGrupos` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `maestros` (
  `idMaestros` int(15) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido_paterno` varchar(45) NOT NULL,
  `Apellido_materno` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `salon` (
  `idSalon` varchar(10) NOT NULL,
  `Edificio` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `usuario` (
  `idUsuario` varchar(30) NOT NULL,
  `Contraseña` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `usuario` (`idUsuario`, `Contraseña`) VALUES
('root', 'root');

ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`idAlumnos`,`Grupos_idGrupos`),
  ADD KEY `fk_Alumnos_Grupos1_idx` (`Grupos_idGrupos`);


ALTER TABLE `calificaciones_alumno`
  ADD PRIMARY KEY (`Alumnos_idAlumnos`,`Alumnos_Grupos_idGrupos`,`Cursos_idCursos`,`Cursos_Maestros_idMaestros`,`Cursos_Salon_idSalon`),
  ADD KEY `fk_Calificaciones_alumno_Cursos1_idx` (`Cursos_idCursos`,`Cursos_Maestros_idMaestros`,`Cursos_Salon_idSalon`);


ALTER TABLE `cursos`
  ADD PRIMARY KEY (`idCursos`,`Maestros_idMaestros`,`Salon_idSalon`),
  ADD KEY `fk_Cursos_Maestros1_idx` (`Maestros_idMaestros`),
  ADD KEY `fk_Cursos_Salon1_idx` (`Salon_idSalon`);

ALTER TABLE `grupos`
  ADD PRIMARY KEY (`idGrupos`);


ALTER TABLE `maestros`
  ADD PRIMARY KEY (`idMaestros`);


ALTER TABLE `salon`
  ADD PRIMARY KEY (`idSalon`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

ALTER TABLE `maestros`
  MODIFY `idMaestros` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE `alumnos`
  ADD CONSTRAINT `fk_Alumnos_Grupos1` FOREIGN KEY (`Grupos_idGrupos`) REFERENCES `grupos` (`idGrupos`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `calificaciones_alumno`
  ADD CONSTRAINT `fk_Calificaciones_alumno_Alumnos1` FOREIGN KEY (`Alumnos_idAlumnos`,`Alumnos_Grupos_idGrupos`) REFERENCES `alumnos` (`idAlumnos`, `Grupos_idGrupos`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Calificaciones_alumno_Cursos1` FOREIGN KEY (`Cursos_idCursos`,`Cursos_Maestros_idMaestros`,`Cursos_Salon_idSalon`) REFERENCES `cursos` (`idCursos`, `Maestros_idMaestros`, `Salon_idSalon`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `cursos`
  ADD CONSTRAINT `fk_Cursos_Maestros1` FOREIGN KEY (`Maestros_idMaestros`) REFERENCES `maestros` (`idMaestros`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Cursos_Salon1` FOREIGN KEY (`Salon_idSalon`) REFERENCES `salon` (`idSalon`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;


