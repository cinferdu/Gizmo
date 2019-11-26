package objeto;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objeto other = (Objeto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
