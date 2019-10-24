package objeto;

import game.Jugador;

public class CajaMisteriosa extends Objeto {

	public CajaMisteriosa() {
		super("Caja Misteriosa", "? ? ? ?",false);
	}

	public void activarEfecto(Jugador jugador) {
		int cantidad = (int) (Math.random() * 4); // [0, 3]
		int signo = ((int) Math.random() * -2); // 0 o -1
		
		if (signo < 0) {
			jugador.decrementarMonedas(cantidad);
		} else {
			jugador.aumentarMonedas(cantidad);
		}
	}
}
