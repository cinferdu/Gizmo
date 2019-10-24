package comunicacionObserver;

import game.Jugador;

public interface Publicador {
	public void registrar(Suscriptor obs);
	public void desregistrar(Suscriptor obs);
	public void avisar(Operacion operacion,Jugador jugadorActual);
}
