package casilla;

public class CreadorDeCasilla {

	// Devuelve una instacia de TipoDeCasilla
	public static TipoDeCasilla crearInstancia(int id) {
		if (Trampa.ID == id)
			return new Trampa();

		if (ConPremio.ID == id)
			return new ConPremio();

		if (ConItem.ID == id) {
			return new ConItem();
		}

		return null;
	}
}
