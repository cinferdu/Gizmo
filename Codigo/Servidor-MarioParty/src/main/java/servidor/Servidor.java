package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeMap;

public class Servidor {

	private static final int PUERTO = 15123;
	
	private HashMap<String, Socket> clientesConectados; // nombre + socket
	private Sala lobby; // Sala "especial" no tiene duenio ni limite
	private TreeMap<Integer, Sala> salas; // agregar el lobby aca -> .get(0)==lobby
	private ServerSocket serverSocket;
	
	public Servidor() {
		clientesConectados = new HashMap<String, Socket>();
		lobby = new Sala();
		salas = new TreeMap<Integer, Sala>();
		
		try {
			serverSocket = new ServerSocket(PUERTO);
			
			System.out.println("Servidor Online");
			while (true) {
				System.out.println("Esperando clientes...");
				Socket cliente = serverSocket.accept();
				System.out.println(cliente.getLocalAddress().getHostAddress());
				ListenerThread hilo = new ListenerThread(cliente, clientesConectados, lobby, salas);
				hilo.start();
			}
			
		} catch (IOException e) {
			System.err.println("Ocurrio un problema en el servidor");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Servidor();
	}

}
