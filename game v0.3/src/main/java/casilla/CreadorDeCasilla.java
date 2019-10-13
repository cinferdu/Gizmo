package casilla;

public class CreadorDeCasilla {
	 
	// Devuelve una instacia de TipoDeCasilla
	public static TipoDeCasilla crearInstancia(int id) {
		if (Trampa.getID() == id) 
			return new Trampa();
		
		if (ConPremio.getID() == id) 
			return new ConPremio();
		
		return null;
	}
}
