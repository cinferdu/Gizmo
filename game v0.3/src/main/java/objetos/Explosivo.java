package objetos;

import java.util.List;
import java.util.Scanner;

import game2.Jugador;

public class Explosivo extends Objeto {

	public Explosivo() {
		super("Explosivo", "Otro jugador pierda 1 turno");
	}

	@Override
	public Jugador elegirObjetivo(List<Jugador> jugadores, Jugador jugadorActual) {
		int i = 1;
		Scanner leer = new Scanner(System.in);
		jugadores.remove(jugadorActual);
		System.out.println("Elija su victima:");
		for (Jugador jugador : jugadores) {
			System.out.println(i+". "+jugador.getNombre());
			i++;
		}
		int opcion = leer.nextInt();
		leer.close();
		return jugadores.get(opcion-1);
	}
	
	public void activarEfecto(Jugador jugador) {
		
		jugador.setPierdeTurno(true);
	}


}
