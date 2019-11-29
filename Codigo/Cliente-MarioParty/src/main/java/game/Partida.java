package game;

import java.util.ArrayList;
import java.util.Iterator;

public class Partida {
	private ArrayList<Jugador> jugadores;
	private int objetivo;
	private boolean hayGanador;
	private Tablero tablero;
	private Jugador jugadorGanador;
	private int rondaActual;
	private int idpartida;

	private Object respuestaDePanel = null;
	private static int partidasCreadas;

	public Partida(ArrayList<Jugador> participantes, int objetivo) {
		setIdpartida(partidasCreadas);
		jugadores = participantes;
		rondaActual = 0;
		this.objetivo = objetivo;
		hayGanador = false;
		jugadorGanador = null;
		tablero = new Tablero("dataCasilla.txt");
		posicionarJugadoresEnElInicio();
	}

	private void posicionarJugadoresEnElInicio() {
		for (Jugador jugador : jugadores)
			jugador.setPosicionActual(this.tablero.getCasillaInicial());

	}

	public boolean definirTablero(Tablero tablero) {
		this.setTablero(tablero);
		return true;
	}

	public boolean verificarObjetivo(Jugador jugador) {
		if (jugador.getMonedas() >= this.objetivo) {
			this.hayGanador = true;
			this.jugadorGanador = jugador;
		}

		return false;
	}
	// TODO
	/*
	 * public void iniciarMiniJuego(MiniJuego miniJuego) { // minijuego pendiente }
	 */

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(int objetivo) {
		this.objetivo = objetivo;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Jugador getJugadorGanador() {
		return jugadorGanador;
	}

	public void setJugadorGanador(Jugador jugadorGanador) {
		this.jugadorGanador = jugadorGanador;
	}

	public int getRondaActual() {
		return rondaActual;
	}

	public void setRondaActual(int rondaActual) {
		this.rondaActual = rondaActual;
	}

	public boolean isHayGanador() {
		return hayGanador;
	}

	public void setHayGanador(boolean hayGanador) {
		this.hayGanador = hayGanador;
	}

	public Object getRespuestaDePanel() {
		return respuestaDePanel;
	}

	public void setRespuestaDePanel(Object respuestaDePanel) {
		this.respuestaDePanel = respuestaDePanel;
	}

	private Jugador getMejorPuntajeEnMiniJuego() {
		Jugador mejorPuntaje = this.jugadores.get(0);
		for (int i = 1; i < jugadores.size(); i++) {
			if (this.jugadores.get(i).getMiniJuegoPuntos() > mejorPuntaje.getMiniJuegoPuntos())
				mejorPuntaje = this.jugadores.get(i);
		}
		return mejorPuntaje;
	}

	private void limpiarMiniPuntajes() {

		for (Iterator<Jugador> iterator = jugadores.iterator(); iterator.hasNext();) {
			Jugador jugador = iterator.next();
			jugador.setMiniJuegoPuntos(0);
		}
	}

	public int getIdpartida() {
		return idpartida;
	}

	public void setIdpartida(int idpartida) {
		this.idpartida = idpartida;
	}

}