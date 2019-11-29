package mensaje;

import entornoGrafico.VentanaJuego;
import game.Jugador;

public class MsjPartidaPuntajesFinales extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Jugador jugadorActual;

	public MsjPartidaPuntajesFinales() {
		this.clase = getClass().getSimpleName();
	}
	
	public MsjPartidaPuntajesFinales(Jugador jugAct) {
		this.clase = getClass().getSimpleName();
		this.jugadorActual = jugAct;
	}

	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel()
				.mostrarVentanaPuntajesFinales(jugadorActual);
	}

}
