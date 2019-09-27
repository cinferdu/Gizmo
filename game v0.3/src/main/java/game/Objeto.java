package game;

public class Objeto {
	private int tipo;
	private String nombre;
	private String descripcion;
	
	public Objeto(int tipo) {
		
		setTipo(tipo);
		// Defino el objeto segun el tipo
		
		if (tipo==0) {
			setNombre("Bolsa de monedas");
			setDescripcion("Agrega 10 monedas");
		}
		if (tipo==1) {
			setNombre("Bolsa de puntos");
			setDescripcion("Agrega 100 puntos");
		}
		
	}

	public void efecto(Jugador jugador) {
		// Activa el efecto segun el tipo establecido
		
		if (tipo == 0)
			jugador.setMonedas(jugador.getMonedas()+10);
		if (tipo==1)
			jugador.setPuntos(jugador.getPuntos()+100);
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
