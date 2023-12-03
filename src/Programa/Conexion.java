package Programa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class Conexion {
	
	private static Connection conexion;
	static String puerto= MySQL.p;
	static String usuario= MySQL.u;
	static String contraseña= MySQL.c;
	static int existebasededatos=0;
	static int existeconexion=0;
	File f;
	FileReader lectorarchivo;
	

	public static Connection obtener_conexion(){
		
		puerto= MySQL.p;
		usuario= MySQL.u;
		contraseña= MySQL.c;
		
		
		try {
			
			
			conexion= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/easyflashcapture", usuario, contraseña);
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error de conectar la base de datos :(");
		}
		
		return conexion;
	}
	
	public static Connection conexion(){
		
		puerto= MySQL.p;
		usuario= MySQL.u;
		contraseña= MySQL.c;
		
		
		try {
			
			
			conexion= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:"+ puerto+ "/", usuario, contraseña);
			existeconexion=1;
			
			Conexion a= new Conexion();
			String sql=a.leer("easyflashcapture.sql");
			//System.out.print(""+sql);
			
			PreparedStatement ab= conexion.prepareStatement("create database easyflashcapture;");
			PreparedStatement ac= conexion.prepareStatement("use easyflashcapture");
			PreparedStatement ad= conexion.prepareStatement("CREATE TABLE `alumnos` ( `idAlumnos` int(15) NOT NULL,`Nombre` varchar(45) NOT NULL,`Apellido_paterno` varchar(45) NOT NULL,`Apellido_materno` varchar(45) NOT NULL, `Correo_electronico` varchar(45) NOT NULL,`Numero_tel_padres` bigint(30) NOT NULL,`Grupos_idGrupos` varchar(5) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			PreparedStatement ae= conexion.prepareStatement("CREATE TABLE `calificaciones_alumno` (`Alumnos_idAlumnos` int(15) NOT NULL,`Alumnos_Grupos_idGrupos` varchar(5) NOT NULL,`Cursos_idCursos` varchar(30) NOT NULL,`Cursos_Maestros_idMaestros` int(11) NOT NULL,`Cursos_Salon_idSalon` varchar(10) NOT NULL,`Califi_1_parcial` int(20) NOT NULL,`Califi_2_parcial` int(20) NOT NULL,`Califi_3_parcial` int(20) NOT NULL,`Promedio_final` int(30) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			PreparedStatement af= conexion.prepareStatement("CREATE TABLE `cursos` (`idCursos` varchar(30) NOT NULL,`Maestros_idMaestros` int(11) NOT NULL,`Salon_idSalon` varchar(10) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			PreparedStatement ag= conexion.prepareStatement("CREATE TABLE `grupos` (`idGrupos` varchar(5) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			PreparedStatement ah= conexion.prepareStatement("CREATE TABLE `maestros` (`idMaestros` int(15) NOT NULL,`Nombre` varchar(45) NOT NULL,`Apellido_paterno` varchar(45) NOT NULL,`Apellido_materno` varchar(45) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			PreparedStatement ai= conexion.prepareStatement("CREATE TABLE `salon` (`idSalon` varchar(10) NOT NULL,`Edificio` varchar(45) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			PreparedStatement aj= conexion.prepareStatement("CREATE TABLE `usuario` (`idUsuario` varchar(30) NOT NULL,`Contraseï¿½a` varchar(45) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			PreparedStatement ak= conexion.prepareStatement("INSERT INTO `usuario` (`idUsuario`, `Contraseï¿½a`) VALUES('root', 'root');");
			PreparedStatement al= conexion.prepareStatement("ALTER TABLE `alumnos`ADD PRIMARY KEY (`idAlumnos`,`Grupos_idGrupos`),ADD KEY `fk_Alumnos_Grupos1_idx` (`Grupos_idGrupos`);");
			PreparedStatement am= conexion.prepareStatement("ALTER TABLE `calificaciones_alumno`ADD PRIMARY KEY (`Alumnos_idAlumnos`,`Alumnos_Grupos_idGrupos`,`Cursos_idCursos`,`Cursos_Maestros_idMaestros`,`Cursos_Salon_idSalon`),ADD KEY `fk_Calificaciones_alumno_Cursos1_idx` (`Cursos_idCursos`,`Cursos_Maestros_idMaestros`,`Cursos_Salon_idSalon`);");
			PreparedStatement an= conexion.prepareStatement("ALTER TABLE `cursos`ADD PRIMARY KEY (`idCursos`,`Maestros_idMaestros`,`Salon_idSalon`),ADD KEY `fk_Cursos_Maestros1_idx` (`Maestros_idMaestros`),ADD KEY `fk_Cursos_Salon1_idx` (`Salon_idSalon`);");
			PreparedStatement añ= conexion.prepareStatement("ALTER TABLE `grupos`ADD PRIMARY KEY (`idGrupos`);");
			PreparedStatement ao= conexion.prepareStatement("ALTER TABLE `maestros`ADD PRIMARY KEY (`idMaestros`);");
			PreparedStatement ap= conexion.prepareStatement("ALTER TABLE `salon`ADD PRIMARY KEY (`idSalon`);");
			PreparedStatement aq= conexion.prepareStatement("ALTER TABLE `usuario`ADD PRIMARY KEY (`idUsuario`);");
			PreparedStatement ar= conexion.prepareStatement("ALTER TABLE `maestros`MODIFY `idMaestros` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;");
			PreparedStatement as= conexion.prepareStatement("ALTER TABLE `alumnos`ADD CONSTRAINT `fk_Alumnos_Grupos1` FOREIGN KEY (`Grupos_idGrupos`) REFERENCES `grupos` (`idGrupos`) ON DELETE CASCADE ON UPDATE CASCADE;");
			PreparedStatement at= conexion.prepareStatement("ALTER TABLE `calificaciones_alumno`ADD CONSTRAINT `fk_Calificaciones_alumno_Alumnos1` FOREIGN KEY (`Alumnos_idAlumnos`,`Alumnos_Grupos_idGrupos`) REFERENCES `alumnos` (`idAlumnos`, `Grupos_idGrupos`) ON DELETE CASCADE ON UPDATE CASCADE,ADD CONSTRAINT `fk_Calificaciones_alumno_Cursos1` FOREIGN KEY (`Cursos_idCursos`,`Cursos_Maestros_idMaestros`,`Cursos_Salon_idSalon`) REFERENCES `cursos` (`idCursos`, `Maestros_idMaestros`, `Salon_idSalon`) ON DELETE CASCADE ON UPDATE CASCADE;");
			PreparedStatement au= conexion.prepareStatement("ALTER TABLE `cursos`ADD CONSTRAINT `fk_Cursos_Maestros1` FOREIGN KEY (`Maestros_idMaestros`) REFERENCES `maestros` (`idMaestros`) ON DELETE CASCADE ON UPDATE CASCADE,ADD CONSTRAINT `fk_Cursos_Salon1` FOREIGN KEY (`Salon_idSalon`) REFERENCES `salon` (`idSalon`) ON DELETE CASCADE ON UPDATE CASCADE;");
			PreparedStatement av= conexion.prepareStatement("COMMIT;");
			
			ab.executeUpdate();
			ac.executeUpdate();
			ad.executeUpdate();
			ae.executeUpdate();
			af.executeUpdate();
			ag.executeUpdate();
			ah.executeUpdate();
			ai.executeUpdate();
			aj.executeUpdate();
			ak.executeUpdate();
			al.executeUpdate();
			am.executeUpdate();
			an.executeUpdate();
			añ.executeUpdate();
			ao.executeUpdate();
			ap.executeUpdate();
			aq.executeUpdate();
			ar.executeUpdate();
			as.executeUpdate();
			at.executeUpdate();
			au.executeUpdate();
			av.executeUpdate();
			
		}catch(Exception e) {
			e.getStackTrace();
			if(conexion==null){
				JOptionPane.showMessageDialog(null, "Error de conectar la base de datos :(");
			}else{
				Contraseña frame = new Contraseña();
				frame.setVisible(true);
				existebasededatos=1;
			}
			
		}
		
		return conexion;
	}
	
	
	
	public String leer(String nombre){
		try{
			f= new File(nombre);
			lectorarchivo= new FileReader(f);
			BufferedReader br= new BufferedReader (lectorarchivo);
			String cadena="";
			String aux="";
			while(true){
				aux=br.readLine();
				if(aux!=null){
					cadena=cadena+aux+"\n";
				}else{
					break;
				}
			}
			br.close();
			lectorarchivo.close();
			return cadena;
		}catch(IOException e){
			
		}
		return null;
		
	}
	
	
}


