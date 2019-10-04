package objetos;

import game2.Jugador;

public class CajaDePandora extends Objeto {
	
	public CajaDePandora() {
		super("Caja de Pandora", "? ? ? ?");
	}

	public void activarEfecto(Jugador jugador) {
		jugador.setMonedas(jugador.getMonedas()+20);
	}
}
