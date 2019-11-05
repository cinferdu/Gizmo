package mensaje;

import java.net.Socket;
import java.util.HashMap;

import mensajeRespuesta.RespLogin;

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
		
		if (resultado) {
			lc.setNombreCliente(nombre);
			lc.enviarMensaje(new RespLogin(resultado,lc.getSalas()));
		}else {
			lc.enviarMensaje(new RespLogin(resultado,null));
		}
		
	}

}
