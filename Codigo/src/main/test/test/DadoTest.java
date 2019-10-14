package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.DadoTrucado;

public class DadoTest {

	@Test
	public void test() {
		DadoTrucado dadoTrucado = new DadoTrucado();
		assertEquals(1, dadoTrucado.generar());
	}

}
