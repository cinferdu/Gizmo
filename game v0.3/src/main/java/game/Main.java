package game;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		ArrayList<Jugador> participantes = new ArrayList<Jugador>();
		participantes.add(new Jugador("Mario"));
		participantes.add(new Jugador("Luigi"));
		Partida juego = new Partida(participantes, 50);
		juego.iniciarPartida();
		
	}
}