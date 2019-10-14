package casillas;

import game.Jugador;

public class Trampa extends TipoDeCasilla {

	private static final int ID = 2;
	
	@Override
	public void activarCasilla(Jugador objetivo) {
		objetivo.setPierdeTurno(true);
	}

	public static int getID() {
		return ID;
	}

}
