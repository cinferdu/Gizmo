package mensaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;

import game.Jugador;
import loteria.Loteria;

public class MsjResultadoMiniJuego extends Mensaje {
	private Jugador jugador; 
	private HashMap<String, int[]> jugadas;

	public MsjResultadoMiniJuego(HashMap<String,int[]> jugadas) {
		clase = getClass().getSimpleName();
		this.jugadas = jugadas;
	}
	
	@Override
	public void ejecutar() {
		Loteria juego = new Loteria(this.jugadas);
		HashMap<String, Integer> posiciones =juego.getTablaAciertos();
	}

}
