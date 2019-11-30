package mensaje;

import java.util.TreeMap;

import sala.Sala;
import servidor.Servidor;

public class MsjIngresarLobby extends Mensaje {

	private static final long serialVersionUID = 1L;

	private TreeMap<Integer, Sala> salas;
	private boolean espectador;

	public MsjIngresarLobby() {
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		this.salas = listenerServer.getSalas();
		int idsala = listenerServer.getSalaActiva();
		int idpartida = listenerServer.getId_partidaActiva();
		if (espectador) { // si un espectador solicito volver al lobby
			Servidor.partidas.get(listenerServer.getId_partidaEspectador()).removeSpect(listenerServer.getNombreCliente());
		}
		if (idsala != -1) {
			listenerServer.getSalas().get(idsala).removeCliente(listenerServer.getNombreCliente());
		}
		if (idpartida != -1) {
			listenerServer.desconectarJugadorDePartida();
		}
		listenerServer.setSalaActiva(0);
		listenerServer.getLobby().addCliente(listenerServer.getNombreCliente());
		listenerServer.enviarMensaje(this);
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}

	public boolean isEspectador() {
		return espectador;
	}

	public void setEspectador(boolean espectador) {
		this.espectador = espectador;
	}
	
	
}