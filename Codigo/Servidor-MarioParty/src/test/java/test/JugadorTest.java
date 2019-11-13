package test;

import game.Jugador;
import game.Tablero;

public class JugadorTest {
	Tablero mapa = null;
	Jugador jugador = null;
	Jugador segundoJugador = null;

//	@Before
//	public void init() {
//		mapa = new Tablero(2, 2);
//		mapa.crearTablero(2, 2);
//		jugador = new Jugador(0, mapa, "Mario");
//		segundoJugador = new Jugador(0, mapa, "Luigi");
//	}
//
//	@Test
//	public void crearJugador() {
//		assertEquals(mapa.getCasillaInicial(), jugador.getPosicionActual());
//	}
//
//	@Test
//	public void moverJugador() {
//		jugador.setNroPasos(new DadoTrucado().generar());
//		assertEquals(1, jugador.getNroPasos());
//		jugador.avanzar();
//		assertEquals(mapa.getCasillaFinal(), jugador.getPosicionActual());
//	}
//
//	@Test
//	public void JugadorPerdioTurno() {
//		assertEquals(mapa.getCasillaInicial(), segundoJugador.getPosicionActual());
//		segundoJugador.setNroPasos(1);
//		segundoJugador.setFinTurno(true);
//		segundoJugador.avanzar(); // Como perdio el turno se queda en el lugar
//		assertEquals(mapa.getCasillaInicial(), segundoJugador.getPosicionActual());
//	}
//	
//	@Test
//	public void tirarDadoTest() {
//		DadoTrucado dadoTrucado = new DadoTrucado();
//		assertEquals(1, jugador.tirarDado(dadoTrucado));
//	}
//	
//	@Test
//	public void elegirCaminoTest() {
//		assertTrue(true);
////		Casillero elegirCamino = jugador.elegirCamino(new Casillero(0, null, null, null, null, new Objeto(0), new Poder(0)));
////		assertEquals(new Casillero(0, null, null, null, null, new Objeto(0), new Poder(0)), elegirCamino);
//	}
//	
//	@Test
//	public void usarObjetoTest() {
//		Objeto objeto = new Objeto(1);
//		jugador.setObjeto(objeto);
//		assertEquals(objeto, jugador.usarObjeto());
//	}
//	
//	@Test
//	public void usarPoderTest() {
//		jugador.setPoder(new Poder(1));
////		assertEquals(new Poder(1).efecto(jugador), jugador.usarPoder());
//		assertTrue(true);
//	}
//	
//	@Test
//	public void activarPoderTest() {
//		Poder poder = new Poder(1);
//		jugador.setPoder(poder);
//		assertEquals(poder, jugador.activarPoder());
//	}
//	
//	@Test
//	public void accionTest() {
//		assertTrue(jugador.accion());
//	}
//	
//	@Test
//	public void avanzarTest() {
//		jugador.setPosicionActual(new Casillero(0, null, null, null, null, new Objeto(0), new Poder(0)));
//		assertTrue(jugador.avanzar());
//	}
//	
//	@Test
//	public void activarCasillaTest() {
//		//no se que tiene que hacer!
//		assertTrue(true);
//	}
}
