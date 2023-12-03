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

public class Salones extends JFrame  implements ActionListener{
	
	private JTextField salon, edificio;
	private java.sql.Connection conexion;
	JButton enviar,regresar,eliminar,modificar,limpiar,buscar;
	JScrollPane scroll;
	private JTable tabladatos;
	private JTextField textid;
	ResultSet rs;
	
	
	public Salones() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.RED);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(588, 321);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.setTitle("Salones");
		
		JLabel lblUsuario = new JLabel("Salon:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(0, -2, 89, 36);
		getContentPane().add(lblUsuario);
		
		salon = new JTextField();
		salon.setBounds(56, 8, 89, 20);
		getContentPane().add(salon);
		salon.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Edificio:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContrasea.setBounds(0, 30, 98, 36);
		getContentPane().add(lblContrasea);
		
		edificio = new JTextField();
		edificio.setBounds(66, 40, 89, 20);
		getContentPane().add(edificio);
		
		enviar = new JButton("Enviar Datos");
		enviar.setFont(new Font("Tahoma", Font.BOLD, 11));
		enviar.setBounds(10, 71, 120, 42);
		enviar.addActionListener(this);
		getContentPane().add(enviar);
		
		regresar = new JButton("Regresar al Menu");
		regresar.setFont(new Font("Tahoma", Font.BOLD, 9));
		regresar.setBounds(10, 226, 129, 45);
		regresar.addActionListener(this);
		getContentPane().add(regresar);
		

		tabladatos = new JTable();
		tabladatos.setBounds(5, 5, 141, 50);
		
		scroll= new JScrollPane();
		scroll.setBounds(326, 10, 237, 222);
		scroll.setViewportView(tabladatos);
		getContentPane().add(scroll);
		
		eliminar = new JButton("Eliminar");
		eliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		eliminar.setBounds(162, 71, 120, 42);
		eliminar.addActionListener(this);
		getContentPane().add(eliminar);
		
		modificar = new JButton("Actualizar");
		modificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		modificar.setBounds(10, 140, 120, 42);
		modificar.addActionListener(this);
		getContentPane().add(modificar);
		
		limpiar = new JButton("Limpiar");
		limpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		limpiar.setBounds(162, 140, 120, 42);
		limpiar.addActionListener(this);
		getContentPane().add(limpiar);
		
		buscar = new JButton("Buscar");
		buscar.setBounds(196, 7, 89, 23);
		buscar.addActionListener(this);
		getContentPane().add(buscar);
		
		textid = new JTextField();
		textid.setEnabled(false);
		textid.setBounds(162, 8, 25, 20);
		getContentPane().add(textid);
		textid.setColumns(10);
		textid.setVisible(false);
		
		
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
			if(e.getSource()==buscar) {
				Buscar();
				textid.setText(salon.getText());
			}
			if(e.getSource()==limpiar) {
				Limpiar();
			}
			if(e.getSource()==modificar) {
				Modificar();
				mostrar_tabla();
			}
			if(e.getSource()==eliminar) {
				Borrar_datos();
				mostrar_tabla();
			}
			
		}
		
		private void Enviar_datos() {
			
			try{
				
				final String consulta="INSERT INTO SALON(idSalon,Edificio) VALUES(?,?)"; 
				
				PreparedStatement sentencia= conexion.prepareStatement(consulta);
				
				sentencia.setString(1, salon.getText());
				sentencia.setString(2, edificio.getText());
				
				
				sentencia.executeUpdate();
				
				Limpiar();
				
				
			}catch(Exception e) {
				e.getStackTrace();
				JOptionPane.showMessageDialog(null, "Error al enviar informacion a la base de datos");
			}
		}
		
		private void Borrar_datos() {
			
			try{
				
				final String consulta="DELETE FROM salon WHERE idSalon = ?"; 
				
				PreparedStatement sentencia= conexion.prepareStatement(consulta);
				
				sentencia.setString(1, salon.getText());
				
				
				
				sentencia.executeUpdate();
				
				Limpiar();
				
			}catch(Exception e) {
				e.getStackTrace();
				JOptionPane.showMessageDialog(null, "Error al eliminar informacion de la base de datos");
			}
		}
		
		public void Modificar(){
			try{
				
			
				final String consulta="UPDATE salon SET idSalon=?,Edificio=? WHERE idSalon=?"; 
				
				PreparedStatement sentencia= conexion.prepareStatement(consulta);
				
				
				sentencia.setString(1, salon.getText());
				sentencia.setString(2, edificio.getText());
				sentencia.setString(3, textid.getText());
				
				sentencia.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Sea actualizado  correctamente ");
				
				Limpiar();
				
			}catch(Exception e) {
				e.getStackTrace();
				JOptionPane.showMessageDialog(null, "Error al actualizar el salon");
			}
		}
		
		public void Buscar(){
			try{
				
				final String consulta="SELECT * FROM salon WHERE idSalon = ?"; 
				PreparedStatement sentencia= conexion.prepareStatement(consulta);
				sentencia.setString(1, salon.getText());
				
				rs= sentencia.executeQuery();
				
				if(rs.next()){
					JOptionPane.showMessageDialog(null, "Salon encontrado");
					salon.setText(rs.getString("idSalon"));
					edificio.setText(rs.getString("Edificio"));
				}else{
					JOptionPane.showMessageDialog(null, "No existe ese salon");
				}
					
				
			}catch(Exception e){
				
			}
		}
		
		public void Limpiar() {
			salon.setText(null);
			edificio.setText(null);
		}
		
		public void mostrar_tabla() {
			DefaultTableModel modelo= new DefaultTableModel();
			modelo.addColumn("Salon");
			modelo.addColumn("Edificio");
			tabladatos.setModel(modelo);
			
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
				tabladatos.setModel(modelo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
}
