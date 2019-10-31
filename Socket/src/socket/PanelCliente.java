package socket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class PanelCliente extends JPanel implements Runnable{
	
	private JTextField Mensaje;
	private JLabel Usuario;
	private JButton Enviar;
	private JTextArea Chat;
	private JComboBox IP;
	
	
	public PanelCliente(){
		
		String ingresarUsuario = JOptionPane.showInputDialog("Nombre: ");
		
		JLabel lUsuario = new JLabel("Usuario: ");
		add(lUsuario);
		
		Usuario = new JLabel();
		Usuario.setText(ingresarUsuario);
		add(Usuario);
	
		JLabel texto=new JLabel(".:CHAT:.");
		add(texto);
		
		IP = new JComboBox();
		add(IP);
		
		Chat = new JTextArea(12,20);
		add(Chat);
		
		Mensaje=new JTextField(20);
		add(Mensaje);		
	
		Enviar=new JButton("Enviar");
		Enviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EnviarTexto(Mensaje.getText());
				
			}
		});
		add(Enviar);
		
		Thread hilo = new Thread(this);
		hilo.start();
	}
	
	private void EnviarTexto(String Mensaje) {
		Chat.append(Mensaje);
		Socket sCliente;
		try {
			sCliente = new Socket(Inet4Address.getLocalHost().getHostAddress(),9999);
			/*DataOutputStream salida= new DataOutputStream(sCliente.getOutputStream());
			salida.writeUTF(Mensaje);
			salida.close();*/
			
			PaqueteDatos datos = new PaqueteDatos();
			
			datos.setUsuario(Usuario.getText());
			datos.setIP(IP.getSelectedItem().toString());
			datos.setMensaje(Mensaje);
			
			ObjectOutputStream paquete_datos = new ObjectOutputStream(sCliente.getOutputStream());
			
			paquete_datos.writeObject(datos);
			
			//sCliente.close();
			
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void run() {
		
		try {
			
			ServerSocket servidorCliente = new ServerSocket(9090);
			Socket sCliente;
			PaqueteDatos paquete_recibido;
			
			while(true) {
				
				sCliente = servidorCliente.accept();
				ObjectInputStream entrada = new ObjectInputStream(sCliente.getInputStream());
				paquete_recibido = (PaqueteDatos) entrada.readObject();
				
				if(! paquete_recibido.getMensaje().equals(" online")) {

					Chat.append("\n"+paquete_recibido.getUsuario()+": "+paquete_recibido.getMensaje());
				}else {

					//Chat.append("\n" + paquete_recibido.getDireccionesIp());
					ArrayList<String> direccionesIps =new ArrayList<String>();
					direccionesIps = paquete_recibido.getDireccionesIp();
					IP.removeAllItems();
					for(String dir:direccionesIps) {
						IP.addItem(dir);
					}
				}
				
				//sCliente.close();
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}