package mensaje;

import java.util.ArrayList;
import java.util.Iterator;

import casilla.Casilla;
import game.Dado;
import game.Jugador;
import game.Partida;
import objeto.Objeto;
import servidor.ListenerThread;

public class PartidaThread extends Thread {

	private Partida partida;
	private ListenerThread listener;
	private ArrayList<String> nombresJugadores;
	private Casilla caminoSeleccionado;
	private Objeto objetoSelecionado;
	
	
	public PartidaThread(Partida juego, ArrayList<String> nombres, ListenerThread listener) {
		partida = juego;
		this.listener = listener;
		nombresJugadores = nombres;
	}

	@Override
	public void run() {
		
		try {
			sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		Jugador jugadorActual;
		Iterator<Jugador> iteradorJugador;

		// TODO observer Actualizar

		while (!partida.isHayGanador()) {

			// Incremento la ronda
			partida.aumentarRonda();
			iteradorJugador = partida.getJugadores().iterator();

			avisar(new MsjPartidaIniRonda(partida.getRondaActual()));
			
			while (iteradorJugador.hasNext() && partida.isHayGanador() == false) {
				jugadorActual = iteradorJugador.next();

				if (!jugadorActual.isPierdeTurno()) {

					avisar(new MsjPartidaBotonInformar(jugadorActual));
					avisar(new MsjPartidaBotonAccion(jugadorActual),jugadorActual);

					esperarNofify();
					
					jugadorActual.setNroPasos(Dado.lanzarDado());
					
					avisar(new MsjPartidaLanzamientoDado(jugadorActual.getNombre(), jugadorActual.getNroPasos()));

					// El jugador avanza los pasos
					avanzar(jugadorActual); // loop de movimientos

					jugadorActual.activarCasilla();
					//avisar(Operacion.CASILLA_ACTIVADA, jugadorActual); 

					// Si tiene objetos entra en la etapa de SELECCIONAR_ACCION, sino solo mostrara
					// un mensaje
					if (!jugadorActual.isMochilaVacia()) {
						// El jugador elije su proxima accion
						//avisar(Operacion.SELECCIONAR_ACCION, jugadorActual); // espera a que haga algo

						//if (respuestaDePanel != null)
						//	jugadorActual.usarObjeto((Integer) respuestaDePanel);

					} else {
						//avisar(Operacion.SIN_ACCION, jugadorActual);
					}

					// Verifico si el jugador cumplio con el objetivo
					if (partida.verificarObjetivo(jugadorActual))
						partida.setJugadorGanador(jugadorActual);// El jugador gano la partida
				} else {
					// Activo el turno del jugador
					jugadorActual.setPierdeTurno(false);
					//avisar(Operacion.PERDIO_TURNO, jugadorActual); // Perdio su turno
				}

				//avisar(Operacion.ACTUALIZAR_TABLERO, jugadorActual); // Mostrar monedas y estrellas??

				// Fin del turno del jugador.
				// Turno del siguiente jugador.
			}
/*
			if (!partida.isHayGanador()) {
				for (Iterator<Jugador> iterator = partida.getJugadores().iterator(); iterator.hasNext();) {
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
				//Jugador mejor = getMejorPuntajeEnMiniJuego();
				//mejor.aumentarMonedas(mejor.getMiniJuegoPuntos() / 3);
				//this.limpiarMiniPuntajes();

			}*/

		}
		//avisar(Operacion.PUNTAJES_FINALES, partida.getJugadorGanador());

	}
	
	public void avanzar(Jugador jugador) {
		Casilla sigcamino = null;

		while (jugador.getNroPasos() > 0) {

			if ((sigcamino = jugador.getPosicionActual().caminoUnico()) != null)
				jugador.setPosicionActual(sigcamino);
			else {
				//avisar(Operacion.SELECCIONAR_CAMINO, jugador);
				avisar(new MsjPartidaElegirCaminoInformar(jugador));
				avisar(new MsjPartidaElegirCaminoAccion(jugador, jugador.getPosicionActual().getSiguientesCasillas()));
				esperarNofify();
				
				//jugador.setPosicionActual((Casilla) respuestaDePanel);
				jugador.setPosicionActual(caminoSeleccionado);
			}

			avisar(new MsjPartidaMovimiento(jugador));

			jugador.decrementarPasos();
		}
	}
	
	/**
	 * Envia un mensaje a todos los jugadores
	 * @param msjPartida
	 */
	private void avisar(Mensaje msjPartida) {
		listener.enviarMensajeBroadcast(msjPartida, this.nombresJugadores); 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Envia un mensaje a un solo jugador
	 * 
	 * @param msjPartida
	 * @param jugadorActual
	 */
	private void avisar(Mensaje msjPartida, Jugador jugadorActual) {
		listener.enviarMensaje(msjPartida, jugadorActual.getNombre());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void esperarNofify() {
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setCaminoSeleccionado(Casilla caminoSeleccionado) {
		this.caminoSeleccionado = caminoSeleccionado;
	}

	public void setObjetoSelecionado(Objeto objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	
}
