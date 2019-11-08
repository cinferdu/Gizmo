package mensaje;

import casilla.Casilla;
import game.Jugador;

public class MsjPartidaElegirCaminoAccion extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Casilla casillaElegida;
	
	public MsjPartidaElegirCaminoAccion(Jugador jugAct, Casilla casillaElegida) {
		super();
		this.casillaElegida = casillaElegida;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		//listenerServer.notificarPartida(casillaElegida);
		//listenerClient.notificarPartida();
	}

}
