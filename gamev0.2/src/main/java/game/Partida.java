package game;

import java.util.ArrayList;
import java.util.List;

public class Partida {
	private List<Jugador> jugadores = null;
	private int cantidadRondas;
	private Objetivo objetivo;
	private Tablero tablero;
	private Casillero casillaInicial = null;
	
	public Partida() {
		super();
		jugadores = new ArrayList<Jugador>();
	}
	
	public boolean iniciarPartida(List<Jugador> jugadores,Casillero casillaInicial,int cantRondas) {
		this.setCasillaInicial(casillaInicial);
		this.cantidadRondas = cantRondas;
		if(jugadores.size() < 2)
			return false;
		return true;
	}
	
	public boolean definirObjetivoFinal() {
		//random de objetivos!
		this.objetivo = new Objetivo();
		return true;
	}
	
	public boolean verificarObjetivo(Jugador jugador) {
		//si el jugador cumple con tal... return true y fin del juego
		return true;
	}
	
	public void iniciarMiniJuego(MiniJuego miniJuego) {
		//minijuego pendiente
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getCantidadRondas() {
		return cantidadRondas;
	}

	public void setCantidadRondas(int cantidadRondas) {
		this.cantidadRondas = cantidadRondas;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Casillero getCasillaInicial() {
		return casillaInicial;
	}

	public void setCasillaInicial(Casillero casillaInicial) {
		this.casillaInicial = casillaInicial;
	}
}
