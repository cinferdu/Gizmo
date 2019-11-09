package cliente;

import java.io.DataInputStream;

import org.apache.log4j.Logger;

import mensaje.Mensaje;

public class Listener extends Thread {
	
	private final static Logger LOGGER = Logger.getLogger(Listener.class);
	
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
				LOGGER.info(cliente);
				String cadenaLeida = leer.readUTF();
				msj = Mensaje.getMensaje(cadenaLeida);
				
				msj.setListener(this);
				msj.ejecutar();
			}
			catch (Exception ex) {
				escuchando = false;
				LOGGER.error(ex.getStackTrace());
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
