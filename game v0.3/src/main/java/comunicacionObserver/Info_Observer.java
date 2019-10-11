package comunicacionObserver;

import game.Jugador;
import game.Partida;

public class Info_Observer {
	private Partida juego;
	private Jugador jugadorActual;
	
	public Info_Observer(Partida partida, Jugador jugador) {
		juego = partida;
		jugadorActual = jugador;
	}

	public Partida getJuego() {
		return juego;
	}

	public void setJuego(Partida juego) {
		this.juego = juego;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}
	
	
}
