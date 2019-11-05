package mensajeCliente;

import java.io.Serializable;

public class Paquete implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	
	public Paquete(String nombre) {
		this.nombre = nombre;
	}
}
