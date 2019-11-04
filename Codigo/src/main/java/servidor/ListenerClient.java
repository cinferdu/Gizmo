package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import mensaje.Mensaje;
import mensaje.MsjDesconectar;

public class ListenerClient extends Thread {
	private String nombreCliente;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private Gson gson = new Gson();
	private HashMap<String, Socket> clientesConectados;
	private ArrayList<Sala> lobby;
	private ArrayList<Sala> salas;
	
	public ListenerClient(Socket cliente, HashMap<String, Socket> clientesConectados, ArrayList<Sala> lobby, ArrayList<Sala> salas) {
		try {
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			System.err.println("Error cliente");
			e.printStackTrace();
		}
		this.clientesConectados = clientesConectados;
		this.lobby = lobby;
		this.salas = salas;
	}
	
	@Override
	public void run() {
		Mensaje comando;
		
		try {
			String cadenaLeida = entrada.readUTF();
			
			while ( (comando = gson.fromJson(cadenaLeida, Mensaje.class)) instanceof MsjDesconectar == false ) {//preguntar si es Desconectar
				
				comando.setListener(this);
				comando.ejecutar();
				
				salida.flush(); // limpio
				cadenaLeida = entrada.readUTF();
			}
			
			
			
		} catch (IOException e) {
			System.out.println("Hubo un error de conexion");
			e.printStackTrace();
		}
			// DESCONEXION
			// lo borro de las listas de conectado
			// .close() a todo
			// mensaje cliente X desconectado si esta en alguna sala
		
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public DataInputStream getEntrada() {
		return entrada;
	}

	public DataOutputStream getSalida() {
		return salida;
	}

	public Gson getGson() {
		return gson;
	}

	public HashMap<String, Socket> getClientesConectados() {
		return clientesConectados;
	}

	public ArrayList<Sala> getLobby() {
		return lobby;
	}

	public ArrayList<Sala> getSalas() {
		return salas;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public void setEntrada(DataInputStream entrada) {
		this.entrada = entrada;
	}

	public void setSalida(DataOutputStream salida) {
		this.salida = salida;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public void setClientesConectados(HashMap<String, Socket> clientesConectados) {
		this.clientesConectados = clientesConectados;
	}

	public void setLobby(ArrayList<Sala> lobby) {
		this.lobby = lobby;
	}

	public void setSalas(ArrayList<Sala> salas) {
		this.salas = salas;
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
