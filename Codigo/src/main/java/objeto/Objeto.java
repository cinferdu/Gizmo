package objeto;

import java.util.List;

import game.Jugador;

public abstract class Objeto {
	protected String nombre;
	protected String descripcion;
	protected boolean conObjetivo;
	protected Jugador victima;

	public Objeto(String nombre, String descripcion, boolean conObjetivo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.conObjetivo = conObjetivo;
		this.victima = null;
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

	public boolean isConObjetivo() {
		return conObjetivo;
	}

	public Jugador getVictima() {
		return victima;
	}

	public void setVictima(Jugador victima) {
		this.victima = victima;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
