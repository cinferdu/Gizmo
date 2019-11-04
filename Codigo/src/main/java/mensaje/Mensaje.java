package mensaje;

import servidor.ListenerClient;

public abstract class Mensaje {
	
	protected ListenerClient lc;
	
	public abstract void ejecutar();
	
	public void setListener(ListenerClient lc) {
		this.lc = lc;
	}
	
}
