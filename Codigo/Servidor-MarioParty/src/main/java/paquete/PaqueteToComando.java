package paquete;

import mensaje.Mensaje;
import mensaje.MsjLogin;

public class PaqueteToComando {
	public static Mensaje getMensaje(String paquete) {
		return new MsjLogin();
	}
}
