package casilla;

import java.awt.Color;

import game.Jugador;

public class Trampa extends TipoDeCasilla {
	private static final int ID = 2;
	
	public Trampa() {
		super(Color.RED, "con Trampa");
	}
	
	@Override
	public void activarCasilla(Jugador objetivo) {
		objetivo.setPierdeTurno(true);
	}
	
	public static int getID() {
		return ID;
	}
}
