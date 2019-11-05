package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

import com.google.gson.Gson;

import entornoGrafico.Login;
import game.Jugador;
import game.Partida;

public class Cliente {

	private String nombreCliente;
	 Socket readSocket;
	 Socket writeSocket;
	
	JFrame ventanaActual;
	private Sala salaActual;
	private Partida partidaActual;
	private Jugador jugador; // jugador en la partida???
	
	DataInputStream entrada;
	DataOutputStream salida;
	
	private final Gson gson = new Gson();

	public Cliente(String ipServidor, int puerto) {

		try {
			readSocket = new Socket(ipServidor, puerto);
			writeSocket = new Socket(ipServidor, puerto);
			salida = new DataOutputStream(writeSocket.getOutputStream());
			salida.flush();
			entrada = new DataInputStream(readSocket.getInputStream()); 
			System.out.println("sockets iniciados");

			Listener escucha = new Listener(this);
			escucha.start();
			System.out.println("escuchando");
		} catch (IOException e) {
			System.err.println("Error al iniciar , chequee la conexión al Server");
			System.exit(1);
			e.printStackTrace();
		}
		
		salaActual = null;
		partidaActual = null;
		jugador = null;
		
		new Listener(this).start();
		
		ventanaActual = new Login(this);
		ventanaActual.setVisible(true);
		
	}
	
	public void enviarMensaje(Object msjClienteLogin) {
		try {
			salida.writeUTF(gson.toJson(msjClienteLogin));
		} catch (IOException e) {
			
			System.err.println("Error al enviar el mensaje al servidor");
			e.printStackTrace();
		}
	}
	
	public static void test(String msj) {
		System.out.println("****" + msj);
	}
	
	public static void main(String[] args) {
		new Cliente("192.168.1.33",10200);
	}
	
	public String recibirMsg() {
		String obj = null;
		try {
			obj = entrada.readUTF();
		} catch (IOException e) {
			System.err.println("Error al recibir el mensaje");
		}
		return obj;
	}

}
