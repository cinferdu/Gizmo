package objeto;

import game.Jugador;

public class GuanteBlanco extends Objeto {

	public GuanteBlanco() {
		super("Guante Blanco", "Robas 5 monedas a otro jugador.");
	}

	@Override
	public void activarEfecto(Jugador jugador) {
		//TODO elegirobjetivo
		
		jugador.aumentarMonedas(5);
	}

}
