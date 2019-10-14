package comunicacionObserver;

import game.Jugador;
import game.Partida;

public interface Productor {
	public void registrar(Consumidor obs);
	public void desregistrar(Consumidor obs);
	public void avisar(Operacion operacion, Partida partida, Jugador jugadorActual);
}
