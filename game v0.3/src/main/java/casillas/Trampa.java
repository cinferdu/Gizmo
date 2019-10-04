package casillas;

import game2.Jugador;

public class Trampa implements TipoDeCasilla {

	public void activarCasilla(Jugador objetivo) {
		objetivo.setPierdeTurno(true);
	}

}
