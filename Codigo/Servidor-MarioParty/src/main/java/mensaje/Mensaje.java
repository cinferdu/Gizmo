package mensaje;

import java.io.Serializable;

import servidor.ListenerThread;

public abstract class Mensaje implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8121363586181763380L;
	protected ListenerThread lc;
	
	public abstract void ejecutar();
	
	public void setListener(ListenerThread lc) {
		this.lc = lc;
	}
	
}
