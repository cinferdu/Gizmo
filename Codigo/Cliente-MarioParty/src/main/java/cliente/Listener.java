package cliente;

import java.io.DataInputStream;

import com.google.gson.Gson;

import mensaje.Mensaje;
import paquete.Paquete;
import paquete.PaqueteToMensaje;

public class Listener extends Thread {
	
	private Cliente cliente;
	private DataInputStream leer;
	
	public Listener(Cliente cliente) {
		this.cliente = cliente;
		leer = cliente.entrada; 
	}
	
	@Override
	public void run() {
		
		Mensaje msj = null;
		
		while (true) {
			try {
				String cadenaLeida = leer.readUTF();
				Paquete paquete = new Gson().fromJson(cadenaLeida, Paquete.class);
				msj = PaqueteToMensaje.getMensaje(paquete, cadenaLeida);
				
				msj.setListener(this);
				msj.ejecutar();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
