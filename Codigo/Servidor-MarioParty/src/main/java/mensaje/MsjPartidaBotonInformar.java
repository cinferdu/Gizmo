package mensaje;

import game.Jugador;

public class MsjPartidaBotonInformar extends Mensaje {

	public MsjPartidaBotonInformar(Jugador jugAct) {
		super();
		this.clase = this.getClass().getSimpleName();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void ejecutar() {
		
	}

}
