package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import game.DadoTrucado;
import game.Jugador;
import game.Tablero;

public class JugadorTest {

	Tablero mapa = null;
	Jugador jugador = null;
	Jugador segundoJugador = null;

	@Before
	public void init() {
		mapa = new Tablero(2, 2);
		mapa.crearTablero(2, 2);
		jugador = new Jugador(0, mapa, "Mario");
		segundoJugador = new Jugador(0, mapa, "Luigi");
	}

	@Test
	public void crearJugador() {
		assertEquals(mapa.getCasillaInicial(), jugador.getPosicionActual());
	}

	@Test
	public void moverJugador() {
		jugador.setNroPasos(new DadoTrucado().generar());
		assertEquals(1, jugador.getNroPasos());
		jugador.avanzar();
		assertEquals(mapa.getCasillaFinal(), jugador.getPosicionActual());
		jugador.avanzar(); // como llego a la ultima casilla se queda en el lugar
		assertEquals(mapa.getCasillaFinal(), jugador.getPosicionActual());
	}

	@Test
	public void JugadorPerdioTurno() {
		assertEquals(mapa.getCasillaInicial(), segundoJugador.getPosicionActual());
		segundoJugador.setNroPasos(1);
		segundoJugador.setFinTurno(true);
		segundoJugador.avanzar(); // Como perdio el turno se queda en el lugar
		assertEquals(mapa.getCasillaInicial(), segundoJugador.getPosicionActual());
		segundoJugador.setFinTurno(false);
		segundoJugador.avanzar();
		assertEquals(mapa.getCasillaFinal(), segundoJugador.getPosicionActual());
	}
}
