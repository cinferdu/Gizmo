package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import entornoGrafico.Login;
import game.Jugador;
import game.Partida;
import mensaje.Mensaje;
import sala.Sala;

public class Cliente {

	private final static String IPSERVIDOR = "10.11.3.8";
	private final static int PUERTO = 10200;

	private final static Logger LOGGER = Logger.getLogger(Cliente.class);

	private String nombreCliente;
	Socket readSocket;
	Socket writeSocket;

	private JFrame ventanaActual;
	private Sala salaActual;
	private Partida partidaActual;
	private Jugador jugador;

	DataInputStream entrada;
	DataOutputStream salida;

	private final Gson gson = new Gson();

	public Cliente() {

		try {
			readSocket = new Socket(IPSERVIDOR, PUERTO);
			writeSocket = new Socket(IPSERVIDOR, PUERTO);
			salida = new DataOutputStream(writeSocket.getOutputStream());
			salida.flush();
			entrada = new DataInputStream(readSocket.getInputStream());

		} catch (IOException e) {
			LOGGER.error("Error al iniciar , chequee la conexión al Server");
			System.exit(1);
		}

		new Listener(this).start();

		//ventanaActual = new Login();
		//ventanaActual.setVisible(true);
	}

	public void enviarMensaje(Mensaje mensaje) {
		try {
			salida.writeUTF(gson.toJson(mensaje));
		} catch (IOException e) {
			LOGGER.error("Error al enviar el mensaje al servidor");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SecurityException, IOException {
		new Login();
	}

	public String recibirMsg() {
		String obj = null;
		try {
			obj = entrada.readUTF();
		} catch (IOException e) {
			LOGGER.error("Error al recibir el mensaje");
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

	@Override
	public String toString() {
		return "Cliente [nombreCliente=" + nombreCliente + ", readSocket=" + readSocket + ", writeSocket=" + writeSocket
				+ ", ventanaActual=" + ventanaActual + ", salaActual=" + salaActual + ", partidaActual=" + partidaActual
				+ ", jugador=" + jugador + ", entrada=" + entrada + ", salida=" + salida + ", gson=" + gson + "]";
	}
}
