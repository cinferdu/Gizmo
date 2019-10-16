package objeto;

import game.Jugador;

public class DadoDorado extends Objeto {

	public DadoDorado() {
		super("Dado dorado", "Lanzas un dado de 10 caras y el numero que obtienes, lo ganas en monedas");
	}

	@Override
	public void activarEfecto(Jugador jugador) {
		jugador.aumentarMonedas((int) Math.random() * 10 + 1);
	}

}
