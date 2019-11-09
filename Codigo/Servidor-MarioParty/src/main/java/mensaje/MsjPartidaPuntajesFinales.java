package mensaje;

import java.util.ArrayList;

import game.Jugador;

public class MsjPartidaPuntajesFinales extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Jugador jugadorActual;

	public MsjPartidaPuntajesFinales(Jugador jugAct, ArrayList<Jugador> players) {
		super();
		this.clase = getClass().getSimpleName();
		setJugadorActual(jugAct);
	}

	@Override
	public void ejecutar() {
		
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

}
