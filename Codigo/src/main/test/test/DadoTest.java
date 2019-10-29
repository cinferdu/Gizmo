package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Dado;

public class DadoTest {

	@Test
	public void test() {
		assertEquals(1, Dado.lanzarDado(1));
	}

}
