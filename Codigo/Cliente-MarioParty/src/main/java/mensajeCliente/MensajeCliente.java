package mensajeCliente;

import java.io.Serializable;

import cliente.Listener;

public abstract class MensajeCliente implements Serializable{
	
	private static final long serialVersionUID = 4266451488681579070L;
	protected Listener listener;
	
	public abstract void ejecutar();
	
	public void setListener(Listener lc) {
		this.listener = lc;
	}
	
}
