package objeto;

public class GeneradorObjetoRandom {
	public static Objeto generar() {
		double result = Math.random();

		if (result > 0.90) // (0.9, 1)
			return new DadoDorado();
		if (result > 0.75) // (0.75, 0.9]
			return new GuanteBlanco();
		if (result > 0.45) // (0.45, 0.75]
			return new PistolaCongelante();
		if (result >= 0) // [0, 0.45]
			return new CajaMisteriosa();

		return null;
	}
}
