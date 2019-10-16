package game;

public class Dado {

	public static int lanzarDado() {
		return (int) Math.random() * 6 + 1;
	}

	public static int lanzarDado(int caras) {
		return (int) Math.random() * caras + 1;
	}
	
}
