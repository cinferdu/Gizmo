package objeto;

import game.Jugador;

public class GuanteBlanco extends Objeto {

	public GuanteBlanco() {
		super("Guante Blanco", "Robas 5 monedas a otro jugador.", true);
	}

	@Override
	public void activarEfecto(Jugador jugador) {
		if (victima.getMonedas() >= 5) {
			jugador.aumentarMonedas(5);
		} else {
			jugador.aumentarMonedas(victima.getMonedas());
		}
		// si tiene 5 o menos le quedaran 0 monedas
		victima.decrementarMonedas(5);
	}

}
