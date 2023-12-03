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
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cursos extends JFrame implements ActionListener{

	private java.sql.Connection conexion;
	private JTextField curso;
	JButton enviar, regresar,eliminar,limpiar,modificar,buscar;
	private JTextField idmaestro,textxid;
	private JTextField idsalon;
	JScrollPane scrollmaestros;
	private JTable tabladatosmaestros;
	JScrollPane scrollsalones;
	private JTable tabladatossalones;
	private JTable tabladatos;
	JScrollPane scroll;
	ResultSet rs;
	
	
	public Cursos() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.RED);
		setTitle("Cursos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(860, 494);
		setLocationRelativeTo(null);
		
		
		JLabel lblGrupo = new JLabel("Nombre del curso:");
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(10, 11, 169, 26);
		getContentPane().add(lblGrupo);
		
		curso = new JTextField();
		curso.setBounds(149, 14, 167, 25);
		curso.setColumns(10);
		getContentPane().add(curso);
		
		enviar = new JButton("Enviar Datos");
		enviar.setFont(new Font("Tahoma", Font.BOLD, 10));
		enviar.setBounds(442, 267, 129, 45);
		enviar.addActionListener(this);
		getContentPane().add(enviar);
		
		
		regresar = new JButton("Regresar al Menu");
		regresar.setFont(new Font("Tahoma", Font.BOLD, 9));
		regresar.setBounds(627, 399, 129, 45);
		regresar.addActionListener(this);
		getContentPane().add(regresar);
		
		JLabel lblNiombreDelMaestro = new JLabel("<html>Identificacionn del <br>maestro que da el curso:</html>");
		lblNiombreDelMaestro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNiombreDelMaestro.setBounds(10, 96, 169, 68);
		getContentPane().add(lblNiombreDelMaestro);
		
		idmaestro = new JTextField();
		idmaestro.setColumns(10);
		idmaestro.setBounds(159, 118, 181, 25);
		getContentPane().add(idmaestro);
		
		JLabel lblApellidoPaterno = new JLabel("<html>Nombre del salon donde <br>se imparte este curso:</html>");
		lblApellidoPaterno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoPaterno.setBounds(10, 247, 210, 55);
		getContentPane().add(lblApellidoPaterno);
		
		idsalon = new JTextField();
		idsalon.setColumns(10);
		idsalon.setBounds(196, 254, 144, 25);
		getContentPane().add(idsalon);
		
		JLabel lblDebesLlenarAntes = new JLabel("<html>Debes llenar antes los maestros y salones para poder llenar estos campos <br>conforme a las tablas de abajo</html>");
		lblDebesLlenarAntes.setForeground(Color.CYAN);
		lblDebesLlenarAntes.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDebesLlenarAntes.setBounds(10, 43, 384, 55);
		getContentPane().add(lblDebesLlenarAntes);
		
		
		tabladatosmaestros = new JTable();
		tabladatosmaestros.setSize(300, 50);
		
		scrollmaestros= new JScrollPane();
		scrollmaestros.setBounds(10, 160, 353, 83);
		scrollmaestros.setViewportView(tabladatosmaestros);
		getContentPane().add(scrollmaestros);
		
		tabladatossalones = new JTable();
		tabladatossalones.setBounds(5, 5, 141, 50);
		
		scrollsalones= new JScrollPane();
		scrollsalones.setBounds(10, 304, 237, 108);
		scrollsalones.setViewportView(tabladatossalones);
		getContentPane().add(scrollsalones);
		
		tabladatos = new JTable();
		tabladatos.setBounds(5, 5, 141, 50);
		
		scroll= new JScrollPane();
		scroll.setBounds(489, 18, 345, 238);
		scroll.setViewportView(tabladatos);
		getContentPane().add(scroll);
		
		eliminar = new JButton("Eliminar");
		eliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		eliminar.setBounds(627, 267, 129, 45);
		eliminar.addActionListener(this);
		getContentPane().add(eliminar);
		
		modificar = new JButton("Actualizar");
		modificar.setFont(new Font("Tahoma", Font.BOLD, 10));
		modificar.setBounds(442, 322, 129, 45);
		modificar.addActionListener(this);
		getContentPane().add(modificar);
		
		limpiar = new JButton("Limpiar");
		limpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		limpiar.setBounds(627, 323, 129, 45);
		limpiar.addActionListener(this);
		getContentPane().add(limpiar);
		
		buscar = new JButton("Buscar");
		buscar.setBounds(390, 15, 89, 23);
		buscar.addActionListener(this);
		getContentPane().add(buscar);
		
		textxid = new JTextField("New button");
		textxid.setVisible(false);
		textxid.setEnabled(false);
		textxid.setBounds(335, 15, 45, 23);
		getContentPane().add(textxid);
		
		
		conexion= Conexion.obtener_conexion();
	
		mostrar_maestros();
		mostrar_salones();
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
		if(e.getSource()==limpiar) {
			Limpiar();
		}
		if(e.getSource()==eliminar) {
			Borrar_datos();
			mostrar_tabla();
		}
		if(e.getSource()==buscar) {
			Buscar();
			textxid.setText(curso.getText());
		}
		if(e.getSource()==modificar) {
			Modificar();
			mostrar_tabla();
		}
	}
	
	private void Enviar_datos() {
		
		try{
			
			final String consulta="INSERT INTO CURSOS(idCursos, Maestros_idMaestros, Salon_idSalon) VALUES(?,?,?)"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, curso.getText());
			sentencia.setString(2, idmaestro.getText());
			sentencia.setString(3, idsalon.getText());
			
			
			sentencia.executeUpdate();
			
			Limpiar();
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al enviar informacion a la base de datos");
		}
	}
	
	private void Borrar_datos() {
		
		try{
			
			final String consulta="DELETE FROM cursos WHERE idCursos = ?"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, curso.getText());
			
			
			
			sentencia.executeUpdate();
			
			Limpiar();
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al borrar informacion de la base de datos");
		}
	}
	
	public void Buscar(){
		try{
			
			final String consulta="SELECT * FROM cursos WHERE idCursos = ?"; 
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			sentencia.setString(1, curso.getText());
			
			rs= sentencia.executeQuery();
			
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Curso encontrado");
				curso.setText(rs.getString("idCursos"));
				idmaestro.setText(rs.getString("Maestros_idMaestros"));
				idsalon.setText(rs.getString("Salon_idSalon"));
			}else{
				JOptionPane.showMessageDialog(null, "No existe ese curso");
			}
				
			
		}catch(Exception e){
			
		}
	}
	
	public void Modificar(){
		try{
			
		
			final String consulta="UPDATE cursos SET idCursos=?,Maestros_idMaestros=?,Salon_idSalon=?  WHERE idCursos=?"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			
			sentencia.setString(1, curso.getText());
			sentencia.setString(2, idmaestro.getText());
			sentencia.setString(3, idsalon.getText());
			sentencia.setString(4, textxid.getText());
			
			sentencia.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Sea actualizado correctamente ");
			
			Limpiar();
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al actualizar el curso");
		}
	}
	
	public void Limpiar(){
		curso.setText(null);
		idsalon.setText(null);
		idmaestro.setText(null);
	}
	
	public void mostrar_maestros() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("idMaestros");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido pater");
		modelo.addColumn("Apellido mater");
		tabladatosmaestros.setModel(modelo);
		
		String sql= "SELECT * FROM maestros";
		
		String datos[]= new String[4];
		Statement st;
		try {
			 st= conexion.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				datos[0]=rs.getString(1);
				datos[1]=rs.getString(2);
				datos[2]=rs.getString(3);
				datos[3]=rs.getString(4);
				modelo.addRow(datos);
			}
			tabladatosmaestros.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void mostrar_salones() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("Salon");
		modelo.addColumn("Edificio");
		tabladatossalones.setModel(modelo);
		
		String sql= "SELECT * FROM salon";
		
		String datos[]= new String[2];
		Statement st;
		try {
			 st= conexion.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				datos[0]=rs.getString(1);
				datos[1]=rs.getString(2);
				modelo.addRow(datos);
			}
			tabladatossalones.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void mostrar_tabla() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("Cursos");
		modelo.addColumn("id_maestro");
		modelo.addColumn("id_salon");
		tabladatos.setModel(modelo);
		
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
			tabladatos.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
