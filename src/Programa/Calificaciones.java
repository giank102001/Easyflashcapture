package Programa;

import java.awt.BorderLayout;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;

public class Calificaciones extends JFrame implements ActionListener{

	private java.sql.Connection conexion;
	private JTextField alumno;
	private JButton enviar, regresar,calculo,modificar,eliminar,limpiar,buscar;
	private JTextField grupo;
	private JTextField curso;
	private JTextField maestro;
	private JTextField salon;
	private JLabel lblCorreo;
	private JTextField cal1;
	private JLabel lblTelefonoDePadres;
	private JLabel lblParaPoderLlenar;
	private JTextField cali2;
	private JLabel lblGrupo_1;
	private JTextField cali3;
	private JTextField promfinal;
	private JTable tabladatosalumnos;
	JScrollPane scrollalumnos;
	private JTable tabladatoscursos;
	JScrollPane scrollcursos;
	private JTable tabladatos;
	JScrollPane scroll;
	private JTextField textxid;
	ResultSet rs;
	private JTextField textx2id;
	
	
	public Calificaciones() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.RED);
		setTitle("Calificaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1107, 650);
		setLocationRelativeTo(null);
		
		
		JLabel lblGrupo = new JLabel("Identificacionn del Alumno:");
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(0, 67, 225, 26);
		getContentPane().add(lblGrupo);
		
		alumno = new JTextField();
		alumno.setBounds(211, 70, 62, 25);
		alumno.setColumns(10);
		getContentPane().add(alumno);
		
		enviar = new JButton("Enviar Datos");
		enviar.setFont(new Font("Tahoma", Font.BOLD, 10));
		enviar.setBounds(495, 407, 129, 45);
		enviar.addActionListener(this);
		getContentPane().add(enviar);
		
		
		regresar = new JButton("Regresar al Menu");
		regresar.setFont(new Font("Tahoma", Font.BOLD, 9));
		regresar.setBounds(952, 555, 129, 45);
		regresar.addActionListener(this);
		getContentPane().add(regresar);
		
		JLabel lblNiombreDelMaestro = new JLabel("Grupo en donde esta el alumno:");
		lblNiombreDelMaestro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNiombreDelMaestro.setBounds(0, 102, 235, 38);
		getContentPane().add(lblNiombreDelMaestro);
		
		grupo = new JTextField();
		grupo.setColumns(10);
		grupo.setBounds(242, 111, 94, 25);
		getContentPane().add(grupo);
		
		JLabel lblApellidoPaterno = new JLabel("Curso donde esta el alumno:");
		lblApellidoPaterno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoPaterno.setBounds(0, 312, 215, 26);
		getContentPane().add(lblApellidoPaterno);
		
		curso = new JTextField();
		curso.setColumns(10);
		curso.setBounds(223, 312, 164, 25);
		getContentPane().add(curso);
		
		JLabel lblApellidoMaterno = new JLabel("Identificacion del maestro que da el curso:");
		lblApellidoMaterno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoMaterno.setBounds(0, 349, 336, 26);
		getContentPane().add(lblApellidoMaterno);
		
		maestro = new JTextField();
		maestro.setColumns(10);
		maestro.setBounds(318, 352, 68, 25);
		getContentPane().add(maestro);
		
		salon = new JTextField();
		salon.setColumns(10);
		salon.setBounds(245, 394, 105, 25);
		getContentPane().add(salon);
		
		lblCorreo = new JLabel("Salon donde se imparte el curso:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCorreo.setBounds(0, 391, 275, 26);
		getContentPane().add(lblCorreo);
		
		cal1 = new JTextField();
		cal1.setColumns(10);
		cal1.setBounds(699, 14, 111, 25);
		getContentPane().add(cal1);
		
		lblTelefonoDePadres = new JLabel("Calificacion 1\u00B0Parcial:");
		lblTelefonoDePadres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefonoDePadres.setBounds(512, 11, 186, 26);
		getContentPane().add(lblTelefonoDePadres);
		
		lblParaPoderLlenar = new JLabel("<html>Para poder llenar estos campos primero debes llenar sus tablas <br>conforme a la tabla de abajo</html>");
		lblParaPoderLlenar.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblParaPoderLlenar.setForeground(Color.CYAN);
		lblParaPoderLlenar.setBounds(5, 254, 381, 53);
		getContentPane().add(lblParaPoderLlenar);
		
		cali2 = new JTextField();
		cali2.setColumns(10);
		cali2.setBounds(699, 57, 111, 25);
		getContentPane().add(cali2);
		
		lblGrupo_1 = new JLabel("Calificacion 2\u00B0Parcial:");
		lblGrupo_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo_1.setBounds(512, 54, 173, 26);
		getContentPane().add(lblGrupo_1);
		
		cali3 = new JTextField();
		cali3.setColumns(10);
		cali3.setBounds(699, 93, 111, 25);
		getContentPane().add(cali3);
		
		JLabel lblCalificacionparcial = new JLabel("Calificacion 3\u00B0Parcial:");
		lblCalificacionparcial.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCalificacionparcial.setBounds(512, 90, 173, 26);
		getContentPane().add(lblCalificacionparcial);
		
		JLabel lblPromedioFinal = new JLabel("Promedio Final:");
		lblPromedioFinal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPromedioFinal.setBounds(556, 193, 142, 38);
		getContentPane().add(lblPromedioFinal);
		
		promfinal = new JTextField();
		promfinal.setColumns(10);
		promfinal.setBounds(699, 202, 111, 25);
		getContentPane().add(promfinal);
		
		calculo = new JButton("Calcular promedio final");
		calculo.addActionListener(this);
		calculo.setBounds(594, 145, 197, 37);
		getContentPane().add(calculo);
		
		tabladatosalumnos = new JTable();
		tabladatosalumnos.setBounds(5, 5, 141, 50);
		
		scrollalumnos= new JScrollPane();
		scrollalumnos.setBounds(10, 145, 326, 98);
		scrollalumnos.setViewportView(tabladatosalumnos);
		getContentPane().add(scrollalumnos);
		
		JLabel label = new JLabel("<html>Para poder llenar estos campos primero debes llenar sus tablas <br>conforme a la tabla de abajo</html>");
		label.setForeground(Color.CYAN);
		label.setFont(new Font("Tahoma", Font.ITALIC, 14));
		label.setBounds(6, 3, 381, 53);
		getContentPane().add(label);
		
		tabladatoscursos = new JTable();
		tabladatoscursos.setBounds(5, 5, 141, 50);
		
		scrollcursos= new JScrollPane();
		scrollcursos.setBounds(10, 430, 345, 156);
		scrollcursos.setViewportView(tabladatoscursos);
		getContentPane().add(scrollcursos);
		
		tabladatos = new JTable();
		tabladatos.setBounds(5, 5, 141, 50);
		
		scroll= new JScrollPane();
		scroll.setBounds(480, 240, 601, 156);
		scroll.setViewportView(tabladatos);
		getContentPane().add(scroll);
		
		eliminar = new JButton("Eliminar");
		eliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		eliminar.setBounds(681, 407, 129, 45);
		eliminar.addActionListener(this);
		getContentPane().add(eliminar);
		
		modificar = new JButton("Actualizar");
		modificar.setFont(new Font("Tahoma", Font.BOLD, 10));
		modificar.setBounds(495, 476, 129, 45);
		modificar.addActionListener(this);
		getContentPane().add(modificar);
		
		limpiar = new JButton("Limpiar");
		limpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		limpiar.setBounds(681, 476, 129, 45);
		limpiar.addActionListener(this);
		getContentPane().add(limpiar);
		
		buscar = new JButton("Buscar");
		buscar.setBounds(381, 220, 89, 23);
		buscar.addActionListener(this);
		getContentPane().add(buscar);
		
		textxid = new JTextField();
		textxid.setEnabled(false);
		textxid.setBounds(346, 179, 62, 20);
		getContentPane().add(textxid);
		textxid.setVisible(false);
		textxid.setColumns(10);
		
		textx2id = new JTextField();
		textx2id.setEnabled(false);
		textx2id.setBounds(346, 153, 86, 20);
		getContentPane().add(textx2id);
		textx2id.setVisible(false);
		textx2id.setColumns(10);
		
		
		
		conexion= Conexion.obtener_conexion();
		
		mostrar_alumnos();
		mostrar_cursos();
		mostrar_tabla();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==enviar) {
			Enviar_datos();
			mostrar_tabla();
		}
		if(e.getSource()==regresar) {
			Menu frame = new Menu();
			frame.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==buscar) {
			Buscar();
			textxid.setText(alumno.getText());
			textx2id.setText(curso.getText());
		}
		if(e.getSource()==eliminar) {
			Borrar_datos();
			mostrar_tabla();
		}
		if(e.getSource()==modificar) {
			Modificar();
			mostrar_tabla();
		}
		if(e.getSource()==calculo) {
			String cad1= cal1.getText();
			String cad2= cali2.getText();
			String cad3= cali3.getText();
			
			float x1= (float) Math.round(Float.parseFloat(cad1));
			float x2= (float) Math.round(Float.parseFloat(cad2));
			float x3= (float) Math.round(Float.parseFloat(cad3));
			
			String c1= String.valueOf(x1);
			cal1.setText(c1);
			String c2= String.valueOf(x2);
			cali2.setText(c2);
			String c3= String.valueOf(x3);
			cali3.setText(c3);
			
			
			float p= (x1+x2+x3)/3;
			float resultado= (float)Math.round(p);
			String prom= String.valueOf(resultado);
			promfinal.setText(""+prom);
			JOptionPane.showMessageDialog(null, "Las calificaciones de los parciales se redondean\nde la calificacion redondeada se saca el promedio final y esta tambien se redondea");
		}
		
	}
	
	private void Enviar_datos() {
		
		try{
			
			final String consulta="INSERT INTO CALIFICACIONES_ALUMNO(Alumnos_idAlumnos, Alumnos_Grupos_idGrupos, Cursos_idCursos, Cursos_Maestros_idMaestros, Cursos_Salon_idSalon, Califi_1_parcial,Califi_2_parcial,Califi_3_parcial, Promedio_final) VALUES(?,?,?,?,?,?,?,?,?)"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, alumno.getText());
			sentencia.setString(2, grupo.getText());
			sentencia.setString(3, curso.getText());
			sentencia.setString(4, maestro.getText());
			sentencia.setString(5, salon.getText());
			sentencia.setString(6, cal1.getText());
			sentencia.setString(7, cali2.getText());
			sentencia.setString(8, cali3.getText());
			sentencia.setString(9, promfinal.getText());
			
			sentencia.executeUpdate();
			
			Limpiar();
			
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al enviar informacion a la base de datos");
		}
	}
	
	public void Borrar_datos(){
		try{
			final String consulta= "DELETE FROM calificaciones_alumno WHERE Alumnos_idAlumnos=? AND Cursos_idCursos=?";
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			sentencia.setString(1, alumno.getText());
			sentencia.setString(2, curso.getText());
			
			sentencia.executeUpdate();
			
			Limpiar();
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al eliminar informacion de la base de datos");
		}
	}
	
	public void Buscar(){
		try{
			
			final String consulta="SELECT * FROM calificaciones_alumno WHERE Alumnos_idAlumnos= ? and Cursos_idCursos=?"; 
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			sentencia.setString(1, alumno.getText());
			sentencia.setString(2, curso.getText());
			
			rs= sentencia.executeQuery();
			
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Calificacion de alumno encontrado");
				alumno.setText(rs.getString("Alumnos_idAlumnos"));
				grupo.setText(rs.getString("Alumnos_Grupos_idGrupos"));
				curso.setText(rs.getString("Cursos_idCursos"));
				maestro.setText(rs.getString("Cursos_Maestros_idMaestros"));
				salon.setText(rs.getString("Cursos_Salon_idSalon"));
				cal1.setText(rs.getString("Califi_1_parcial"));
				cali2.setText(rs.getString("Califi_2_parcial"));
				cali3.setText(rs.getString("Califi_3_parcial"));
				promfinal.setText(rs.getString("Promedio_final"));
			}else{
				JOptionPane.showMessageDialog(null, "No existe esa calificacion de alumno");
			}
				
			
		}catch(Exception e){
			
		}
	}
	
	public void Modificar(){
		try{
			
		
			final String consulta="UPDATE calificaciones_alumno SET Alumnos_idAlumnos=?, Alumnos_Grupos_idGrupos=?, Cursos_idCursos=?, Cursos_Maestros_idMaestros=?, Cursos_Salon_idSalon=?, Califi_1_parcial=?,Califi_2_parcial=?,Califi_3_parcial=?, Promedio_final=?  WHERE Alumnos_idAlumnos= ? and Cursos_idCursos=?"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			
			sentencia.setString(1, alumno.getText());
			sentencia.setString(2, grupo.getText());
			sentencia.setString(3, curso.getText());
			sentencia.setString(4, maestro.getText());
			sentencia.setString(5, salon.getText());
			sentencia.setString(6, cal1.getText());
			sentencia.setString(7, cali2.getText());
			sentencia.setString(8, cali3.getText());
			sentencia.setString(9, promfinal.getText());
			sentencia.setString(10, textxid.getText());
			sentencia.setString(11, textx2id.getText());
			
			sentencia.executeUpdate();
			
			
			JOptionPane.showMessageDialog(null, "Sea actualizado correctamente ");
			
			Limpiar();
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al actualizar el curso");
		}
	}
	
	public void Limpiar(){
		alumno.setText("");
		grupo.setText("");
		curso.setText("");
		maestro.setText("");
		salon.setText("");
		cal1.setText("");
		cali2.setText("");
		cali3.setText("");
		promfinal.setText("");
	}
	
	public void mostrar_alumnos() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("id_Alumnos");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido_pat");
		modelo.addColumn("Apellido_mat");
		modelo.addColumn("id_Grupos");
		tabladatosalumnos.setModel(modelo);
		
		String sql= "SELECT idAlumnos,Nombre,Apellido_paterno,Apellido_materno,Grupos_idGrupos FROM alumnos";
		
		String datos[]= new String[5];
		Statement st;
		try {
			 st= conexion.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				datos[0]=rs.getString(1);
				datos[1]=rs.getString(2);
				datos[2]=rs.getString(3);
				datos[3]=rs.getString(4);
				datos[4]=rs.getString(5);
				modelo.addRow(datos);
			}
			tabladatosalumnos.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public void mostrar_cursos() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("Cursos");
		modelo.addColumn("id_maestro");
		modelo.addColumn("id_salon");
		tabladatoscursos.setModel(modelo);
		
		String sql= "SELECT * FROM cursos";
		
		String datos[]= new String[3];
		Statement st;
		try {
			 st= conexion.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				datos[0]=rs.getString(1);
				datos[1]=rs.getString(2);
				datos[2]=rs.getString(3);
				modelo.addRow(datos);
			}
			tabladatoscursos.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void mostrar_tabla() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("idAlumno");
		modelo.addColumn("idGrupo");
		modelo.addColumn("idCurso");
		modelo.addColumn("idMaestro");
		modelo.addColumn("idSalon");
		modelo.addColumn("Calificacion 1° par");
		modelo.addColumn("Calificacion 2° par");
		modelo.addColumn("Calificacion 3° par");
		modelo.addColumn("Promedio_final");
		tabladatos.setModel(modelo);
		
		String sql= "SELECT * FROM calificaciones_alumno";
		
		String datos[]= new String[9];
		Statement st;
		try {
			 st= conexion.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				datos[0]=rs.getString(1);
				datos[1]=rs.getString(2);
				datos[2]=rs.getString(3);
				datos[3]=rs.getString(4);
				datos[4]=rs.getString(5);
				datos[5]=rs.getString(6);
				datos[6]=rs.getString(7);
				datos[7]=rs.getString(8);
				datos[8]=rs.getString(9);
				modelo.addRow(datos);
			}
			tabladatos.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
