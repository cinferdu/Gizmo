package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeMap;

import com.google.gson.Gson;

import mensaje.Mensaje;
import mensaje.MsjDesconectar;

public class ListenerThread extends Thread {
	private String nombreCliente;
	private int id_salaActiva; // sala en la que se encuetra el cliente
	private HashMap<String, Socket> clientesConectados;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Sala lobby;
	private TreeMap<Integer, Sala> salas = new TreeMap<Integer, Sala>(); // key=ID
	
	private Gson gson = new Gson();

	public ListenerThread(Socket cliente, HashMap<String, Socket> clientesConectados, Sala lobby, TreeMap<Integer, Sala> salas) {
		
		try {
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			System.err.println("Error cliente");
			e.printStackTrace();
		}
		this.clientesConectados = clientesConectados;
		this.lobby = lobby;
		this.salas = salas;
		id_salaActiva = -1;
	}
	
	@Override
	public void run() {
		Mensaje comando = null;
		
		try {
			String cadenaLeida = (String) entrada.readObject();
			
			while ( (comando = gson.fromJson(cadenaLeida, Mensaje.class)) instanceof MsjDesconectar == false ) {//preguntar si es Desconectar
				
				comando.setListener(this);
				comando.ejecutar();
				
				salida.flush(); 
				cadenaLeida = entrada.readUTF();
			}
			
			
			
		} catch (IOException | ClassNotFoundException e ) {
			System.err.println("Error de conexion con el cliente " + nombreCliente);
			e.printStackTrace();
		}
		
		comando.setListener(this);
		comando.ejecutar();
		
		// DESCONEXION
		// lo borro de las listas de conectado
		// .close() a todo
		// mensaje cliente X desconectado si esta en alguna sala
	
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public ObjectOutputStream getSalida() {
		return salida;
	}

	public Gson getGson() {
		return gson;
	}

	public HashMap<String, Socket> getClientesConectados() {
		return clientesConectados;
	}

	public Sala getLobby() {
		return lobby;
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	public void setSalida(ObjectOutputStream salida) {
		this.salida = salida;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public void setClientesConectados(HashMap<String, Socket> clientesConectados) {
		this.clientesConectados = clientesConectados;
	}

	public void setLobby(Sala lobby) {
		this.lobby = lobby;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}

	public int getSalaActiva() {
		return id_salaActiva;
	}

	public void setSalaActiva(int salaActiva) {
		this.id_salaActiva = salaActiva;
	}

	public void enviarMensaje(Mensaje mensaje) {
		try {
			salida.writeUTF(gson.toJson(mensaje));
		} catch (IOException e) {
			System.err.println("No se pudo enviar el mensaje");
			e.printStackTrace();
		}
	}

	
	
}
