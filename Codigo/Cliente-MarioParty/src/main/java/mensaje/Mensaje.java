package mensaje;

import java.io.Serializable;

import cliente.Listener;

public abstract class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -6544865381140109432L;
	protected transient Listener clientListener;
	protected String clase;
	protected boolean resultado;
	
	public abstract void ejecutar();
	
	public void setListener(Listener lc) {
		this.clientListener = lc;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}
	
}
