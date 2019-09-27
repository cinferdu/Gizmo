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
	
	public Casillero(int tipo,Casillero casilleroIzq,Casillero casilleroDer,Casillero casilleroArriba,Casillero casilleroAbajo,Objeto objeto,Poder poder) {
		// Define el tipo de casillero
		// tipo = 0 la casilla no tiene nada en particular
		//tipo = 1 la casilla tiene un objeto
		//tipo = 2 la casilla tiene un poder
		//tipo = 3 el jugador pierde un turno
		this.tipo = tipo;
		// Enlaza los casilleros
		this.casilleroAbajo = casilleroAbajo;
		this.casilleroArriba = casilleroArriba;
		this.casilleroDer = casilleroDer;
		this.casilleroIzq = casilleroIzq;
		if (tipo==1)
			this.setObjeto(objeto);
		if (tipo==2)
			this.setPoder(poder);
		
		
	}

	
	public boolean esFinal() {
		if (casilleroAbajo==null && casilleroArriba==null && casilleroDer==null && casilleroIzq == null)
			return true;
		return false;
	}
	public Casillero casillaDisponible() {
		// Retorna la casilla disponible
		if (casilleroAbajo!=null)
			return casilleroAbajo;
		if (casilleroArriba!=null)
			return casilleroArriba;
		if (casilleroDer!=null)
			return casilleroDer;
		else
			return casilleroIzq;
	}
	public boolean hayUnion() {
		
		if ( casilleroAbajo!=null && casilleroArriba!=null )
			return true;
		if ( casilleroAbajo!=null && casilleroDer!=null )
			return true;
		if ( casilleroDer!=null && casilleroArriba!=null )
			return true;
		return false;
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
