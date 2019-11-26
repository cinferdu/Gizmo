package objeto;

import game.Jugador;

public class DadoDorado extends Objeto {

	public DadoDorado() {
		super("Dado dorado",
				"<html>Lanzas un dado de 10 caras y el numero <br>que obtienes, lo ganas en monedas</html>", false);
	}

	@Override
	public void activarEfecto(Jugador jugador) {
	}

}
