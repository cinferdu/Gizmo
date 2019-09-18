package tp;

public class Partida {

	private int rondas = 1;
	
	public boolean verifCantiJugadores(Jugador[] jugadores)
	{
		if(jugadores.length>=2)
			return true;
		
		return false;
	}
}
