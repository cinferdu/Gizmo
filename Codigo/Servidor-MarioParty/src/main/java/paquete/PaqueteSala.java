package paquete;

import servidor.Sala;

public class PaqueteSala extends Paquete {

	private static final long serialVersionUID = 1L;
	private Sala sala;
	
	public PaqueteSala(Sala sala) {
		super("IngresarSala");
		this.setSala(sala);
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
}
