package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import game.Jugador;
import game.Objeto;
import game.Tablero;

public class ObjetoTest {

	Tablero mapa = null;
	Jugador jugador = null;

	@Before
	public void init() {
		mapa = new Tablero(2, 2);
		mapa.crearTablero(2, 2);
		jugador = new Jugador(0, mapa, "Mario");
	}
	
	@Test
	public void usarObjetoTipo0() {
		jugador.setObjeto(new Objeto(0));
		assertEquals(0, jugador.getMonedas());
		jugador.usarObjeto();
		assertEquals(10, jugador.getMonedas());
	}

	@Test
	public void usarObjetoTipo1() {
		jugador.setObjeto(new Objeto(1));
		assertEquals(0, jugador.getPuntos());
		jugador.usarObjeto();
		assertEquals(100, jugador.getPuntos());
	}

}
