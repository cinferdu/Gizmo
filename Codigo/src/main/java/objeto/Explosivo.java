package objeto;

import game.Jugador;

public class Explosivo extends Objeto {

	public Explosivo() {
		super("Explosivo", "Otro jugador pierde 1 turno",false);
	}

	public void activarEfecto(Jugador jugador) {
		victima.setPierdeTurno(true);
	}

}
