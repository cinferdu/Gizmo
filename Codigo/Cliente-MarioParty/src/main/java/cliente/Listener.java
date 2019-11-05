package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JFrame;

import com.google.gson.Gson;

import game.Jugador;
import game.Partida;
import paquete.Paquete;

public class Listener extends Thread {
	
	private Cliente cliente;
	private DataInputStream leer;
	
	public Listener(Cliente cliente) {
		this.cliente = cliente;
		leer = cliente.entrada; 
	}
	
	@Override
	public void run() {
		while (true) {			
			try {
				Cliente.test("todo el mundo");
				String cadenaLeida = cliente.recibirMsg();//leer.readUTF();
				Paquete comando = new Gson().fromJson(cadenaLeida, Paquete.class);
				Cliente.test("comando");
				cliente.ventanaActual.dispose();
				
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
