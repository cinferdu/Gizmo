package paquete;

import mensaje.Mensaje;
import mensaje.MsjCrearSala;
import mensaje.MsjIngresarSala;
import mensaje.MsjLogin;

public class PaqueteToMensaje {
	// se encargar de crear el mensaje correspondiente (con el getComando) y cargarlo con la infomacion (transformando cadenaLeida al paquete que necesite)
	public static Mensaje getMensaje(Paquete paquete, String cadenaLeida) {
		
		Mensaje msj = null;
		switch (paquete.getComando()) {
		case "Login":
			msj = new MsjLogin(cadenaLeida);
			break;
		case "CrearSala":
			msj = new MsjCrearSala(cadenaLeida);
			break;
		case "InformarSala":
			msj = new MsjIngresarSala(cadenaLeida);
			break;
			
		default:
			break;
		}
		return msj;
	}
}
