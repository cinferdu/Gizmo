package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import casilla.Casilla;
import comunicacionObserver.Suscriptor;
import comunicacionObserver.Operacion;
import comunicacionObserver.Publicador;
import miniTenis.MiniTenis;

public class Partida implements Publicador {
	private ArrayList<Jugador> jugadores;
	private int objetivo;
	private boolean hayGanador;
	private Tablero tablero;
	private Jugador jugadorGanador;
	private int rondaActual;
	private List<Suscriptor> clientes;

	private Object respuestaDePanel = null;

	public Partida(ArrayList<Jugador> participantes, int objetivo) {

		jugadores = participantes;
		rondaActual = 0;
		this.objetivo = objetivo;
		hayGanador = false;
		jugadorGanador = null;
		tablero = new Tablero("dataCasilla.txt");
		clientes = new ArrayList<Suscriptor>();
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
					jugadorActual.setNroPasos(Dado.lanzarDado());
					avisar(Operacion.LANZAMIENTO_DADO, jugadorActual);

					// El jugador avanza los pasos
					avanzar(jugadorActual);

					jugadorActual.activarCasilla();
					avisar(Operacion.CASILLA_ACTIVADA, jugadorActual);

					// Si tiene objetos entra en la etapa de SELECCIONAR_ACCION, sino solo mostrara
					// un mensaje
					if (jugadorActual.getMochila_objetos().size() != 0) {
						// El jugador elije su proxima accion
						avisar(Operacion.SELECCIONAR_ACCION, jugadorActual);

						if (respuestaDePanel != null)
							jugadorActual.usarObjeto((Integer) respuestaDePanel);

					} else {
						avisar(Operacion.SIN_ACCION, jugadorActual);
					}

					// Verifico si el jugador cumplio con el objetivo
					if (verificarObjetivo(jugadorActual))
						setJugadorGanador(jugadorActual);// El jugador gano la partida
				} else {
					// Activo el turno del jugador
					jugadorActual.setPierdeTurno(false);
					avisar(Operacion.PERDIO_TURNO, jugadorActual); // Perdio su turno
				}

				avisar(Operacion.ACTUALIZAR_TABLERO, jugadorActual); // Mostrar monedas y estrellas??

				// Fin del turno del jugador.
				// Turno del siguiente jugador.
			}

			if (!hayGanador) {
				for (Iterator<Jugador> iterator = jugadores.iterator(); iterator.hasNext();) {
					Jugador jugador = iterator.next();
					// MINIJUEGO
					MiniTenis miniGame = new MiniTenis();
					try {
						miniGame.setGamerName(jugador.getNombre());
						int score = miniGame.iniciarMiniTenis(jugador.getNombre());
						jugador.setMiniJuegoPuntos(score);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Jugador mejor = getMejorPuntajeEnMiniJuego();
				mejor.aumentarMonedas(mejor.getMiniJuegoPuntos() / 3);
				this.limpiarMiniPuntajes();

			}

		}
		avisar(Operacion.PUNTAJES_FINALES, jugadorGanador);

	}

	private void posicionarJugadoresEnElInicio() {
		for (Jugador jugador : jugadores)
			jugador.setPosicionActual(this.tablero.getCasillaInicial());

	}

	private void avanzar(Jugador jugador) {
		Casilla sigcamino = null;

		while (jugador.getNroPasos() > 0) {

			if ((sigcamino = jugador.getPosicionActual().caminoUnico()) != null)
				jugador.setPosicionActual(sigcamino);
			else {
				avisar(Operacion.SELECCIONAR_CAMINO, jugador);

				jugador.setPosicionActual((Casilla) respuestaDePanel);
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

	public void registrar(Suscriptor obs) {
		clientes.add(obs);
	}

	public void desregistrar(Suscriptor obs) {
		clientes.remove(obs);
	}

	public void avisar(Operacion operacion, Jugador jugadorActual) {
		for (Suscriptor consumidor : clientes) {
			consumidor.actualizar(operacion, jugadorActual);
		}

		// Para que espere un poco antes de volver a continuar y
		// no hacer todo en muy poco tiempo
		// Tal vez habria que modificarlo
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

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

}