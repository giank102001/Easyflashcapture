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

public class Maestros extends JFrame implements ActionListener{

	private java.sql.Connection conexion;
	private JTextField idmaestro;
	JButton enviar, regresar;
	private JTextField nombre;
	private JTextField paterno;
	private JTextField materno,textxid;
	private JButton modificar;
	private JButton limpiar;
	private JButton buscar,eliminar;
	JScrollPane scroll;
	private JTable tabladatos;
	ResultSet rs;

	
	
	public Maestros() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.RED);
		setTitle("Maestros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(742, 456);
		setLocationRelativeTo(null);
		this.setTitle("Maestros");
		
		
		JLabel lblGrupo = new JLabel("idMaestro:");
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(10, 6, 96, 26);
		getContentPane().add(lblGrupo);
		
		idmaestro = new JTextField();
		idmaestro.setBounds(99, 6, 102, 25);
		idmaestro.setColumns(10);
		getContentPane().add(idmaestro);
		
		enviar = new JButton("Enviar Datos");
		enviar.setFont(new Font("Tahoma", Font.BOLD, 10));
		enviar.setBounds(10, 172, 129, 45);
		enviar.addActionListener(this);
		getContentPane().add(enviar);
		
		
		regresar = new JButton("Regresar al Menu");
		regresar.setFont(new Font("Tahoma", Font.BOLD, 9));
		regresar.setBounds(10, 359, 129, 45);
		regresar.addActionListener(this);
		getContentPane().add(regresar);
		
		JLabel lblNiombreDelMaestro = new JLabel("Nombre del Maestro:");
		lblNiombreDelMaestro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNiombreDelMaestro.setBounds(0, 33, 181, 38);
		getContentPane().add(lblNiombreDelMaestro);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(163, 42, 181, 25);
		getContentPane().add(nombre);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		lblApellidoPaterno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoPaterno.setBounds(0, 77, 169, 26);
		getContentPane().add(lblApellidoPaterno);
		
		paterno = new JTextField();
		paterno.setColumns(10);
		paterno.setBounds(132, 80, 144, 25);
		getContentPane().add(paterno);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
		lblApellidoMaterno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoMaterno.setBounds(0, 128, 159, 26);
		getContentPane().add(lblApellidoMaterno);
		
		materno = new JTextField();
		materno.setColumns(10);
		materno.setBounds(132, 131, 159, 25);
		getContentPane().add(materno);
		
		eliminar = new JButton("Eliminar");
		eliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		eliminar.setBounds(199, 172, 120, 42);
		eliminar.addActionListener(this);
		getContentPane().add(eliminar);
		
		modificar = new JButton("Actualizar");
		modificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		modificar.setBounds(10, 242, 129, 42);
		modificar.addActionListener(this);
		getContentPane().add(modificar);
		
		limpiar = new JButton("Limpiar");
		limpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		limpiar.setBounds(199, 242, 120, 42);
		limpiar.addActionListener(this);
		getContentPane().add(limpiar);
		
		buscar = new JButton("Buscar");
		buscar.setBounds(256, 10, 89, 23);
		buscar.addActionListener(this);
		getContentPane().add(buscar);
		
		tabladatos = new JTable();
		tabladatos.setSize(300, 50);
		
		scroll= new JScrollPane();
		scroll.setBounds(363, 10, 353, 222);
		scroll.setViewportView(tabladatos);
		getContentPane().add(scroll);
		
		textxid = new JTextField();
		textxid.setEnabled(false);
		textxid.setBounds(233, 8, 25, 20);
		getContentPane().add(textxid);
		textxid.setColumns(10);
		textxid.setVisible(false);
		
		
		
		conexion= Conexion.obtener_conexion();
		
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
		if(e.getSource()==eliminar) {
			Borrar_datos();
			mostrar_tabla();
		}
		if(e.getSource()==buscar) {
			Buscar();
			textxid.setText(idmaestro.getText());
		}
		if(e.getSource()==limpiar) {
			Limpiar();
		}
		if(e.getSource()==modificar) {
			Modificar();
			mostrar_tabla();
			Limpiar();
		}
		
	}
	
	private void Enviar_datos() {
		
		try{
			
			final String consulta="INSERT INTO Maestros(idMaestros, Nombre, Apellido_paterno, Apellido_materno) VALUES(?,?,?,?)"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, idmaestro.getText());
			sentencia.setString(2, nombre.getText());
			sentencia.setString(3, paterno.getText());
			sentencia.setString(4, materno.getText());
			
			sentencia.executeUpdate();
			
			Limpiar();
			
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al enviar informacion a la base de datos");
		}
	}
	
	private void Borrar_datos() {
		
		try{
			
			final String consulta="DELETE FROM maestros WHERE idMaestros = ?"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, idmaestro.getText());
			
			
			
			sentencia.executeUpdate();
			
			Limpiar();
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al eliminar informacion de la base de datos");
		}
	}
	
	public void Buscar(){
		try{
			
			final String consulta="SELECT * FROM maestros WHERE idMaestros = ?"; 
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			sentencia.setString(1, idmaestro.getText());
			
			rs= sentencia.executeQuery();
			
			if(rs.next()){
				idmaestro.setText(rs.getString("idMaestros"));
				nombre.setText(rs.getString("Nombre"));
				paterno.setText(rs.getString("Apellido_paterno"));
				materno.setText(rs.getString("Apellido_materno"));
				JOptionPane.showMessageDialog(null, "id del Maestro encontrado");
			}else{
				JOptionPane.showMessageDialog(null, "No existe un Maestro con esa id");
			}
				
			
		}catch(Exception e){
			
		}
	}
	
	public void Modificar(){
		try{
			
		
			final String consulta="UPDATE maestros SET idMaestros=?, Nombre=?, Apellido_paterno=?, Apellido_materno=?  WHERE idMaestros=?"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			
			sentencia.setString(1, idmaestro.getText());
			sentencia.setString(2, nombre.getText());
			sentencia.setString(3, paterno.getText());
			sentencia.setString(4, materno.getText());
			sentencia.setString(5, textxid.getText());
			
			sentencia.executeUpdate();
			
			
			JOptionPane.showMessageDialog(null, "Sea actualizado correctamente ");
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al actualizar el maestro");
		}
	}
	
	public void Limpiar(){
		idmaestro.setText(null);
		nombre.setText(null);
		paterno.setText(null);
		materno.setText(null);
	}
	
	public void mostrar_tabla() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("idMaestros");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido pater");
		modelo.addColumn("Apellido mater");
		tabladatos.setModel(modelo);
		
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
			tabladatos.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
