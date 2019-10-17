package game;

public class Dado {

	/**
	 * Devuelve un valor entero entre 1 y 6
	 */
	public static int lanzarDado() {
		return (int) (Math.random() * 6 + 1);
	}

	/**
	 * Devuelve un valor entero entre 1 y el valor de caras
	 */
	public static int lanzarDado(int caras) {
		return (int) (Math.random() * caras + 1);
	}
}
