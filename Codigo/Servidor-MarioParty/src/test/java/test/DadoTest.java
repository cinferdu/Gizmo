package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import game.Dado;

public class DadoTest {

	@Test
	public void test() {
		assertEquals(1, Dado.lanzarDado(1));
	}

}
