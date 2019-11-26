package mensaje;

import java.util.ArrayList;

import entornoGrafico.VentanaJuego;
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
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().informarObjetoUtilizado(jugadorAct,
				objeto);

		ArrayList<Jugador> game = listenerClient.getCliente().getPartidaActual().getJugadores();

		for (int i = 0; i < game.size(); i++) {
			game.get(i).setMonedas(jugadores.get(i).getMonedas());
			game.get(i).setPierdeTurno(jugadores.get(i).isPierdeTurno());
		}
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().movimiento();
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
