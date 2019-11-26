package mensaje;

import entornoGrafico.VentanaJuego;
import game.Jugador;

public class MsjPartidaSelecObjAccion extends Mensaje {

	private static final long serialVersionUID = 1L;
	private int objetoElegido;
	private Jugador jugObjetivo;
	private Jugador jugadorAct;

	public MsjPartidaSelecObjAccion(Jugador jugadorAct) {
		this.jugadorAct = jugadorAct;
		this.clase = this.getClass().getSimpleName();
	}

	public MsjPartidaSelecObjAccion(int indexObj, Jugador victima) {
		this.objetoElegido = indexObj;
		this.jugObjetivo = victima;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().mostrarOpcionesObjetos(jugadorAct);
	}

	public int getObjetoElegido() {
		return objetoElegido;
	}

	public Jugador getJugObjetivo() {
		return jugObjetivo;
	}

	public Jugador getJugadorAct() {
		return jugadorAct;
	}

	public void setObjetoElegido(int objetoElegido) {
		this.objetoElegido = objetoElegido;
	}

	public void setJugObjetivo(Jugador jugObjetivo) {
		this.jugObjetivo = jugObjetivo;
	}

	public void setJugadorAct(Jugador jugadorAct) {
		this.jugadorAct = jugadorAct;
	}

}
