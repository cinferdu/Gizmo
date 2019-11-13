package mensaje;

import game.Jugador;

public class MsjPartidaSelecObjInf extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Jugador jugadorAct;
	
	public MsjPartidaSelecObjInf(Jugador jugadorAct) {
		this.jugadorAct = jugadorAct;
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub

	}

	public Jugador getJugadorAct() {
		return jugadorAct;
	}

	public void setJugadorAct(Jugador jugadorAct) {
		this.jugadorAct = jugadorAct;
	}
}
