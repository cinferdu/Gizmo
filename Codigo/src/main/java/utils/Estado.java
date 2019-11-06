package utils;

public enum Estado {
	ABIERTA("Abierta",0),
	CERRADA("Cerrada",1);
	
	private String nombre;
	private int id;
	
	private Estado(String nombre, int id) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
