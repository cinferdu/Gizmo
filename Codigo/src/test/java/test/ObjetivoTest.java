package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import game.Objetivo;

public class ObjetivoTest {

	@Test
	public void Descripciontest1()
	{
		Objetivo obj = new Objetivo(0,4000);
		assertEquals("Monedas Max", obj.getDescripcion());
	}
	
	@Test
	public void Descripciontest2()
	{
		Objetivo obj = new Objetivo(1,3);
		assertEquals("Recorridos Max", obj.getDescripcion());
	}
	
	@Test
	public void Descripciontest3()
	{
		Objetivo obj = new Objetivo(2,30000);
		assertEquals("Puntaje Max", obj.getDescripcion());
	}
	
	@Test
	public void Descripciontest4()
	{
		Objetivo obj = new Objetivo(3,1);
		assertEquals("LLegar a la casilla final", obj.getDescripcion());
	}
	
	@Test
	public void MonedasMaxtest()
	{
		Objetivo obj = new Objetivo(0,4000);
		assertEquals(4000,obj.getMonedasMax());
	}
	
	@Test
	public void RecorridosMaxtest()
	{
		Objetivo obj = new Objetivo(1,3);
		assertEquals(3,obj.getRecorridos());
	}

	@Test
	public void PuntosMaxtest()
	{
		Objetivo obj = new Objetivo(2,300);
		assertEquals(300,obj.getPuntosMax());
	}
	
	@Test
	public void TipoTest()
	{
		Objetivo obj = new Objetivo(2,300);
		assertEquals(2, obj.getTipo());
	}
}
