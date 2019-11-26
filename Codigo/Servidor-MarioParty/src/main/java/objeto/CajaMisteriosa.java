package objeto;

import game.Jugador;

public class CajaMisteriosa extends Objeto {

	public CajaMisteriosa() {
		super("Caja Misteriosa", "? ? ? ?", false);
	}

	public void activarEfecto(Jugador jugador) {
		int cantidad = (int) (Math.random() * 5); // [-4, 4]

		if (Math.random() < 0.5) {
			jugador.decrementarMonedas(cantidad);
		} else {
			jugador.aumentarMonedas(cantidad);
		}
	}
}
