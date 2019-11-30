package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import controller.UsuarioController;
import model.Usuario;
import sala.Sala;
import util.HibernateUtil;
import util.UtilesLog;

public class Servidor {
	private static final int PUERTO = 10200;

	private HashMap<String, ListenerThread> clientesConectados; // nombre + socket
	private Sala lobby; // Sala "especial" no tiene duenio ni limite
	private TreeMap<Integer, Sala> salas; // agregar el lobby aca -> .get(0)==lobby
	public static TreeMap<Integer, PartidaThread> partidas = new TreeMap<Integer, PartidaThread>();
	private ServerSocket serverSocket;

	private final static Logger LOGGER = Logger.getLogger(Servidor.class);

	public Servidor() {
		clientesConectados = new HashMap<String, ListenerThread>();
		lobby = new Sala();
		salas = new TreeMap<Integer, Sala>();

		try {
			HibernateUtil.getSession();
			
//			initUsers();
			
			serverSocket = new ServerSocket(PUERTO);
			Socket clientewrite;
			Socket clienteread;
			LOGGER.info("Servidor Online");
			while (true) {
				LOGGER.info("Esperando clientes...");
				clientewrite = serverSocket.accept();
				clienteread = serverSocket.accept();
				LOGGER.info(clientewrite.getLocalAddress().getHostAddress());
				ListenerThread hilo = new ListenerThread(clienteread, clientewrite, clientesConectados, lobby, salas,
						partidas);
				LOGGER.info("Se ha conectado un cliente");
				hilo.start();
			}

		} catch (IOException e) {
			LOGGER.error("Ocurrio un problema en el servidor");
			UtilesLog.loggerStackTrace(e, this.getClass());
		}
	}

	private void initUsers() {
		UsuarioController.save(new Usuario("123", "123"));
		UsuarioController.save(new Usuario("asd", "asd"));
		UsuarioController.save(new Usuario("qwe", "qwe"));
		UsuarioController.save(new Usuario("zxc", "zxc"));
	}

	public static void main(String[] args) throws SecurityException, IOException {
		new Servidor();
	}
}
