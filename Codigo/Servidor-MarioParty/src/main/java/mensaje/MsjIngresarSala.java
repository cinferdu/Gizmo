package mensaje;

import servidor.Sala;

public class MsjIngresarSala extends Mensaje {

	private static final long serialVersionUID = 1L;
	
	private Sala sala;

	public MsjIngresarSala(Sala sala) {
		this.setSala(sala);
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		// si alguien pide entrar en una sala
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}
