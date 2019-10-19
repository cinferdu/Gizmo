package objeto;

import game.Jugador;

public class CajaMisteriosa extends Objeto {

	public CajaMisteriosa() {
		super("Caja Misteriosa", "? ? ? ?",false);
	}

	public void activarEfecto(Jugador jugador) {
		jugador.aumentarMonedas((int) (Math.random() * 4 + 1));
	}
}
