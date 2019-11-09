package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeMap;

public class Servidor {
	
	private static final int PUERTO = 10200;
	
	private HashMap<String, ListenerThread> clientesConectados; // nombre + socket
	private Sala lobby; // Sala "especial" no tiene duenio ni limite
	private TreeMap<Integer, Sala> salas; // agregar el lobby aca -> .get(0)==lobby
	public static TreeMap<Integer, PartidaThread> partidas = new TreeMap<Integer, PartidaThread>();
	private ServerSocket serverSocket;
	
	public Servidor() {
		clientesConectados = new HashMap<String, ListenerThread>();
		lobby = new Sala();
		salas = new TreeMap<Integer, Sala>();
		
		try {
			serverSocket = new ServerSocket(PUERTO);
			Socket clientewrite;
			Socket clienteread;
			System.out.println("Servidor Online");
			while (true) {
				System.out.println("Esperando clientes...");
				clientewrite = serverSocket.accept();
				clienteread = serverSocket.accept();
				System.out.println(clientewrite.getLocalAddress().getHostAddress());
				ListenerThread hilo = new ListenerThread(clienteread, clientewrite, clientesConectados, lobby, salas, partidas);
				System.out.println("Se ha conectado un cliente");
				hilo.start();
			}
			
		} catch (IOException e) {
			System.err.println("Ocurrio un problema en el servidor");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SecurityException, IOException {
		new Servidor();
	}
}
