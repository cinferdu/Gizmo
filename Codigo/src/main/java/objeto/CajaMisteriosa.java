package objeto;

import game.Jugador;

public class CajaMisteriosa extends Objeto {

	public CajaMisteriosa() {
		super("Caja Misteriosa", "? ? ? ?",false);
	}

	public void activarEfecto(Jugador jugador) {
		int cantidad = ((int) Math.random() * 9) - 4; // [-4, 4]
		
		if (cantidad < 0) {
			jugador.decrementarMonedas(cantidad);
		} else {
			jugador.aumentarMonedas(cantidad);
		}
	}
}
