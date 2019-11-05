package mensaje;

import java.net.Socket;
import java.util.HashMap;

import mensajeRespuesta.RespLogin;
import paquete.PaqueteLogin;
import servidor.Servidor;

public class MsjLogin extends Mensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3357984417586718588L;
	private String nombre;

	@Override
	public void ejecutar() {
		HashMap<String, Socket> clientes = lc.getClientesConectados(); // Modif
		boolean resultado = !clientes.containsKey(nombre);
		
		PaqueteLogin aEnviar = new PaqueteLogin(nombre);
		aEnviar.setResultado(resultado);
		if (resultado) {
			lc.setNombreCliente(nombre);
			lc.enviarMensaje(aEnviar);
		}else {
			lc.enviarMensaje(aEnviar);
		}
	}

}
