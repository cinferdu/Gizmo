package mensaje;

import servidor.ListenerThread;

public abstract class Mensaje {
	
	protected ListenerThread lc;
	
	public abstract void ejecutar();
	
	public void setListener(ListenerThread lc) {
		this.lc = lc;
	}
	
}
