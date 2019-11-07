package cliente;

import java.io.DataInputStream;

import mensaje.Mensaje;

public class Listener extends Thread {
	
	private Cliente cliente;
	private DataInputStream leer;
	
	public Listener(Cliente cliente) {
		this.cliente = cliente;
		leer = cliente.entrada; 
	}
	
	@Override
	public void run() {
		boolean escuchando = true;
		Mensaje msj = null;
		
		while (escuchando) {
			try {
				String cadenaLeida = leer.readUTF();
				msj = Mensaje.getMensaje(cadenaLeida);
				
				msj.setListener(this);
				msj.ejecutar();
			}
			catch (Exception ex) {
				escuchando = false;
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
