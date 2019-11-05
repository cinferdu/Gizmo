package mensaje;

import java.io.DataOutputStream;
import java.util.HashMap;

import com.google.gson.Gson;

import paquete.PaqueteLogin;

public class MsjLogin extends Mensaje {

	private static final long serialVersionUID = -3357984417586718588L;
	private String nombre;

	public MsjLogin(String cadenaLeida) {
		PaqueteLogin paq = new Gson().fromJson(cadenaLeida, PaqueteLogin.class);
		nombre = paq.getNombre();
	}

	@Override
	public void ejecutar() {
		HashMap<String, DataOutputStream> clientes = lc.getClientesConectados(); // Modif
		boolean resultado = !clientes.containsKey(nombre);

		PaqueteLogin aEnviar = new PaqueteLogin(nombre);
		aEnviar.setResultado(resultado);
		if (resultado) {
			clientes.put(nombre, lc.getSalida());
			lc.setNombreCliente(nombre);
			aEnviar.setSalas(lc.getSalas());
			lc.enviarMensaje(aEnviar);
		} else {
			lc.enviarMensaje(aEnviar);
		}
	}

}
