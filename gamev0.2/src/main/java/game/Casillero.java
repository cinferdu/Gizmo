package game;

public class Casillero {
	private int posX;
	private int posY;
	private int tipo;
	private Objeto objeto;
	private Casillero casilleroDer = null;
	private Casillero casilleroIzq = null;
	private Casillero casilleroAbajo= null;
	private Casillero casilleroArriba = null;
	private Poder poder;
	
	public Casillero(int tipo,Casillero casilleroIzq,Casillero casilleroDer,Casillero casilleroArriba,Casillero casilleroAbajo) {
		// Define el tipo de casillero
		this.tipo = tipo;
		// Enlaza los casilleros
		this.casilleroAbajo = casilleroAbajo;
		this.casilleroArriba = casilleroArriba;
		this.casilleroDer = casilleroDer;
		this.casilleroIzq = casilleroIzq;
		
		
	}

	
	
	
	
	public Casillero getCasilleroDer() {
		return casilleroDer;
	}

	public void setCasilleroDer(Casillero casilleroDer) {
		this.casilleroDer = casilleroDer;
	}

	public Casillero getCasilleroIzq() {
		return casilleroIzq;
	}

	public void setCasilleroIzq(Casillero casilleroIzq) {
		this.casilleroIzq = casilleroIzq;
	}

	public Casillero getCasilleroAbajo() {
		return casilleroAbajo;
	}

	public void setCasilleroAbajo(Casillero casilleroAbajo) {
		this.casilleroAbajo = casilleroAbajo;
	}

	public Casillero getCasilleroArriba() {
		return casilleroArriba;
	}

	public void setCasilleroArriba(Casillero casilleroArriba) {
		this.casilleroArriba = casilleroArriba;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}
}
