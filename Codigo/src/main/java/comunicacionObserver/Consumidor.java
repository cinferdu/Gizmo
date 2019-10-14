package comunicacionObserver;

import game.Jugador;
import game.Partida;

public interface Consumidor {
	public void actualizar(Operacion operacion, Partida partida, Jugador jugadorActual);
}
