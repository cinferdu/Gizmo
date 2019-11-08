package mensaje;

import java.util.ArrayList;
import java.util.List;

import casilla.Casilla;
import game.Jugador;

public class MsjPartidaElegirCaminoAccion extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Casilla casillaElegida;
	private ArrayList<Casilla> caminosPosibles;
	private Jugador jugadorActual;
	
	public MsjPartidaElegirCaminoAccion(Jugador jugAct, Casilla casillaElegida) {
		super();
		this.casillaElegida = casillaElegida;
		jugadorActual = jugAct;
		this.clase = this.getClass().getSimpleName();
	}
	
	public MsjPartidaElegirCaminoAccion(Jugador jugAct, List<Casilla> caminosPosibles) {
		super();
		this.caminosPosibles = (ArrayList<Casilla>) caminosPosibles;
		jugadorActual = jugAct;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		listenerServer.notificarCasillaElegina(casillaElegida);
	}

}
