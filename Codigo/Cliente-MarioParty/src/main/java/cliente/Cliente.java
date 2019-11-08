package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

import com.google.gson.Gson;

import entornoGrafico.Login;
import game.Jugador;
import game.Partida;
import mensaje.Mensaje;

public class Cliente {

	private String nombreCliente;
	Socket readSocket;
	Socket writeSocket;

	private JFrame ventanaActual;
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

		} catch (IOException e) {
			System.err.println("Error al iniciar , chequee la conexión al Server");
			System.exit(1);
		}

		salaActual = null;
		partidaActual = null;
		jugador = null;

		new Listener(this).start();
		// System.out.println("escuchando");

		ventanaActual = new Login(this);
		ventanaActual.setVisible(true);

	}

	public void enviarMensaje(Mensaje mensaje) {
		try {
			salida.writeUTF(gson.toJson(mensaje));
		} catch (IOException e) {

			System.err.println("Error al enviar el mensaje al servidor");
			e.printStackTrace();
		}
	}
	
	public static void test(String msj) {
		System.out.println("****" + msj);
	}

	public static void main(String[] args) {
		new Cliente("127.0.0.1", 10200);
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

	public String getNombreCliente() {
		return nombreCliente;
	}

	public JFrame getVentanaActual() {
		return ventanaActual;
	}

	public Sala getSalaActual() {
		return salaActual;
	}

	public Partida getPartidaActual() {
		return partidaActual;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public void setVentanaActual(JFrame ventanaActual) {
		this.ventanaActual = ventanaActual;
	}

	public void setSalaActual(Sala salaActual) {
		this.salaActual = salaActual;
	}

	public void setPartidaActual(Partida partidaActual) {
		this.partidaActual = partidaActual;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

}
