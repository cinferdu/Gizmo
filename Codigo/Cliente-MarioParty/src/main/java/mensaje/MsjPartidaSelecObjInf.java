package mensaje;

import entornoGrafico.VentanaJuego;
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
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().seleccionarAccionMensaje(jugadorAct);

	}

}
