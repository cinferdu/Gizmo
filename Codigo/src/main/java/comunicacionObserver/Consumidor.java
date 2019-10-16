package comunicacionObserver;

import game.Jugador;

public interface Consumidor {
	public void actualizar(Operacion operacion,Jugador jugadorActual);
}
