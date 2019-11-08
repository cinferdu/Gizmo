package mensaje;

import game.Jugador;

public class MsjPartidaElegirCaminoInformar extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Jugador jugadorActual;
	
	public MsjPartidaElegirCaminoInformar(Jugador jugadorActual) {
		super();
		this.clase = this.getClass().getSimpleName();
		this.jugadorActual = jugadorActual;
	}

	@Override
	public void ejecutar() {
		
	}

}
