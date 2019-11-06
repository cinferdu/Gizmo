package paquete;

import mensaje.Mensaje;
import mensajeRespuesta.RespLogin;
import mensajeRespuesta.RespSala;

public class PaqueteToMensaje {
	// se encargar de crear el mensaje correspondiente (con el getComando) y cargarlo con la infomacion (transformando cadenaLeida al paquete que necesite)
	public static Mensaje getMensaje(Paquete paquete, String cadenaLeida) {
		
		Mensaje msj = null;
		switch (paquete.getTipoMensaje()) {
		case "Login":
			msj = new RespLogin(cadenaLeida);
			break;
		case "CrearSala":
		case "IngresarSala":
			msj = new RespSala(cadenaLeida);
			break;
		default:
			break;
		}
		return msj;
	}
}