package paquete;

import java.io.Serializable;

public class Paquete implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String comando;

	public String getComando() {
		return comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}
	
	
}
