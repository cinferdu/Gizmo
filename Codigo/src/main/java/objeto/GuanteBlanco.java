package objeto;

import game.Jugador;

public class GuanteBlanco extends Objeto {

	public GuanteBlanco() {
		super("Guante Blanco", "Robas 5 monedas a otro jugador.", false);
	}

	@Override
	public void activarEfecto(Jugador jugador) {
		jugador.aumentarMonedas(5);
	}

}
