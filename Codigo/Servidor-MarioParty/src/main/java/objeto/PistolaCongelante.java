package objeto;

import game.Jugador;

public class PistolaCongelante extends Objeto {

	public PistolaCongelante() {
		super("Pistola Congelante", "Otro jugador pierde 1 turno", true);
	}

	public void activarEfecto(Jugador jugador) {
		victima.setPierdeTurno(true);
	}

}
