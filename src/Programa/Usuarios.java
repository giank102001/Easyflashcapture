package Programa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class Usuarios extends JFrame  implements ActionListener{
	
	private JTextField usuario;
	private JPasswordField contraseña;
	private java.sql.Connection conexion;
	JButton enviar,regresar,eliminar,limpiar,buscar,modificar;
	JScrollPane scroll;
	private JTable tabladatos;
	private JTextField textxid;
	ResultSet rs;
	
	public Usuarios() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 102, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(626, 323);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.setTitle("Usuarios");
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(0, -2, 89, 36);
		getContentPane().add(lblUsuario);
		
		usuario = new JTextField();
		usuario.setBounds(75, 8, 143, 20);
		getContentPane().add(usuario);
		usuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContrasea.setBounds(0, 28, 98, 36);
		getContentPane().add(lblContrasea);
		
		contraseña = new JPasswordField();
		contraseña.setBounds(96, 38, 143, 20);
		getContentPane().add(contraseña);
		
		enviar = new JButton("Enviar Datos");
		enviar.setFont(new Font("Tahoma", Font.BOLD, 11));
		enviar.setBounds(10, 76, 120, 42);
		enviar.addActionListener(this);
		getContentPane().add(enviar);
		
		regresar = new JButton("Regresar al Menu");
		regresar.setFont(new Font("Tahoma", Font.BOLD, 9));
		regresar.setBounds(10, 228, 129, 45);
		regresar.addActionListener(this);
		getContentPane().add(regresar);
		
		tabladatos = new JTable();
		tabladatos.setBounds(5, 5, 141, 50);
		
		scroll= new JScrollPane();
		scroll.setBounds(363, 10, 237, 222);
		scroll.setViewportView(tabladatos);
		getContentPane().add(scroll);
		
		buscar = new JButton("Buscar");
		buscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		buscar.setBounds(264, 7, 89, 23);
		buscar.addActionListener(this);
		getContentPane().add(buscar);
		
		eliminar = new JButton("Eliminar");
		eliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		eliminar.setBounds(197, 79, 120, 36);
		eliminar.addActionListener(this);
		getContentPane().add(eliminar);
		
		modificar = new JButton("Actualizar");
		modificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		modificar.setBounds(10, 142, 120, 36);
		modificar.addActionListener(this);
		getContentPane().add(modificar);
		
		limpiar = new JButton("Limpiar");
		limpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		limpiar.setBounds(197, 142, 120, 36);
		limpiar.addActionListener(this);
		getContentPane().add(limpiar);
		
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
			if(e.getSource()==limpiar) {
				Limpiar();
			}
			if(e.getSource()==buscar) {
				Buscar();
				textxid.setText(usuario.getText());
			}
			if(e.getSource()==modificar) {
				Modificar();
				mostrar_tabla();
			}
		}
		
		private void Enviar_datos() {
			
			try{
				
				final String consulta="INSERT INTO USUARIO(idUsuario,Contraseï¿½a) VALUES(?,?)"; 
				
				PreparedStatement sentencia= conexion.prepareStatement(consulta);
				
				sentencia.setString(1, usuario.getText());
				sentencia.setString(2, contraseña.getText());
				
				
				sentencia.executeUpdate();
				
				Limpiar();
				
				
			}catch(Exception e) {
				e.getStackTrace();
				JOptionPane.showMessageDialog(null, "Error al enviar informacion a la base de datos");
			}
		}
		
		private void Borrar_datos() {
			
			try{
				
				final String consulta="DELETE FROM usuario WHERE idUsuario = ? and idUsuario!='root'"; 
				
				PreparedStatement sentencia= conexion.prepareStatement(consulta);
				
				sentencia.setString(1, usuario.getText());
				
				
				
				sentencia.executeUpdate();
				
				Limpiar();
				
			}catch(Exception e) {
				e.getStackTrace();
				JOptionPane.showMessageDialog(null, "Error al eliminar informacion de la base de datos");
			}
		}
		
		public void Buscar(){
			try{
				
				final String consulta="SELECT * FROM usuario WHERE idUsuario = ?"; 
				PreparedStatement sentencia= conexion.prepareStatement(consulta);
				sentencia.setString(1, usuario.getText());
				
				rs= sentencia.executeQuery();
				
				if(rs.next()){
					usuario.setText(rs.getString("idUsuario"));
					contraseña.setText(rs.getString("Contraseï¿½a"));
					JOptionPane.showMessageDialog(null, "Usuario encontrado");
				}else{
					JOptionPane.showMessageDialog(null, "No existe ese usuario");
				}
					
				
			}catch(Exception e){
				
			}
		}
		
		public void Modificar(){
			try{
				
			
				final String consulta="UPDATE usuario SET idUsuario=?,Contraseï¿½a=? WHERE idUsuario=?"; 
				
				PreparedStatement sentencia= conexion.prepareStatement(consulta);
				
				
				sentencia.setString(1, usuario.getText());
				sentencia.setString(2, contraseña.getText());
				sentencia.setString(3, textxid.getText());
				
				sentencia.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Sea actualizado correctamente ");
				
				Limpiar();
				
			}catch(Exception e) {
				e.getStackTrace();
				JOptionPane.showMessageDialog(null, "Error al actualizar el usuario");
			}
		}
		
		public void Limpiar(){
			usuario.setText(null);
			contraseña.setText(null);
		}
		
		public void mostrar_tabla() {
			DefaultTableModel modelo= new DefaultTableModel();
			modelo.addColumn("Usuarios");
			modelo.addColumn("Contraseï¿½a");
			tabladatos.setModel(modelo);
			
			String sql= "SELECT * FROM `usuario` WHERE `idUsuario`!='root'";
			
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
				tabladatos.setModel(modelo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
}

