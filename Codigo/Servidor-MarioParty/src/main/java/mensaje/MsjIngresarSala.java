package mensaje;

import com.google.gson.Gson;

import paquete.PaqueteCreacionSala;

public class MsjIngresarSala extends Mensaje {

	private static final long serialVersionUID = 1L;

	public MsjIngresarSala(String cadenaLeida) {
		PaqueteCreacionSala paq = new Gson().fromJson(cadenaLeida, PaqueteCreacionSala.class);
	}
	
	@Override
	public void ejecutar() {

	}

}
