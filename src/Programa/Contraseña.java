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

import com.mysql.jdbc.Connection;

import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Contraseña extends JFrame  implements ActionListener{
	
	JPanel contentPane;
	private JTextField usuario;
	JPasswordField contraseña;
	private Connection conexion;
	JButton iniciar;
	
	ResultSet rs;
	
	public Contraseña() {
		
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 335, 279);
			setLocationRelativeTo(null);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			setTitle("Iniciar sesion");
			
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setFont(new Font("Perpetua", Font.PLAIN, 21));
			lblUsuario.setBounds(36, 11, 117, 39);
			contentPane.add(lblUsuario);
			
			usuario = new JTextField();
			usuario.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					char tecla= (char) e.getKeyCode();
					char abajo= '(';
					
					if(tecla== abajo){
						contraseña.requestFocus();
						contraseña.setBackground(Color.yellow);
						usuario.setBackground(Color.white);
					
					}
				}
			});
			usuario.setBounds(110, 22, 162, 20);
			usuario.setBackground(Color.yellow);
			contentPane.add(usuario);
			usuario.setColumns(10);
			
			JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setFont(new Font("Perpetua", Font.PLAIN, 21));
			lblContrasea.setBounds(10, 81, 117, 48);
			contentPane.add(lblContrasea);
			
			contraseña = new JPasswordField();
			contraseña.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char tecla= e.getKeyChar();
					
					if(tecla==KeyEvent.VK_ENTER){
						iniciar.doClick();
					}
				}
				public void keyPressed(KeyEvent e) {
					char tecla= (char) e.getKeyCode();
					char arriba= '&';
					
					if(tecla== arriba ){
						usuario.requestFocus();
						usuario.setBackground(Color.yellow);
						contraseña.setBackground(Color.white);
					
					
					}
				}
			});
			contraseña.setBounds(110, 97, 162, 20);
			contentPane.add(contraseña);
			contraseña.setColumns(10);
			
			iniciar = new JButton("Iniciar sesion");
			iniciar.setFont(new Font("Century Gothic", Font.BOLD, 14));
			iniciar.setBounds(84, 183, 148, 23);
			iniciar.addActionListener(this);
			contentPane.add(iniciar);
			

		conexion= Conexion.obtener_conexion();
		
		
	
	}
	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==iniciar){
				
				if(!usuario.getText().equals("") && !contraseña.getText().equals("")){
					Iniciar_sesion();
				}else{
					JOptionPane.showMessageDialog(null, "Debe de ingresar sus datos");
				}
				
			}
		}
		
		public void Iniciar_sesion(){
			PreparedStatement ps=null;
			ResultSet rs= null;
			
			String sql="SELECT * From usuario WHERE idUsuario=?";
			
			try{
				ps= conexion.prepareStatement(sql);
				ps.setString(1, usuario.getText());
				rs= ps.executeQuery();
				
				if(rs.next()){
					if(contraseña.getText().equals(rs.getString(2))){
						JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente");
						Menu f= new Menu();
						f.setVisible(true);
						this.setVisible(false);
						setVisible(false);
						
					}else{
						JOptionPane.showMessageDialog(null, "La contraseña no es correcta");
						contraseña.setText("");
					}
				}else{
					JOptionPane.showMessageDialog(null, "No existe ese usuario");
					usuario.setText("");
					contraseña.setText("");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		
		
}
