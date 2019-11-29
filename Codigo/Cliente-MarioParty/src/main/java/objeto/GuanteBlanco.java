package objeto;

import game.Jugador;

public class GuanteBlanco extends Objeto {

	public GuanteBlanco() {
		super("Guante Blanco", "Robas 5 monedas a otro jugador.", true);
	}

	@Override
	public void activarEfecto(Jugador jugador) {
	}

}
