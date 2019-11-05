package paquete;

import mensaje.Mensaje;
import mensaje.MsjLogin;
import mensajeRespuesta.RespLogin;

public class PaqueteToMensaje {
	
	public static Mensaje getMensaje(Paquete paquete, String cadenaLeida) {
			
		Mensaje msj = null;
		switch (paquete.getComando()) {
		case "Login":
			msj = new RespLogin(cadenaLeida);
			break;
		default:
			break;
		}
		return msj;
	}
}