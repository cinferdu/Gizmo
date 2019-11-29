package mensaje;

import java.util.TreeMap;

import sala.Sala;
import servidor.Servidor;

public class MsjIngresarLobby extends Mensaje {

	private static final long serialVersionUID = 1L;

	private TreeMap<Integer, Sala> salas;
	
	public MsjIngresarLobby() {
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		this.salas = listenerServer.getSalas();
		int idsala = listenerServer.getSalaActiva();
		if (resultado) { // si un espectador solicito volver al lobby
			Servidor.partidas.get(listenerServer.getId_partidaEspectador()).removeSpect(listenerServer.getNombreCliente());
		}
		if (idsala != -1) {
			listenerServer.getSalas().get(idsala).removeCliente(listenerServer.getNombreCliente());
		}
		listenerServer.setSalaActiva(0);
		listenerServer.enviarMensaje(this);
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}
	
}