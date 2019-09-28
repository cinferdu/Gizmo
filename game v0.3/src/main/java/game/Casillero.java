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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casilleroAbajo == null) ? 0 : casilleroAbajo.hashCode());
		result = prime * result + ((casilleroArriba == null) ? 0 : casilleroArriba.hashCode());
		result = prime * result + ((casilleroDer == null) ? 0 : casilleroDer.hashCode());
		result = prime * result + ((casilleroIzq == null) ? 0 : casilleroIzq.hashCode());
		result = prime * result + ((objeto == null) ? 0 : objeto.hashCode());
		result = prime * result + ((poder == null) ? 0 : poder.hashCode());
		result = prime * result + tipo;
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
		Casillero other = (Casillero) obj;
		if (casilleroAbajo == null) {
			if (other.casilleroAbajo != null)
				return false;
		} else if (!casilleroAbajo.equals(other.casilleroAbajo))
			return false;
		if (casilleroArriba == null) {
			if (other.casilleroArriba != null)
				return false;
		} else if (!casilleroArriba.equals(other.casilleroArriba))
			return false;
		if (casilleroDer == null) {
			if (other.casilleroDer != null)
				return false;
		} else if (!casilleroDer.equals(other.casilleroDer))
			return false;
		if (casilleroIzq == null) {
			if (other.casilleroIzq != null)
				return false;
		} else if (!casilleroIzq.equals(other.casilleroIzq))
			return false;
		if (objeto == null) {
			if (other.objeto != null)
				return false;
		} else if (!objeto.equals(other.objeto))
			return false;
		if (poder == null) {
			if (other.poder != null)
				return false;
		} else if (!poder.equals(other.poder))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
}
