package comunicacionObserver;

import game.Jugador;

public interface Suscriptor {
	public void actualizar(Operacion operacion,Jugador jugadorActual);
}
