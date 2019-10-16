package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import casilla.Casilla;
import comunicacionObserver.Consumidor;
import comunicacionObserver.Operacion;
import comunicacionObserver.Productor;

public class Partida implements Productor {
	private List<Jugador> jugadores;
	private int objetivo;
	private boolean hayGanador;
	private Tablero tablero;
	private Jugador jugadorGanador;
	private int rondaActual;
	private	List<Consumidor> clientes;

	public Partida(List<Jugador> participantes, int objetivo) {

		jugadores = participantes;
		rondaActual = 0;
		this.objetivo = objetivo;
		hayGanador = false;
		jugadorGanador = null;
		tablero = new Tablero("dataCasilla.txt");
		clientes = new ArrayList<Consumidor>();
	}

	public void iniciarPartida() {
		
		if (jugadores.size() < 2)
			return;
		
		posicionarJugadoresEnElInicio();
		
		Jugador jugadorActual;
		Iterator<Jugador> iteradorJugador;
		
		// TODO observer Actualizar
		
		while (!hayGanador) {
			
			// Incremento la ronda
			rondaActual++;
			iteradorJugador = jugadores.iterator();
			
			avisar(Operacion.NUEVA_RONDA, null);
			
			while (iteradorJugador.hasNext() && this.hayGanador == false) {
				jugadorActual = iteradorJugador.next();
				
				if (!jugadorActual.isPierdeTurno()) {
					
					// Lanza el dado
					jugadorActual.setNroPasos(tirarDado());
					avisar(Operacion.LANZAMIENTO_DADO, jugadorActual);
					
					// El jugador avanza los pasos
					avanzar(jugadorActual);
					
					jugadorActual.activarCasilla();
					avisar(Operacion.CASILLA_ACTIVADA, jugadorActual);
					
					// El jugador elije su proxima accion
					jugadorActual.accion();
					
					/*	agregar observer 
					 * 	esperar respuesta
					 */
					
					// TODO observer Actualizar
					
					// Verifico si el jugador cumplio con el objetivo
					if (verificarObjetivo(jugadorActual))
						setJugadorGanador(jugadorActual);// El jugador gano la partida
				} else {
					// Activo el turno del jugador
					jugadorActual.setPierdeTurno(false);
					//avisar(null, jugadorActual); Perdio su turno
				}
				
				//avisar(null, jugadorActual); Mostrar monedas y estrellas??
				
				// Fin del turno del jugador.
				// Turno del siguiente jugador.
			}
			
			// MINIJUEGO
		}
		
		// TODO observer Actualizar
		
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
 				avisar(Operacion.SELECCIONAR_CAMINO, jugador);
/* 				
 				synchronized (this) {
					try {
						if (jugador.getCaminoElegido() == null) {	// tal vez borrar esto
							this.wait();							
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
*/
				jugador.setPosicionActual(jugador.getCaminoElegido());
				jugador.setCaminoElegido(null);
 			}
			
			avisar(Operacion.MOVIMIENTO, jugador);
			
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

	public void registrar(Consumidor obs) {
		clientes.add(obs);
	}

	public void desregistrar(Consumidor obs) {
		clientes.remove(obs);
	}

	public void avisar(Operacion operacion, Jugador jugadorActual) {
		for (Consumidor consumidor : clientes) {
			consumidor.actualizar(operacion, jugadorActual);
		}
		
		// Para que espere un poco antes de volver a continuar y no hacer todo en muy poco tiempo
		// Tal vez habria que modificarlo
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		/*
		synchronized (this) {
            try {
				this.wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		*/
	}

}