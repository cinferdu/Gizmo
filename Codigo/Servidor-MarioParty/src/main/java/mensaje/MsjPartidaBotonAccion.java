package mensaje;

import game.Jugador;

public class MsjPartidaBotonAccion extends Mensaje {

	private static final long serialVersionUID = 1L;

	public MsjPartidaBotonAccion(Jugador jugAct) {
		super();
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		listenerServer.notificarPartida();
	}

}
