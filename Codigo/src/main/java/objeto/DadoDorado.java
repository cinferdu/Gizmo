package objeto;

import game.Jugador;

public class DadoDorado extends Objeto {

	public DadoDorado() {
		// <br> == nueva linea
		super("Dado dorado", "<html>Lanzas un dado de 10 caras y el numero <br>que obtienes, lo ganas en monedas</html>", false);
	}

	@Override
	public void activarEfecto(Jugador jugador) {
		jugador.aumentarMonedas((int) (Math.random() * 10 + 1));
	}

}
