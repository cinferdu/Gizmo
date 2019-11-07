package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

import com.google.gson.Gson;

import entornoGrafico.Login;
import game.Jugador;
import game.Partida;
import mensaje.Mensaje;
import mensajeCliente.MsjClienteLogin;
import mensajeCliente.Paquete;
import servidor.Sala;

public class Cliente {

	private String nombreCliente;
	private Socket cliente;
	
	private JFrame ventanaActual;
	private Sala salaActual;
	private Partida partidaActual;
	private Jugador jugador; // jugador en la partida???
	
	private DataInputStream entrada;
	private DataOutputStream salida;
	
	private final Gson gson = new Gson();

	public Cliente(String ipServidor, int puerto) {

		try {
			System.out.println("1");
			cliente = new Socket(ipServidor, puerto);
			System.out.println("2");
			entrada = new DataInputStream(cliente.getInputStream());
			System.out.println("3");
			salida = new DataOutputStream(cliente.getOutputStream());
			System.out.println("4");
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
	
	public void enviarMensaje(Paquete msjClienteLogin) {
		try {
			salida.writeUTF(gson.toJson(msjClienteLogin));
		} catch (IOException e) {
			System.err.println("Error al enviar el mensaje al servidor");
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		new Cliente("localhost",15000);
	}

}
