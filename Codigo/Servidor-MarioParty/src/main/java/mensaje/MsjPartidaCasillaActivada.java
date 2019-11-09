package mensaje;

import java.util.ArrayList;

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
		
	}

}
