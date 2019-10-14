package casillas;

import game.Jugador;

public class ConPremio extends TipoDeCasilla {
	
	private static final int ID = 1;

	@Override
	public void activarCasilla(Jugador objetivo) {
		objetivo.setMonedas(objetivo.getMonedas()+5);
	}

	public static int getID() {
		return ID;
	}
}
