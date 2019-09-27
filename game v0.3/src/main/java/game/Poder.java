package game;

public class Poder {
	private int tipo;
	private String nombre;
	private String descripcion;
	
	public Poder(int tipo) {
		this.tipo = tipo;
	}

	public void efecto(Jugador jugador) {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
