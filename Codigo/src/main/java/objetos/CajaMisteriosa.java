package objetos;

import game.Jugador;

public class CajaMisteriosa extends Objeto {

	public CajaMisteriosa() {
		super("Caja Misteriosa", "? ? ? ?");
	}

	public void activarEfecto(Jugador jugador) {
		jugador.setMonedas(jugador.getMonedas() + (int) Math.random() * 20);
	}
}
