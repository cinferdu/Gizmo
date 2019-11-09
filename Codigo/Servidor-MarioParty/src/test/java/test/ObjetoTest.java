package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Jugador;
import game.Partida;
import objeto.GuanteBlanco;
import objeto.Objeto;
import objeto.PistolaCongelante;

public class ObjetoTest {

	Jugador primerJugador = null;
	Jugador segundoJugador = null;
	Partida partida = null;
	ArrayList<Objeto> mochila_objetos = null;

	@BeforeEach
	public void setUp() {
		primerJugador = new Jugador("Mario");
		segundoJugador = new Jugador("Princess Peach");
		ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
		listaJugadores.add(primerJugador);
		listaJugadores.add(segundoJugador);
		partida = new Partida(listaJugadores, 50);
		mochila_objetos = new ArrayList<Objeto>();
	}

	@Test
	public void usarObjetoPistolaCongelante() {
		Objeto pistola = new PistolaCongelante();
		pistola.setVictima(primerJugador); /// Setea victima de objeto
		mochila_objetos.add(pistola);
		segundoJugador.setMochila_objetos(mochila_objetos);
		segundoJugador.usarObjeto(0); // Se setea objeto en mochila del jugador, y este se utiliza
		assertEquals(true, primerJugador.isPierdeTurno());
	}

	@Test
	public void usarObjetoGuanteBlanco() {
		Objeto guante = new GuanteBlanco();
		guante.setVictima(primerJugador); /// Setea victima de objeto
		primerJugador.setMonedas(15);
		mochila_objetos.add(guante);
		segundoJugador.setMochila_objetos(mochila_objetos);
		segundoJugador.usarObjeto(0); // Se setea objeto en mochila del jugador, y este se utiliza
		assertEquals(10, primerJugador.getMonedas());
		assertEquals(5, segundoJugador.getMonedas());
	}

}
