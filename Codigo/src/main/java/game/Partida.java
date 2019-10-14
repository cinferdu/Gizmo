package game;

import java.util.Iterator;
import java.util.List;

import casillas.Casilla;

public class Partida {
	private List<Jugador> jugadores;
	private int objetivo;
	private boolean hayGanador;
	public Tablero tablero;
	private Jugador jugadorGanador;
	private int rondaActual;

	public Partida(List<Jugador> participantes, int objetivo) {

		jugadores = participantes;
		rondaActual = 0;
		this.objetivo = objetivo;
		hayGanador = false;
		jugadorGanador = null;
		tablero = new Tablero("dataCasilla.txt");

	}

	public void iniciarPartida() {
		
		if (jugadores.size() < 2)
			return;
		
		posicionarJugadoresEnElInicio();
		
		Jugador jugadorActual;
		Iterator<Jugador> iteradorJugador;
		
		
		while (!hayGanador) {
			
			// Incremento la ronda
			rondaActual++;
			iteradorJugador = jugadores.iterator();
			
			
			while (iteradorJugador.hasNext() && this.hayGanador == false) {
				jugadorActual = iteradorJugador.next();
				
				if (!jugadorActual.isPierdeTurno()) {

					// Lanza el dado
					jugadorActual.setNroPasos(tirarDado());
					
					
					// El jugador avanza los pasos
					avanzar(jugadorActual);
					
					jugadorActual.activarCasilla();
					
					
					// El jugador elije su proxima accion
					jugadorActual.accion();
					
					
					// Verifico si el jugador cumplio con el objetivo
					if (verificarObjetivo(jugadorActual))
						setJugadorGanador(jugadorActual);// El jugador gano la partida
				} else {
					// Activo el turno del jugador
					jugadorActual.setPierdeTurno(false);
				}
				
				
				// Fin del turno del jugador.
				// Turno del siguiente jugador.
			}
			
			// MINIJUEGO
		}
		
		
		System.out.println("Ganador: " +this.jugadorGanador.getNombre()+" \nMonedas: "+this.jugadorGanador.getMonedas());
		
		// TODO mostrar ganador o resultados 
	}


	private void posicionarJugadoresEnElInicio() {
		for (Jugador jugador : jugadores) 
			jugador.setPosicionActual(this.tablero.getCasillaInicial());
		
	}

	public int tirarDado() {
		return (int) (Math.random() * 6 + 1);
	}

	private void avanzar(Jugador jugador) {
		Casilla sigcamino = null;

		while (jugador.getNroPasos() > 0) {
			
			if ((sigcamino = jugador.getPosicionActual().caminoUnico()) != null)
				jugador.setPosicionActual(sigcamino);
 			else {
				jugador.setPosicionActual(jugador.elegirCamino());
 			}
			jugador.decrementarPasos();
		}
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

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
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

}
