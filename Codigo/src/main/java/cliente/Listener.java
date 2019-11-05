package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JFrame;

import game.Jugador;
import game.Partida;
import servidor.Sala;

public class Listener extends Thread {
	
	private Cliente cliente;
	
	public Listener(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		while (true) {			
			try {
				//Recibo mensajes del servidor 
				//Msg msgRecibido = (Msg) cliente.recibirMsg();
				//Ejecuto la acción
				//msgRecibido.realizarAccion(cliente);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
