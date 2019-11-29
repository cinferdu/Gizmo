package minijuego;

public abstract class Minijuego {
	private String nombre;
	
	public Minijuego(String nombre) {
		this.nombre = nombre;
	}

	public abstract void ejecutar();
}
