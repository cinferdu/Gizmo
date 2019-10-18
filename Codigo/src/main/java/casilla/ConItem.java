package casilla;

import java.awt.Color;

import game.Jugador;
import objeto.GeneradorObjetoRandom;

public class ConItem extends TipoDeCasilla {
	private static final int ID = 3;
	
	public ConItem() {
		super(Color.PINK, "con Item");
	}

	@Override
	public void activarCasilla(Jugador objetivo) {
		objetivo.addMochila_objetos(GeneradorObjetoRandom.generar());
	}
	
	public static int getID() {
		return ID;
	}
}