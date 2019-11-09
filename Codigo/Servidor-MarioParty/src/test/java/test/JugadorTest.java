package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Jugador;
import game.Partida;
import objeto.Objeto;
import objeto.PistolaCongelante;
import servidor.PartidaThread;

public class JugadorTest {

	Jugador primerJugador = null;
	Jugador segundoJugador = null;
	Partida partida = null;
	PartidaThread partidaThread = null;

	@BeforeEach
	public void setUp() {

		primerJugador = new Jugador("Mario");
		segundoJugador = new Jugador("Princess Peach");
		ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
		ArrayList<String> listaNombresJugadores = new ArrayList<String>();
		listaJugadores.add(primerJugador);
		listaJugadores.add(segundoJugador);
		listaNombresJugadores.add(primerJugador.getNombre());
		listaNombresJugadores.add(segundoJugador.getNombre());
		partida = new Partida(listaJugadores, 50);
		partidaThread = new PartidaThread(partida, listaNombresJugadores, null);
	}

	@Test
	public void crearJugador() {
		assertEquals(partida.getTablero().getCasillaInicial(), primerJugador.getPosicionActual());
	}

	@Test
	public void moverJugadorTest() {
		primerJugador.setNroPasos(1);
		partidaThread.avanzar(primerJugador);
		assertEquals(partida.getTablero().getCasillaInicial().caminoUnico(), primerJugador.getPosicionActual());
	}

	@Test
	public void usarObjetoTest() {
		Objeto pistola = new PistolaCongelante();
		pistola.setVictima(primerJugador); /// Setea victima de objeto
		ArrayList<Objeto> mochila_objetos = new ArrayList<Objeto>();
		mochila_objetos.add(pistola);
		segundoJugador.setMochila_objetos(mochila_objetos);
		segundoJugador.usarObjeto(0); // Se setea objeto en mochila del jugador, y este se utiliza
		assertEquals(true, primerJugador.isPierdeTurno());
	}

	@Test
	public void obtenerMonedasTest() {
		primerJugador.setMonedas(3); // Se setean 3 monedas en el jugador
		primerJugador.setNroPasos(2);
		partidaThread.avanzar(primerJugador);// Jugador avanza hasta una casilla premio
		primerJugador.activarCasilla();// La casilla con premio le hace ganar 5 monedas
		assertEquals(8, primerJugador.getMonedas());
	}

	@Test
	public void perderTurnoTest() {
		primerJugador.setNroPasos(3);
		partidaThread.avanzar(primerJugador);// Jugador avanza hasta una casilla trampa
		primerJugador.activarCasilla();// la casilla trampa le hace perder el turno
		assertEquals(true, primerJugador.isPierdeTurno());
	}

}