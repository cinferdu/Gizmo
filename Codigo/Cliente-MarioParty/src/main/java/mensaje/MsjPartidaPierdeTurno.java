package mensaje;

import entornoGrafico.VentanaJuego;
import game.Jugador;

public class MsjPartidaPierdeTurno extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Jugador jugadorAct;

	public MsjPartidaPierdeTurno(Jugador jugAct) {
		super();
		setJugadorAct(jugAct);
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().informar_PerdioTurno(jugadorAct);
	}

	public Jugador getJugadorAct() {
		return jugadorAct;
	}

	public void setJugadorAct(Jugador jugadorAct) {
		this.jugadorAct = jugadorAct;
	}

}
