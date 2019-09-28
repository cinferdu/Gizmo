package game;

public class Objetivo {
	private String descripcion;
	private int tipo;
	private int puntosMax;
	private int monedasMax;
	private int recorridos;

	public Objetivo(int tipo,int criterio) {
		if (tipo==0) {
			this.setMonedasMax(criterio);
			this.setDescripcion("Monedas Max");
		}
		if (tipo==1) {
			this.setRecorridos(criterio);
			this.setDescripcion("Recorridos Max");
		}
		if (tipo==2) {
			this.setPuntosMax(criterio);
			this.setDescripcion("Puntaje Max");
		}
		if (tipo==3) {
			
			this.setDescripcion("LLegar a la casilla final");
		}

		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getPuntosMax() {
		return puntosMax;
	}

	public void setPuntosMax(int puntosMax) {
		this.puntosMax = puntosMax;
	}

	public int getMonedasMax() {
		return monedasMax;
	}

	public void setMonedasMax(int monedasMax) {
		this.monedasMax = monedasMax;
	}

	public int getRecorridos() {
		return recorridos;
	}

	public void setRecorridos(int recorridos) {
		this.recorridos = recorridos;
	}
}
