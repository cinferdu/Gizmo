package mensaje;

import game.Jugador;

public class MsjPartidaCasillaActivada extends Mensaje {

	private static final long serialVersionUID = 1L;

	public MsjPartidaCasillaActivada(Jugador jugAct) {
		super();
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		
	}

}
