package Programa;

import java.awt.BorderLayout;








import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.Box;

import java.awt.Color;

import javax.swing.JScrollPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu extends JFrame implements ActionListener{
	
	JButton grupos,salones,usuarios,maestros,alumnos,cursos,calificaciones,enviar,verificar;
	private JTextField correo;
	private JPasswordField contraseña;
	private JTextField comentario;
	private boolean conexion;
	private JLabel mensajeV;
	private String dominio;
	private Validacion validacion = new Validacion();
	public static  String usuario, user;
	public static String contra;
	private int i=0;
	
	public Menu() {
		getContentPane().setBackground(Color.WHITE);
		
		setTitle("EasyFlashCapture");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 500, 400);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Menu", null, panel, null);
		panel.setLayout(null);
		
		grupos = new JButton("Grupos");
		grupos.addActionListener(this);
		grupos.setBounds(34, 185, 91, 34);
		panel.add(grupos);
		
		usuarios = new JButton("Usuarios");
		usuarios.addActionListener(this);
		usuarios.setBounds(34, 241, 91, 34);
		panel.add(usuarios);
		
		salones = new JButton("Salones");
		salones.addActionListener(this);
		salones.setBounds(348, 185, 91, 34);
		panel.add(salones);
		
		maestros= new JButton("Maestros");
		maestros.addActionListener(this);
		maestros.setBounds(193, 185, 91, 34);
		panel.add(maestros);
		
		alumnos = new JButton("Alumnos");
		alumnos.addActionListener(this);
		alumnos.setBounds(348, 241, 91, 34);
		panel.add(alumnos);
		
		cursos = new JButton("Cursos");
		cursos.addActionListener(this);
		cursos.setBounds(193, 241, 91, 34);
		panel.add(cursos);
		
		calificaciones = new JButton("Calificaciones de alumnos");
		calificaciones.addActionListener(this);
		calificaciones.setBounds(140, 286, 195, 34);
		panel.add(calificaciones);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Comentarios y Ayuda", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblPonTuCorreo = new JLabel("Pon tu correo elctronico:");
		lblPonTuCorreo.setBounds(10, 11, 194, 33);
		panel_1.add(lblPonTuCorreo);
		
		correo = new JTextField();
		correo.setBounds(120, 43, 203, 20);
		panel_1.add(correo);
		correo.setColumns(10);
		
		JLabel lblContraseaDelCorreo = new JLabel("Contrase\u00F1a del correo:");
		lblContraseaDelCorreo.setBounds(10, 89, 170, 14);
		panel_1.add(lblContraseaDelCorreo);
		
		contraseña = new JPasswordField();
		contraseña.setBounds(120, 104, 203, 20);
		panel_1.add(contraseña);
		contraseña.setColumns(10);
		
		comentario = new JTextField();
		comentario.setBounds(10, 159, 313, 33);
		panel_1.add(comentario);
		comentario.setColumns(10);
		
		enviar = new JButton("Enviar comentario");
		enviar.setBounds(10, 258, 170, 33);
		enviar.addActionListener(this);
		panel_1.add(enviar);
		
		verificar = new JButton("Verificar Internet");
		verificar.setBounds(291, 238, 127, 33);
		verificar.addActionListener(this);
		panel_1.add(verificar);
		
		mensajeV = new JLabel("");
		mensajeV.setForeground(new Color(255, 0, 0));
		mensajeV.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mensajeV.setBounds(262, 282, 156, 33);
		panel_1.add(mensajeV);
		
		JLabel lblComentarioOAyuda = new JLabel("Comentario o ayuda:");
		lblComentarioOAyuda.setBounds(10, 129, 194, 33);
		panel_1.add(lblComentarioOAyuda);
		
		
		

		verificarConexion();
		
		
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==grupos) {
			JOptionPane.showMessageDialog(null, "Recuerda que debes llenar todos los campos para que funcione bien la aplicacion");
			Grupos a= new Grupos();
			a.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==usuarios) {
			JOptionPane.showMessageDialog(null, "Recuerda que debes llenar todos los campos para que funcione bien la aplicacion");
			Usuarios a= new Usuarios();
			a.setVisible(true);
			this.setVisible(false);
			
		}
		if(e.getSource()==salones) {
			JOptionPane.showMessageDialog(null, "Recuerda que debes llenar todos los campos para que funcione bien la aplicacion");
			Salones a= new Salones();
			a.setVisible(true);
			this.setVisible(false);
			
		}
		if(e.getSource()==maestros) {
			JOptionPane.showMessageDialog(null, "Recuerda que debes llenar todos los campos para que funcione bien la aplicacion");
			Maestros a= new Maestros();
			a.setVisible(true);
			this.setVisible(false);
			
		}
		if(e.getSource()==alumnos) {
			JOptionPane.showMessageDialog(null, "Recuerda que debes llenar todos los campos para que funcione bien la aplicacion");
			Alumnos a= new Alumnos();
			a.setVisible(true);
			this.setVisible(false);
			
		}
		if(e.getSource()==cursos) {
			JOptionPane.showMessageDialog(null, "Recuerda que debes llenar todos los campos para que funcione bien la aplicacion");
			Cursos a= new Cursos();
			a.setVisible(true);
			this.setVisible(false);
			
		}
		if(e.getSource()==calificaciones) {
			JOptionPane.showMessageDialog(null, "Recuerda que debes llenar todos los campos para que funcione bien la aplicacion");
			Calificaciones a= new Calificaciones();
			a.setVisible(true);
			this.setVisible(false);
			
		}
		if(e.getSource()==enviar) {
			usuario= correo.getText().trim();
			contra= contraseña.getText().trim();
			
			user=usuario;
			
			StringTokenizer st = new StringTokenizer(usuario, "@");
			 if(validacion.validateEmail(usuario) && contra.length()!=0)
			   {
			   while(st.hasMoreTokens()) {

			    String correo = st.nextToken();

			     dominio = st.nextToken();
			     dominio= dominio.toLowerCase();

			   }
			   
			   if(dominio.equals("gmail.com")  ){
				   Enviar();
			   }else
				   if(dominio.equals("hotmail.com") ){
					  Enviar();
				   }else
					   if(dominio.equals("outlook.com") ){
						 Enviar();  
			   }else{
				   JOptionPane.showMessageDialog(null,"EL DOMINIO SELECCIONADO ES INCORRECTO","ERROR	",JOptionPane.PLAIN_MESSAGE);
				   
			   }
			
		}else{
			   correo.setText("");
			   contraseña.setText("");
			   JOptionPane.showMessageDialog(null,"LA CUENTA NO ES UN CORREO ELECTRONICO","ERROR	",JOptionPane.PLAIN_MESSAGE);
		   
		}
		if(e.getSource()==verificar){
			verificarConexion();
		}
	}
		
	}
	
	public void Enviar(){
		i=0;
		
		try {
		Properties props= new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new autentificadorSMTP();
		Session session = Session.getDefaultInstance(props, auth);
		
		String correoR= correo.getText();
		String contraseñaR= contraseña.getText();
		String correoReceptor= "giancarlonava94@gmail.com";
		String asunto = "COMENTARIO";
		String mensaje= comentario.getText();
		
			MimeMessage message= new MimeMessage(session);
		
			message.setFrom(new InternetAddress(correoR));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setText(mensaje);
			
			try{
			Transport t= session.getTransport("smtp");
			t.connect(correoR, contraseñaR);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();
			}catch (AuthenticationFailedException ex)
		    {
			  i=1;
		      JOptionPane.showMessageDialog(null, new Object[] { "Usuario o contraseï¿½a incorrecto" }, "Error de autentificacion", 0);
		      throw new MessagingException();
		    }
			
			JOptionPane.showMessageDialog(null, "Correo enviado");
		
			
			} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Correo no enviado");
			} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(i==0){
			JOptionPane.showMessageDialog(null, "Correo no enviado\nï¿½ï¿½Compruebe si su antivirus esta desactivcado!!");
			}
		}
		
		
		
	}
	public void verificarConexion()
	  {
	    ComprobarConexionInternet google = new ComprobarConexionInternet();
	    conexion = google.test();
	    System.out.println(conexion);
	   
	   
	    if (conexion)
	    {
	      System.out.println("hay conexion");
	      mensajeV.setText("Si hay conexion a internet");
	      correo.setEnabled(true);
	      contraseña.setEnabled(true);
	      comentario.setEnabled(true);
	      enviar.setEnabled(true);
	      
	    }
	    else
	    {
	      System.out.println("No hay conexion");
	      mensajeV.setText("No hay conexion a internet");
	      correo.setEnabled(false);
	      contraseña.setEnabled(false);
	      comentario.setEnabled(false);
	      enviar.setEnabled(false);
	    }
	  }
	
	private class autentificadorSMTP
    extends Authenticator
    {
    private autentificadorSMTP() {}
    
    public PasswordAuthentication getPasswordAuthentication()
    {
      return new PasswordAuthentication(Menu.this.usuario, Menu.this.contra);
    }
  }
}
