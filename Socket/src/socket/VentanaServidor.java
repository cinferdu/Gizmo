package socket;

import java.awt.BorderLayout;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaServidor extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private	JTextArea areatexto;
	
	public VentanaServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel panelServidor= new JPanel();
		
		panelServidor.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		panelServidor.add(areatexto,BorderLayout.CENTER);
		
		add(panelServidor);
		
		setVisible(true);
		
		Thread hilo= new Thread(this);
		
		hilo.start();
		
		}

	@Override
	public void run() {

		// System.out.println("Estoy en escucha");

		try {
			
			ServerSocket sServidor = new ServerSocket(9999);

			String Usuario, ip, Mensaje;
			PaqueteDatos paquete_recibido;

			ArrayList<String> listaIp = new ArrayList<String>();
			while (true) {
				Socket socket = sServidor.accept();
				/*
				 * DataInputStream entrada = new DataInputStream(socket.getInputStream());
				 * String mensaje = entrada.readUTF(); areatexto.append("\n"+mensaje);
				 * socket.close();
				 */

				

				ObjectInputStream paquete_entrada = new ObjectInputStream(socket.getInputStream());

				paquete_recibido = (PaqueteDatos) paquete_entrada.readObject();

				Usuario = paquete_recibido.getUsuario();
				ip = paquete_recibido.getIP();
				Mensaje = paquete_recibido.getMensaje();

				if (!Mensaje.equals(" online")) {

					areatexto.append("\n" + Usuario + ": " + Mensaje + " para " + ip);

					Socket Destinatario = new Socket(ip, 9090);

					ObjectOutputStream paquete_reenvio = new ObjectOutputStream(Destinatario.getOutputStream());

					paquete_reenvio.writeObject(paquete_recibido);

					paquete_reenvio.close();

					Destinatario.close();

					socket.close();
				} else {
					// Detectar Usuarios Online//

					InetAddress loc = socket.getInetAddress();

					String IpRemota = loc.getHostAddress();

					System.out.println("Online: " + IpRemota);
					
					listaIp.add(IpRemota);
					
					paquete_recibido.setDireccionesIp(listaIp);
					
					for (String dir : listaIp) {
						Socket Destinatario = new Socket(dir,9090);
						
						ObjectOutputStream paquete_reenvio = new ObjectOutputStream(Destinatario.getOutputStream());

						paquete_reenvio.writeObject(paquete_recibido);

						paquete_reenvio.close();

						Destinatario.close();

						socket.close();
					}
					
					///////////////////////////
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
}