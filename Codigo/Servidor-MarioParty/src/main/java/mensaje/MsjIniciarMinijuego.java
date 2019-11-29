package mensaje;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import game.Partida;
import loteria.Loteria;

public class MsjIniciarMinijuego extends Mensaje{
	private final static Logger LOGGER = Logger.getLogger(MsjIniciarPartida.class);

	private static final long serialVersionUID = 1L;
	private ArrayList<String> nombresJugadores;
	
	public MsjIniciarMinijuego() {
		this.clase = this.getClass().getSimpleName();
	}
	
	public MsjIniciarMinijuego(ArrayList<String> nombresJugadores) {
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		
	}
	
	
}
