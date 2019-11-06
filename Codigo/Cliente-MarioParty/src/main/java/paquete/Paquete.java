package paquete;

import java.io.Serializable;

public class Paquete implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tipo_msj;

	protected boolean resultado;

	public Paquete(String tipo) {
		tipo_msj = tipo;
	}
	
	public String getTipoMensaje() {
		return tipo_msj;
	}

	public void setTipoMensaje(String tipo) {
		this.tipo_msj = tipo;
	}
	
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public boolean isResultado() {
		return resultado;
	}
	
}
