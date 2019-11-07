package mensaje;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;

import servidor.Sala;

												// 	Rehacer //
public class MsjDesconectar extends Mensaje {

	private static final long serialVersionUID = -7264591593086243786L;

	@Override
	public void ejecutar() {
		String nombre = serverListener.getNombreCliente();
		HashMap<String, DataOutputStream> clientes = serverListener.getClientesConectados();

		synchronized (clientes) {
			clientes.remove(nombre);
		}
		int idsala = serverListener.getSalaActiva();
		if (idsala < 0) {
			Sala sala = serverListener.getSalas().get(idsala);
			sala.removeCliente(nombre);
			for (String nombreJugador : sala.getNombreJugadores()) {
				try {
					new DataOutputStream(clientes.get(nombreJugador)).writeUTF(new Gson().toJson("se desconecto " + nombre));
				} catch (IOException e) {
					System.err.println("No se pudo enviar el mensaje");
					e.printStackTrace();
				}
			}
		}
	}

}
