package servidor;

import java.util.ArrayList;
import java.util.Iterator;

import casilla.Casilla;
import game.Dado;
import game.Jugador;
import game.Partida;
import mensaje.Mensaje;
import mensaje.MsjPartidaBotonAccion;
import mensaje.MsjPartidaBotonInformar;
import mensaje.MsjPartidaCasillaActivada;
import mensaje.MsjPartidaElegirCaminoAccion;
import mensaje.MsjPartidaElegirCaminoInformar;
import mensaje.MsjPartidaIniRonda;
import mensaje.MsjPartidaLanzamientoDado;
import mensaje.MsjPartidaMovimiento;
import mensaje.MsjPartidaObjetoUsado;
import mensaje.MsjPartidaPierdeTurno;
import mensaje.MsjPartidaPuntajesFinales;
import mensaje.MsjPartidaSelecObjAccion;
import mensaje.MsjPartidaSelecObjInf;
import mensaje.MsjPartidaSinAccion;

public class PartidaThread extends Thread {

	private Partida partida;
	private ListenerThread listener;
	private ArrayList<String> nombresJugadores;
	private Casilla caminoSeleccionado;
	private Jugador jugadorSeleccionado;
	private int objetoSelecionado; // indice del objeto seleccionado

	private ArrayList<String> nombreSpec;

	public PartidaThread(Partida juego, ArrayList<String> nombres, ListenerThread listener) {
		partida = juego;
		this.listener = listener;
		nombresJugadores = nombres;
		nombreSpec = new ArrayList<String>();
	}

	@Override
	public void run() {

		Jugador jugadorActual;
		Iterator<Jugador> iteradorJugador;

		while (!partida.isHayGanador()) {

			// Incremento la ronda
			partida.aumentarRonda();
			iteradorJugador = partida.getJugadores().iterator();

			avisar(new MsjPartidaIniRonda(partida.getRondaActual()));

			while (iteradorJugador.hasNext() && partida.isHayGanador() == false) {
				jugadorActual = iteradorJugador.next();
				
				if (!nombresJugadores.contains(jugadorActual.getNombre())) {
					continue;
				}
				
				if (!jugadorActual.isPierdeTurno()) {

					avisar(new MsjPartidaBotonInformar(jugadorActual));
					avisar(new MsjPartidaBotonAccion(jugadorActual), jugadorActual);

					jugadorActual.setNroPasos(Dado.lanzarDado());

					avisar(new MsjPartidaLanzamientoDado(jugadorActual.getNombre(), jugadorActual.getNroPasos()));

					// El jugador avanza los pasos
					avanzar(jugadorActual); // loop de movimientos

					jugadorActual.activarCasilla();
					avisar(new MsjPartidaCasillaActivada(jugadorActual));

					if (!nombresJugadores.contains(jugadorActual.getNombre())) {
						continue;
					}
					
					// Si tiene objetos entra en la etapa de SELECCIONAR_ACCION, sino solo mostrara
					// un mensaje
					if (!jugadorActual.isMochilaVacia()) {
						objetoSelecionado = -1;
						
						// El jugador elije su proxima accion
						avisar(new MsjPartidaSelecObjInf(jugadorActual));
						avisar(new MsjPartidaSelecObjAccion(jugadorActual), jugadorActual);
						
						String nombreobj = null;
						if (this.objetoSelecionado != -1) {
							if (jugadorSeleccionado != null) { // busco al jugador, si el objeto es con objetivo
								// obtengo la refencia a ese jugador
								jugadorSeleccionado = partida.getJugadores()
										.get(partida.getJugadores().indexOf(jugadorSeleccionado));
							}
							nombreobj = jugadorActual.usarObjeto(objetoSelecionado, jugadorSeleccionado).getNombre();
							
							
							// CAMBIAR
							//listener.enviarMensajeBroadcast(new MsjPartidaObjetoUsado(jugadorActual.getNombre(),nombreobj, partida.getJugadores()));
							avisar(new MsjPartidaObjetoUsado(jugadorActual.getNombre(),nombreobj, partida.getJugadores()));
						}
					} else {
						avisar(new MsjPartidaSinAccion(jugadorActual));
					}

					// Verifico si el jugador cumplio con el objetivo
					if (partida.verificarObjetivo(jugadorActual)) {
						partida.setJugadorGanador(jugadorActual);// El jugador gano la partida
						partida.setHayGanador(true);
					}
				} else {
					// Activo el turno del jugador
					jugadorActual.setPierdeTurno(false);
					avisar(new MsjPartidaPierdeTurno(jugadorActual));
				}


				// Fin del turno del jugador.
				// Turno del siguiente jugador.
			}
			
			/*if(!partida.isHayGanador()) {
				avisar(new MsjIniciarMinijuego());
			}*/
		}
		avisar(new MsjPartidaPuntajesFinales(partida.getJugadorGanador(), partida.getJugadores()));
		
		listener.terminarPartida(partida.getIdpartida(), nombresJugadores);
		
	}

	public void avanzar(Jugador jugador) {
		Casilla sigcamino = null;

		while (jugador.getNroPasos() > 0) {

			if ((sigcamino = jugador.getPosicionActual().caminoUnico()) != null)
				jugador.setPosicionActual(sigcamino);
			else {
				avisar(new MsjPartidaElegirCaminoInformar(jugador));
				avisar(new MsjPartidaElegirCaminoAccion(jugador, jugador.getPosicionActual().getSiguientesCasillas()),
						jugador);

				jugador.setPosicionActual(buscarCasilla(jugador));
			}

			avisar(new MsjPartidaMovimiento(jugador));

			jugador.decrementarPasos();
		}
	}

	private Casilla buscarCasilla(Jugador jugador) {
		for (Casilla casilla : jugador.getPosicionActual().getSiguientesCasillas()) {
			if (casilla.getPosX() == caminoSeleccionado.getPosX()
					&& casilla.getPosY() == caminoSeleccionado.getPosY()) {
				return casilla;
			}
		}
		return null;
	}

	/**
	 * Envia un mensaje a todos los jugadores
	 * 
	 * @param msjPartida
	 */
	public void avisar(Mensaje msjPartida) {
		listener.enviarMensajeBroadcast(msjPartida, nombresJugadores);
		listener.enviarMensajeBroadcast(msjPartida, nombreSpec);

		long ini = System.currentTimeMillis() + 500;
		while (System.currentTimeMillis() < ini)
			;
	}

	/**
	 * Envia un mensaje a un solo jugador
	 * 
	 * @param msjPartida
	 * @param jugadorActual
	 */
	public void avisar(Mensaje msjPartida, Jugador jugadorActual) {
		listener.enviarMensaje(msjPartida, jugadorActual.getNombre());
		synchronized (this) {
			try {
				this.wait(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setCaminoSeleccionado(Casilla caminoSeleccionado) {
		this.caminoSeleccionado = caminoSeleccionado;
	}

	public void setObjetoSelecionado(int objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public void setJugadorSelecionado(Jugador jugObjetivo) {
		this.jugadorSeleccionado = jugObjetivo;
	}

	public ArrayList<Jugador> getJugadores() {
		return partida.getJugadores();
	}

	public ArrayList<String> getNombreJugadores() {
		return this.nombresJugadores;
	}

	public void setNombreJugadores(ArrayList<String> jugadores) {
		this.nombresJugadores = jugadores;
	}

	public Partida getPartida() {
		return partida;
	}

	public void addSpec(String nombreCliente) {
		nombreSpec.add(nombreCliente);
	}
	
	public void removeSpect(String nombreCliente){
		nombreSpec.remove(nombreCliente);
	}

}
