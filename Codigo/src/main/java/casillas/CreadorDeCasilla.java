package casillas;

public class CreadorDeCasilla {
	
	public static TipoDeCasilla crearInstancia(int id) {
		if (Trampa.getID() == id) 
			return new Trampa();
		
		if (ConPremio.getID() == id) 
			return new ConPremio();
		
		return null;
	}
}
