package game;

import java.util.ArrayList;

public class Partida {
	public static final String ARCHIVO = "dataCasilla.txt";
	private ArrayList<Jugador> jugadores;
	private int objetivo;
	private boolean hayGanador;
	private Tablero tablero;
	private Jugador jugadorGanador;
	private int rondaActual;
	private int idpartida;

	public Partida(int id, ArrayList<Jugador> participantes, int objetivo) {
		idpartida = id;
		//setIdpartida(++partidasCreadas);
		jugadores = participantes;
		rondaActual = 0;
		this.objetivo = objetivo;
		hayGanador = false;
		jugadorGanador = null;
		tablero = new Tablero(ARCHIVO);
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

	public int getIdpartida() {
		return idpartida;
	}

	public void setIdpartida(int idpartida) {
		this.idpartida = idpartida;
	}

	public void aumentarRonda() {
		rondaActual++;
	}

	@Override
	public String toString() {
		return "Partida [jugadores=" + jugadores + ", objetivo=" + objetivo + ", hayGanador=" + hayGanador
				+ ", tablero=" + tablero + ", jugadorGanador=" + jugadorGanador + ", rondaActual=" + rondaActual
				+ ", idpartida=" + idpartida + "]";
	}
}