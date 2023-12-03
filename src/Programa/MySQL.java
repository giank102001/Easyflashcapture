package Programa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;

public class MySQL extends JFrame implements ActionListener{

	private JPanel contentPane;	
	JTextField puerto;
	JTextField usuario;
	JTextField contra;
	private JButton btnVerificar;
	Connection conexion;
	
	static String p;
	static String u;
	static String c;

	public MySQL() {
		setTitle("Inicio de Java-MySQL ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(398, 190);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionText);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.YELLOW, new Color(255, 140, 0), new Color(250, 128, 114), new Color(255, 215, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPuertoDeSu = new JLabel("Puerto de su MySQL:");
		lblPuertoDeSu.setForeground(Color.YELLOW);
		lblPuertoDeSu.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblPuertoDeSu.setBounds(10, 11, 127, 25);
		contentPane.add(lblPuertoDeSu);
		
		JLabel lblUsuarioDeMysql = new JLabel("Usuario de MySQL:");
		lblUsuarioDeMysql.setForeground(Color.YELLOW);
		lblUsuarioDeMysql.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblUsuarioDeMysql.setBounds(20, 47, 127, 25);
		contentPane.add(lblUsuarioDeMysql);
		
		JLabel contraseña = new JLabel("Contrase\u00F1a:");
		contraseña.setForeground(Color.YELLOW);
		contraseña.setFont(new Font("Ebrima", Font.PLAIN, 13));
		contraseña.setBounds(62, 83, 127, 25);
		contentPane.add(contraseña);
		
		puerto = new JTextField();
		puerto.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				char tecla= (char) e.getKeyCode();
				char abajo= '(';
				
				if(tecla== abajo){
					usuario.requestFocus();
					usuario.setBackground(Color.yellow);
					puerto.setBackground(Color.white);
				
				}
				
			}
		});
		puerto.setBackground(Color.yellow);
		puerto.setBounds(142, 15, 103, 20);
		contentPane.add(puerto);
		puerto.setColumns(10);
		
		usuario = new JTextField();
		usuario.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				char tecla= (char) e.getKeyCode();
				char abajo= '(';
				char arriba= '&';
				
				if(tecla== abajo){
					contra.requestFocus();
					contra.setBackground(Color.yellow);
					usuario.setBackground(Color.white);
				
				}
				if(tecla== arriba ){
					puerto.requestFocus();
					puerto.setBackground(Color.yellow);
					usuario.setBackground(Color.white);
				
				
				}
			}
		});
		usuario.setColumns(10);
		usuario.setBounds(142, 51, 103, 20);
		contentPane.add(usuario);
		
		contra = new JTextField();
		contra.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char teclap= e.getKeyChar();
				
				if(teclap==KeyEvent.VK_ENTER){
					btnVerificar.doClick();
				}
				
			}
			public void keyPressed(KeyEvent e) {
				char tecla= (char) e.getKeyCode();
				char arriba= '&';
					
				if(tecla== arriba ){
					usuario.requestFocus();
					usuario.setBackground(Color.yellow);
					contra.setBackground(Color.white);
				
				}
			}
		});
		contra.setColumns(10);
		contra.setBounds(142, 83, 103, 20);
		contentPane.add(contra);
		
		btnVerificar = new JButton("Verificar");
		btnVerificar.setFont(new Font("Ebrima", Font.PLAIN, 13));
		btnVerificar.setBounds(283, 115, 89, 23);
		btnVerificar.addActionListener(this);
		contentPane.add(btnVerificar);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnVerificar){
			p= puerto.getText();
			u= usuario.getText();
			c= contra.getText();
			
			
			conexion= Conexion.conexion();
			
			if(Conexion.existeconexion==1){
				if(Conexion.existebasededatos!=1){
				Contraseña frame = new Contraseña();
				frame.setVisible(true);
				this.setVisible(false);
				}else{
					this.setVisible(false);
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "Los datos no son correctos :(");
			}
			
		}
		
	}
	
}
