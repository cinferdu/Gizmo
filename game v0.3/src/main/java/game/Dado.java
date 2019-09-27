package game;

public class Dado {
	private int caras;
	
	public Dado() {
		super();
		this.caras = 6;
	}

	public int generar() {
		return (int) Math.ceil((Math.random()*100) % this.caras);
	}
}
