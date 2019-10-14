package casilla;

import java.awt.Color;

import game.Jugador;

public class ConPremio extends TipoDeCasilla {
	private static final int ID = 1;

	public ConPremio() {
		super(Color.BLUE, "Casilla con Premio");
	}

	@Override
	public void activarCasilla(Jugador objetivo) {
		objetivo.setMonedas(objetivo.getMonedas()+5);
	}
	
	public static int getID() {
		return ID;
	}
	
}
