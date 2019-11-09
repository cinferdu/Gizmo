package mensaje;

import game.Jugador;

public class MsjPartidaSelecObjAccion extends Mensaje {

	private static final long serialVersionUID = 1L;
	private int objetoElegido;
	private Jugador jugObjetivo;
	private Jugador jugadorAct;

	public MsjPartidaSelecObjAccion(Jugador jugadorAct) {
		this.setJugadorAct(jugadorAct);
		this.clase = this.getClass().getSimpleName();
	}

	public MsjPartidaSelecObjAccion(int indexObj, Jugador victima) {
		this.objetoElegido = indexObj;
		this.jugObjetivo = victima;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		listenerServer.notificarCasillaElegina(objetoElegido, jugObjetivo);
	}

	public Jugador getJugadorAct() {
		return jugadorAct;
	}

	public void setJugadorAct(Jugador jugadorAct) {
		this.jugadorAct = jugadorAct;
	}

}
