package mensaje;

import java.io.Serializable;

import game.Jugador;
import servidor.ListenerThread;

public abstract class MsjPartida implements Serializable {

	private static final long serialVersionUID = 1L;
	protected transient ListenerThread listenerServer;
	protected String clase;
	protected Jugador jugadorActual;

	public MsjPartida(Jugador jugAct) {
		this.jugadorActual = jugAct;
	}
	
	public abstract void ejecutar();

	public void setListener(ListenerThread lc) {
		this.listenerServer = lc;
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
