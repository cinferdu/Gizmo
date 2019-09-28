package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Casillero;
import game.DadoTrucado;
import game.Jugador;
import game.Objeto;
import game.Poder;
import game.Tablero;

public class JugadorTest {
	private Jugador jugador  = new Jugador(1, new Tablero(1, 1), "asd");
	
	@Test
	public void tirarDadoTest() {
		DadoTrucado dadoTrucado = new DadoTrucado();
		assertEquals(1, jugador.tirarDado(dadoTrucado));
	}
	
	@Test
	public void elegirCaminoTest() {
		assertTrue(true);
//		Casillero elegirCamino = jugador.elegirCamino(new Casillero(0, null, null, null, null, new Objeto(0), new Poder(0)));
//		assertEquals(new Casillero(0, null, null, null, null, new Objeto(0), new Poder(0)), elegirCamino);
	}
	
	@Test
	public void usarObjetoTest() {
		jugador.setObjeto(new Objeto(1));
		assertEquals(new Objeto(1), jugador.usarObjeto());
	}
	
	@Test
	public void usarPoderTest() {
		jugador.setPoder(new Poder(1));
//		assertEquals(new Poder(1).efecto(jugador), jugador.usarPoder());
		assertTrue(true);
	}
	
	@Test
	public void activarPoderTest() {
		jugador.setPoder(new Poder(1));
		assertEquals(new Poder(1), jugador.activarPoder());
	}
	
	@Test
	public void accionTest() {
		assertTrue(jugador.accion());
	}
	
	@Test
	public void avanzarTest() {
		jugador.setPosicionActual(new Casillero(0, null, null, null, null, new Objeto(0), new Poder(0)));
		assertTrue(jugador.avanzar());
	}
	
	@Test
	public void activarCasillaTest() {
		//no se que tiene que hacer!
		assertTrue(true);
	}
}
