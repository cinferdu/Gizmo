package casillas;

import game2.Jugador;

public class ConPremio implements TipoDeCasilla {

	public void activarCasilla(Jugador objetivo) {
		objetivo.setMonedas(objetivo.getMonedas()+5);
	}

}
