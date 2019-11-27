package mensaje;

import java.util.ArrayList;

import game.Jugador;

public class MsjPartidaObjetoUsado extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String objeto;
	private String jugadorAct;
	private ArrayList<Jugador> jugadores;
	
	public MsjPartidaObjetoUsado(String jugadorAct, String objetoUtilizado, ArrayList<Jugador> jugadores) {
		clase = getClass().getSimpleName();
		this.jugadorAct = jugadorAct;
		this.objeto = objetoUtilizado;
		this.jugadores = jugadores;
	}
	
	@Override
	public void ejecutar() {

	}

	public String getObjeto() {
		return objeto;
	}

	public String getJugadorAct() {
		return jugadorAct;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public void setJugadorAct(String jugadorAct) {
		this.jugadorAct = jugadorAct;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

}
