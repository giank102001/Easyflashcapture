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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Grupos extends JFrame implements ActionListener{

	private java.sql.Connection conexion;
	private JTextField grupo,textxid;
	JButton enviar, regresar,eliminar,buscar,modificar,limpiar;
	JScrollPane scroll;
	private JTable tabladatos;
	ResultSet rs;
	
	public Grupos() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.RED);
		setTitle("Grupos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(466, 282);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGrupo.setBounds(10, 11, 54, 17);
		getContentPane().add(lblGrupo);
		
		grupo = new JTextField();
		grupo.setBounds(74, 11, 96, 20);
		grupo.setColumns(10);
		getContentPane().add(grupo);
		
		enviar = new JButton("Enviar Datos");
		enviar.setFont(new Font("Tahoma", Font.BOLD, 9));
		enviar.setBounds(10, 39, 120, 45);
		enviar.addActionListener(this);
		getContentPane().add(enviar);
		
		
		regresar = new JButton("Regresar al Menu");
		regresar.setFont(new Font("Tahoma", Font.BOLD, 9));
		regresar.setBounds(10, 187, 129, 45);
		regresar.addActionListener(this);
		getContentPane().add(regresar);
		
		tabladatos = new JTable();
		tabladatos.setBounds(5, 5, 141, 50);
		
		scroll= new JScrollPane();
		scroll.setBounds(293, 13, 150, 150);
		scroll.setViewportView(tabladatos);
		getContentPane().add(scroll);
		
		eliminar = new JButton("Eliminar");
		eliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		eliminar.setBounds(163, 42, 120, 42);
		eliminar.addActionListener(this);
		getContentPane().add(eliminar);
		
		modificar = new JButton("Actualizar");
		modificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		modificar.setBounds(10, 95, 120, 42);
		modificar.addActionListener(this);
		getContentPane().add(modificar);
		
		buscar = new JButton("Buscar");
		buscar.setBounds(192, 10, 89, 23);
		buscar.addActionListener(this);
		getContentPane().add(buscar);
		
		textxid = new JTextField();
		textxid.setEnabled(false);
		textxid.setBounds(233, 8, 25, 20);
		getContentPane().add(textxid);
		textxid.setColumns(10);
		textxid.setVisible(false);
		
		limpiar = new JButton("Limpiar");
		limpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		limpiar.setBounds(163, 95, 120, 42);
		limpiar.addActionListener(this);
		getContentPane().add(limpiar);
		
		
		
		
		conexion= Conexion.obtener_conexion();
		
		mostrar_tabla();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==enviar) {
			Enviar_datos();
			mostrar_tabla();
			Limpiar();
			
		}
		if(e.getSource()==regresar) {
			Menu frame = new Menu();
			frame.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==eliminar) {
			Borrar_datos();
			mostrar_tabla();
			Limpiar();
			
		}
		if(e.getSource()==buscar) {
			Buscar();
			textxid.setText(grupo.getText());
		}
		if(e.getSource()==modificar) {
			Modificar();
			mostrar_tabla();
			Limpiar();
			
		}
		if(e.getSource()==limpiar) {
			Limpiar();
		}
		
	}
	
	private void Enviar_datos() {
		
		try{
			
			final String consulta="INSERT INTO GRUPOS(idGrupos) VALUES(?)"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, grupo.getText());
			
			sentencia.executeUpdate();
			
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al enviar informacion a la base de datos");
		}
	}
	
	private void Borrar_datos() {
		
		try{
			
			final String consulta="DELETE FROM grupos  WHERE idGrupos = ?";
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			sentencia.setString(1, grupo.getText());
			
			
			
			sentencia.executeUpdate();
		
			
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al eliminar informacion de la base de datos");
		}
	}
	
	public void Buscar(){
		
		try{
			
			final String consulta="SELECT * FROM grupos WHERE idGrupos = ?"; 
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			sentencia.setString(1, grupo.getText());
			
			rs= sentencia.executeQuery();
			
			if(rs.next()){
				grupo.setText(rs.getString("idGrupos"));
				JOptionPane.showMessageDialog(null, "Grupo encontrado");
				
			}else{
				JOptionPane.showMessageDialog(null, "No existe ese grupo");
			}
				
			
		}catch(Exception e){
			
		}
	}
	
	public void Modificar(){
		try{
			
		
			final String consulta="UPDATE grupos SET idGrupos=? WHERE idGrupos=?"; 
			
			PreparedStatement sentencia= conexion.prepareStatement(consulta);
			
			
			sentencia.setString(1, grupo.getText());
			sentencia.setString(2, textxid.getText());
			
			sentencia.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Sea actualizado correctamente ");
			
		}catch(Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al mactualizar el grupo");
		}
	}
	
	public void Limpiar(){
		grupo.setText(null);
	}
	
	public void mostrar_tabla() {
		DefaultTableModel modelo= new DefaultTableModel();
		modelo.addColumn("Grupos");
		tabladatos.setModel(modelo);
		
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
			tabladatos.setModel(modelo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
