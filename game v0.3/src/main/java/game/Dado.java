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
	
	public int getCaras() {
		return caras;
	}
	
	public void setCaras(int caras) {
		this.caras = caras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + caras;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dado other = (Dado) obj;
		if (caras != other.caras)
			return false;
		return true;
	}
}
