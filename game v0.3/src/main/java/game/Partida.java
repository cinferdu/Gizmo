package game;

import java.util.ArrayList;
import java.util.List;

public class Partida {
	private List<Jugador> jugadores = null;
	private int cantidadRondas;
	private Objetivo objetivo;
	private boolean hayGanador;
	private Tablero tablero;
	private Jugador jugadorGanador = null ;
	private int rondaActual ;
	
	
	
	public Partida(int cantRondas) {
		
		this.setObjetivo(objetivo);
		this.cantidadRondas = cantRondas;
		jugadores = new ArrayList<Jugador>();
		this.setHayGanador(false);
		this.setRondaActual(0);
	}
	
	public boolean iniciarPartida(int nroJugadores) {
		
		
		//Creo los jugadores necesarios
		for (int i = 0; i < nroJugadores; i++) {
			jugadores.add(new Jugador(i, this.getTablero(), "Pepito"));
		}
		
		if(jugadores.size() < 2)
			return false;
		return true;
	}
	
	public boolean definirObjetivoFinal(Objetivo objetivo) {		
		this.objetivo = objetivo;
		return true;
	}
	public void iniciarRonda() {
		// Incremento la ronda
		this.setRondaActual(this.getRondaActual()+1);
		int i = 0;
		
		while (i<jugadores.size() && this.hayGanador==false) {
			
			
			// Activo el turno del jugador
			jugadores.get(i).finTurno = false;
			
			// Lanza el dado
			jugadores.get(i).tirarDado(new DadoTrucado());
			
			// El jugador avanza los pasos
			jugadores.get(i).avanzar();
			
			// El jugador elije su proxima accion
			jugadores.get(i).accion();
			//Verifico si el jugador cumplio con el objetivo
			if (verificarObjetivo(jugadores.get(i)))
				setJugadorGanador(jugadores.get(i));// El jugador gano la partida
			
			// Fin del turno del jugador.
			
			//Turno del siguiente jugador.
			i++;
		}
		
		
		
	}
	public boolean definirTablero(Tablero tablero) {
		
		this.setTablero(tablero);
		
		return true;
	}

	public boolean verificarObjetivo(Jugador jugador) {

		switch (this.getObjetivo().getTipo()) {
		// Si el jugador alcanzo el max de monedas, gana.
		case 0:
			if (jugador.getMonedas()>this.getObjetivo().getMonedasMax())
				this.hayGanador = true;
			break;
		case 1:
			if (jugador.getRondaActual()>this.getObjetivo().getRecorridos())
				this.hayGanador = true;
			break;
		case 2:
			if (jugador.getPuntos()>this.getObjetivo().getPuntosMax())
				this.hayGanador = true;
			break;
		default:
			break;
		}
		
		
		
		return false;
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

}
