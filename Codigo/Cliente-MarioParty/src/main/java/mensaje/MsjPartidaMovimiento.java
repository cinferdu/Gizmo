package mensaje;

import game.Jugador;

public class MsjPartidaMovimiento extends Mensaje {

	private static final long serialVersionUID = 1L;

	public MsjPartidaMovimiento(Jugador jugAct) {
		super();
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		
	}

}
