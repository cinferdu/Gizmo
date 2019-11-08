package mensaje;

import entornoGrafico.VentanaJuego;
import game.Jugador;
import game.Partida;

public class MsjPartidaMovimiento extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Jugador jugadorActual;

	public MsjPartidaMovimiento(Jugador jugAct) {
		super();
		this.clase = this.getClass().getSimpleName();
		jugadorActual = jugAct;
		
	}

	@Override
	public void ejecutar() {
		Partida game = listenerClient.getCliente().getPartidaActual();
		for (Jugador jugador : game.getJugadores()) {
			if (jugador.getNombre().equals(jugadorActual.getNombre())) {
				jugador.setPosicionActual(jugadorActual.getPosicionActual());
			}
		}/*
		for (int i = 0; i < game.getJugadores().size(); i++) {
			if (game.getJugadores().get(i).getNombre() == jugadorActual.getNombre()) {
				game.getJugadores().get(i).setPosicionActual(jugador.getPosicionActual());
			}
		}*/
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().movimiento();
	}

}
