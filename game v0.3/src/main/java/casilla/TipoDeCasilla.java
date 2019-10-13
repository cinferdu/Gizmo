package casilla;

import java.awt.Color;

import game.Jugador;

public abstract class TipoDeCasilla {
	private Color color_casilla;
	private String nombre;
	
	public TipoDeCasilla(Color color, String nombre) {
		this.color_casilla = color;
		this.nombre = nombre;
	}
	
	public abstract void activarCasilla(Jugador objetivo);
	
	public Color getColor() {
		return color_casilla;
	}
	public String getNombre() {
		return nombre;
	}
	
}
