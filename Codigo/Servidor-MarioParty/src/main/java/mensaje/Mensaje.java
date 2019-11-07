package mensaje;

import java.io.Serializable;

import servidor.ListenerThread;

public abstract class Mensaje implements Serializable{

	private static final long serialVersionUID = 1L;
	protected transient ListenerThread serverListener;
	protected String clase;
	protected boolean resultado;
	
	public abstract void ejecutar();
	
	public void setListener(ListenerThread lc) {
		this.serverListener = lc;
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
