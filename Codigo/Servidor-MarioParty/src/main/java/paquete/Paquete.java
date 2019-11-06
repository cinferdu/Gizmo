package paquete;

import java.io.Serializable;

public class Paquete implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tipo_msj;

	protected boolean resultado;

	public Paquete(String tipo) {
		tipo_msj = tipo;
	}
	
	public String getComando() {
		return tipo_msj;
	}

	public void setComando(String comando) {
		this.tipo_msj = comando;
	}
	
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	
}
