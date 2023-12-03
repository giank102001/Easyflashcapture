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

public class Alumnos extends JFrame implements ActionListener{

	private java.sql.Connection conexion;
	private JTextField idalumno;
	private JButton enviar, regresar,limpiar,modificar,eliminar,buscar;
	private JTextField nombre;
	private JTextField paterno;
	private JTextField materno;
	private JTextField correo;
	private JLabel lblCorreo;
	private JTextField telefono;
	private JLabel lblTelefonoDePadres;
	private JLabel lblParaPoderLlenar;
	private JTextField grupo;
	private JLabel lblGrupo_1;
	JScrollPane scrollgrupos;
	private JTable tabladatosgrupos;
	private JTable tabladatos;
	JScrollPane scroll;
	private JTextField textxid;
	ResultSet rs;
	
	
	public Alumnos() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.RED);
		setTitle("Alumnos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1104, 539);
		setLocationRelativeTo(null);
		
		
		JLabel lblGrupo = new JLabel("idAlumno:");
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(10, 6, 96, 26);
		getContentPane().add(lblGrupo);
		
		idalumno = new JTextField();
		idalumno.setBounds(99, 6, 102, 25);
		idalumno.setColumns(10);
		getContentPane().add(idalumno);
		
		enviar = new JButton("Enviar Datos");
		enviar.setFont(new Font("Tahoma", Font.BOLD, 10));
		enviar.setBounds(553, 278, 129, 45);
		enviar.addActionListener(this);
		getContentPane().add(enviar);
		
		
		regresar = new JButton("Regresar al Menu");
		regresar.setFont(new Font("Tahoma", Font.BOLD, 9));
		regresar.setBounds(949, 444, 129, 45);
		regresar.addActionListener(this);
		getContentPane().add(regresar);
		
		JLabel lblNiombreDelMaestro = new JLabel("Nombre del Alumno:");
		lblNiombreDelMaestro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNiombreDelMaestro.setBounds(10, 43, 181, 38);
		getContentPane().add(lblNiombreDelMaestro);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(163, 52, 181, 25);
		getContentPane().add(nombre);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		lblApellidoPaterno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoPaterno.setBounds(10, 92, 169, 26);
		getContentPane().add(lblApellidoPaterno);
		
		paterno = new JTextField();
		paterno.setColumns(10);
		paterno.setBounds(148, 95, 144, 25);
		getContentPane().add(paterno);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		lblApellidoMaterno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoMaterno.setBounds(10, 144, 159, 26);
		getContentPane().add(lblApellidoMaterno);
		
		materno = new JTextField();
		materno.setColumns(10);
		materno.setBounds(148, 147, 159, 25);
		getContentPane().add(materno);
		
		correo = new JTextField();
		correo.setColumns(10);
		correo.setBounds(84, 184, 229, 25);
		getContentPane().add(correo);
		
		lblCorreo = new JLabel("Correo@:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCorreo.setBounds(10, 181, 159, 26);
		getContentPane().add(lblCorreo);
		
		telefono = new JTextField();
		telefono.setColumns(10);
		telefono.setBounds(164, 230, 159, 25);
		getContentPane().add(telefono);
		
		lblTelefonoDePadres = new JLabel("Telefono de Padres:");
		lblTelefonoDePadres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefonoDePadres.setBounds(10, 227, 159, 26);
		getContentPane().add(lblTelefonoDePadres);
		
		lblParaPoderLlenar = new JLabel("<html>Para poder llenar este campo se debe llenar primero los grupos<br>coforme a la tabla de abajo</html>");
		lblParaPoderLlenar.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblParaPoderLlenar.setForeground(Color.CYAN);
		lblParaPoderLlenar.setBounds(10, 278, 345, 26);
		getContentPane().add(lblParaPoderLlenar);
		
		grupo = new JTextField();
		grupo.setColumns(10);
		grupo.setBounds(67, 327, 159, 25);
		getContentPane().add(grupo);
		
		lblGrupo_1 = new JLabel("Grupo:");
		lblGrupo_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo_1.setBounds(10, 324, 159, 26);
		getContentPane().add(lblGrupo_1);
		
		tabladatosgrupos = new JTable();
		tabladatosgrupos.setBounds(5, 5, 141, 50);
		
		scrollgrupos= new JScrollPane();
		scrollgrupos.setBounds(67, 363, 150, 132);
		scrollgrupos.setViewportView(tabladatosgrupos);
		getContentPane().add(scrollgrupos);
		
		tabladatos = new JTable();
		tabladatos.setBounds(5, 5, 141, 50);
		
		scroll= new JScrollPane();
		scroll.setBounds(427, 27, 651, 238);
		scroll.setViewportView(tabladatos);
		getContentPane().add(scroll);
		
		eliminar = new JButton("Eliminar");
		eliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		eliminar.setBounds(765, 278, 129, 45);
		eliminar.addActionListener(this);
		getContentPane().add(eliminar);
		
		modificar = new JButton("Actualizar");
		modificar.setFont(new Font("Tahoma", Font.BOLD, 10));
		modificar.setBounds(553, 345, 129, 45);
		modificar.addActionListener(this);
		getContentPane().add(modificar);
		
		limpiar = new JButton("Limpiar");
		limpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		limpiar.setBounds(765, 345, 129, 45);
		limpiar.addActionListener(this);
		getContentPane().add(limpiar);
		
		buscar = new JButton("Buscar");
		buscar.setBounds(278, 10, 89, 23);
		buscar.addActionListener(this);
		getContentPane().add(buscar);
		
		textxid = new JTextField();
		textxid.setEnabled(false);
		textxid.setBounds(218, 11, 46, 20);
		getContentPane().add(textxid);
		textxid.setVisible(false);
		textxid.setColumns(10);
		
		
		conexion= Conexion.obtener_conexion();
		
		mostrar_tabla();
		mostrar_grupos();
	
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
		if(e.getSource()==eliminar) {
			Borrar_datos();
			mostrar_tabla();
		}
		if(e.getSource()==buscar) {
			Buscar();
			textxid.setText(idalumno.getText());
		}
		if(e.getSource()==modificar) {
			Modificar();
			mostrar_tabla();
		}
		
	}
	
	private void Enviar_datos() {
		
		try{
			
			final String consulta="INSERT INTO Alumnos(idAlumnos, Nombre, Apellido_paterno, Apellido_materno, Correo_electronico,Numero_tel_padres, Grupos_idGrupos) VALUES(?,?,?,?,?,?,?)"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, idalumno.getText());
			sentencia.setString(2, nombre.getText());
			sentencia.setString(3, paterno.getText());
			sentencia.setString(4, materno.getText());
			sentencia.setString(5, correo.getText());
			sentencia.setString(6, telefono.getText());
			sentencia.setString(7, grupo.getText());
			
			sentencia.executeUpdate();
			
			Limpiar();
			
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al enviar informacion a la base de datos");
		}
	}
	
	private void Borrar_datos() {
		
		try{
			
			final String consulta="DELETE FROM alumnos WHERE idAlumnos = ?"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, idalumno.getText());
			
			
			
			sentencia.executeUpdate();
			
			Limpiar();
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al eliminar informacion a la base de datos");
		}
	}
	
	public void Buscar(){
		try{
			
			final String consulta="SELECT * FROM alumnos WHERE idAlumnos = ?"; 
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			sentencia.setString(1, idalumno.getText());
			
			rs= sentencia.executeQuery();
			
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Alumno encontrado");
				idalumno.setText(rs.getString("idAlumnos"));
				nombre.setText(rs.getString("Nombre"));
				paterno.setText(rs.getString("Apellido_paterno"));
				materno.setText(rs.getString("Apellido_materno"));
			    correo.setText(rs.getString("Correo_electronico"));
			    telefono.setText(rs.getString("Numero_tel_padres"));
			    grupo.setText(rs.getString("Grupos_idGrupos"));
			}else{
				JOptionPane.showMessageDialog(null, "No existe ese alumno");
			}
				
			
		}catch(Exception e){
			
		}
	}
	
	public void Modificar(){
		try{
			
		
			final String consulta="UPDATE alumnos SET idAlumnos=?,Nombre=?,Apellido_paterno=?,Apellido_materno=?,Correo_electronico=?,Numero_tel_padres=?,Grupos_idGrupos=?  WHERE idAlumnos=?"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			
			sentencia.setString(1, idalumno.getText());
			sentencia.setString(2, nombre.getText());
			sentencia.setString(3, paterno.getText());
			sentencia.setString(4, materno.getText());
			sentencia.setString(5, correo.getText());
			sentencia.setString(6, telefono.getText());
			sentencia.setString(7, grupo.getText());
			sentencia.setString(8, textxid.getText());
			
			sentencia.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Sea actualizado correctamente ");
			
			Limpiar();
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al actualizar el curso");
		}
	}
	
	public void Limpiar(){
		idalumno.setText("");
		nombre.setText("");
		paterno.setText("");
		materno.setText("");
		correo.setText("");
		telefono.setText("");
		grupo.setText("");
	}
	
	public void mostrar_grupos() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("Grupos");
		tabladatosgrupos.setModel(modelo);
		
		String sql= "SELECT * FROM grupos";
		
		String datos[]= new String[1];
		Statement st;
		try {
			 st= conexion.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				datos[0]=rs.getString(1);
				modelo.addRow(datos);
			}
			tabladatosgrupos.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void mostrar_tabla() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("id_Alumnos");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido_pat");
		modelo.addColumn("Apellido_mat");
		modelo.addColumn("Correo_elec");
		modelo.addColumn("Numero_tel_padres");
		modelo.addColumn("id_Grupos");
		tabladatos.setModel(modelo);
		
		String sql= "SELECT * FROM alumnos";
		
		String datos[]= new String[7];
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
				modelo.addRow(datos);
			}
			tabladatos.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
