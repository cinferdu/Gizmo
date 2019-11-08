package mensaje;

import game.Jugador;

public class MsjPartidaMovimiento extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Jugador jugadorActual;
	
	public MsjPartidaMovimiento(Jugador jugAct) {
		super();
		this.setJugadorActual(jugAct);
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

}
