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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + tipo;
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
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	
}
