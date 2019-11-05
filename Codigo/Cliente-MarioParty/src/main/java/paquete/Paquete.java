package paquete;

import java.io.Serializable;

public class Paquete implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tipo_msj;

	public Paquete(String cadena) {
		tipo_msj = cadena;
	}
	
	public String getComando() {
		return tipo_msj;
	}

	public void setComando(String comando) {
		this.tipo_msj = comando;
	}
	
	
	
}
