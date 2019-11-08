package mensaje;

import java.io.Serializable;

import cliente.Listener;
import game.Jugador;

public abstract class MsjPartida implements Serializable {

	private static final long serialVersionUID = 1L;
	protected transient Listener listenerClient;
	protected String clase;
	protected Jugador jugadorActual;

	public MsjPartida(Jugador jugAct) {
		this.jugadorActual = jugAct;
	}
	
	public abstract void ejecutar();

	public void setListener(Listener lc) {
		this.listenerClient = lc;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}
}
