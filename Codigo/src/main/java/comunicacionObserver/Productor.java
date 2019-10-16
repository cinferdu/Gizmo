package comunicacionObserver;

import game.Jugador;

public interface Productor {
	public void registrar(Consumidor obs);
	public void desregistrar(Consumidor obs);
	public void avisar(Operacion operacion,Jugador jugadorActual);
}
