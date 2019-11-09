package mensaje;

import java.util.ArrayList;

import entornoGrafico.VentanaJuego;
import game.Jugador;

public class MsjPartidaCasillaActivada extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Jugador jugadorActual;
	
	public MsjPartidaCasillaActivada(Jugador jugadorActual) {
		super();
		this.clase = this.getClass().getSimpleName();
		this.jugadorActual = jugadorActual;
	}

	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().casilla_activada(jugadorActual);
		
	}

}
