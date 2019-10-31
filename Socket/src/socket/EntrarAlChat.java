package socket;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

public class EntrarAlChat extends WindowAdapter{ // Envia una señal de que se encuentra online cuando inicia la ventana
	public void windowOpened(WindowEvent e) {
		try {
			Socket socket = new Socket(Inet4Address.getLocalHost().getHostAddress(),9999);
			PaqueteDatos datos = new PaqueteDatos();
			datos.setMensaje(" online");
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			salida.writeObject(datos);
			socket.close();
			
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}
}
