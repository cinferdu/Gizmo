package objetos;

import java.util.List;

import game.Jugador;

public abstract class Objeto {
	protected String nombre;
	protected String descripcion;
	
	public Objeto(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Jugador elegirObjetivo(List<Jugador> jugadores, Jugador jugadorActual) {
		return jugadorActual;
	}
	
	public abstract void activarEfecto(Jugador jugador);
	
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	
}
