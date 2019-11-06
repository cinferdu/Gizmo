package mensaje;

import java.io.DataOutputStream;
import java.util.HashMap;

import com.google.gson.Gson;

import paquete.PaqueteLogin;

public class MsjLogin extends Mensaje {

	private static final long serialVersionUID = 1L;
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
		aEnviar.setSalas(lc.getSalas());
		
		if (resultado) {
			clientes.put(nombre, lc.getSalida());
			lc.setNombreCliente(nombre);
			lc.getLobby().addCliente(nombre);
			aEnviar.setSalas(lc.getSalas());
			lc.enviarPaquete(aEnviar);
		} else {
			lc.enviarPaquete(aEnviar);
		}
	}

}
