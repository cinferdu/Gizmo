package mensaje;

import java.io.Serializable;

import cliente.Listener;

public abstract class Mensaje implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6544865381140109432L;
	protected Listener listener;
	
	public abstract void ejecutar();
	
	public void setListener(Listener lc) {
		this.listener = lc;
	}
	
	
}
