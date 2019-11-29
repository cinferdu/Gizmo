package mensaje;

import servidor.PartidaThread;
import servidor.Servidor;

public class MsjPartidaEspectador extends Mensaje {

	private static final long serialVersionUID = 3911245659375705969L;
	private int id;
	
	@Override
	public void ejecutar() {
		PartidaThread gameThread = Servidor.partidas.get(id);
		MsjIniciarPartida mensaje = new MsjIniciarPartida(gameThread.getPartida());
		mensaje.resultado = true;
		listenerServer.enviarMensaje(mensaje);
		listenerServer.setId_partidaEspectador(gameThread.getPartida().getIdpartida());
		listenerServer.setSalaActiva(-1);
		gameThread.addSpec(listenerServer.getNombreCliente());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
