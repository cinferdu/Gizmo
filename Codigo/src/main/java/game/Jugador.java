package game;

public class Jugador {
	private int numero;
	private int nroPasos;
	private int rondaActual;
	private String nombre;
	private int monedas;
	private int puntos;
	private boolean pierdeTurno;
	boolean finTurno;
	Casillero posicionActual;
	Objeto objeto;
	Poder poder;

	// Constructor de jugador;
	
	public Jugador(int numero,Tablero tablero,String nombre) {
		
		this.numero = numero;
		this.setRondaActual(0);
		this.setFinTurno(false);
		this.nombre = nombre;
		this.monedas = 0;
		this.puntos = 0;
		this.posicionActual = tablero.getCasillaInicial();
		
	}

	public int tirarDado(DadoTrucado dado) {
		
		this.setNroPasos(dado.generar());
		
		return dado.generar();
	}
	
	public Casillero elegirCamino(Casillero casilleroActual) {
		// Elije el casillero
		int opcion = 0;
		// Agregue metodo de eleccion de camino
		
		// Segun la eleccion
		switch (opcion) {
		case 0:
			return casilleroActual.getCasilleroArriba();
		case 1:
			return casilleroActual.getCasilleroAbajo();
		case 2:
			return casilleroActual.getCasilleroDer();
		case 3:
			return casilleroActual.getCasilleroIzq();
		default:
			break;
		}
		// Si su eleccion fue no moverse;
		return casilleroActual;
		
		
		
		
	}
	
	public Objeto usarObjeto() {
		//Activa el efecto del objeto
		this.getObjeto().efecto(this);
		
		return this.objeto;
	}
	public void usarPoder() {
		//Activa el efecto del poder
		this.getPoder().efecto(this);
	}
	
	public Poder activarPoder() {
		return this.poder;
	}
	public boolean accion() {	
		int opcion = 0;
		switch (opcion) {

		// La primera opcion es usar el objeto
		case 1:
			this.usarObjeto();
			break;
			// El jugador elije usar el poder
		case 2:
			this.usarPoder();
			break;
			

		default:
			break;
		}

		//Finaliza el turno
		this.setFinTurno(true);
		
		return true;
	}
	//Avanza de a uno
	public boolean avanzar() {
		
		// Si el jugador sufre el efecto de perder un turno , lo pierde xd.
		if (isFinTurno()) {
			// Ya no lo sufre mas
			this.setFinTurno(false);
			return false;
		}
		
		// Avanza mientras tenga los nros de pasos suficientes o si la casilla es la final
		
		while (  this.getNroPasos()>0 && this.getPosicionActual().esFinal()==false) {
			
			this.setPosicionActual(this.getPosicionActual().getCasilleroDer());
			// Resto los pasos del jugador
			this.setNroPasos(this.getNroPasos()-1);
		}
		// En la casilla final activo el efecto de esta
		activarCasilla();
	
		return true;
	}
	public void activarCasilla() {
		
		switch (this.getPosicionActual().getTipo()) {
		case 1:
			setObjeto(getPosicionActual().getObjeto());
			break;
		case 2:
			setPoder(getPosicionActual().getPoder());
			break;
		case 3:
			setFinTurno(true);
			break;
		default:
			break;
		} 
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public boolean isFinTurno() {
		return finTurno;
	}

	public void setFinTurno(boolean finTurno) {
		this.finTurno = finTurno;
	}

	public Casillero getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Casillero posicionActual) {
		this.posicionActual = posicionActual;
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

	public int getNroPasos() {
		return nroPasos;
	}

	public void setNroPasos(int nroPasos) {
		this.nroPasos = nroPasos;
	}

	public int getRondaActual() {
		return rondaActual;
	}

	public void setRondaActual(int rondaActual) {
		this.rondaActual = rondaActual;
	}

	public boolean isPierdeTurno() {
		return pierdeTurno;
	}

	public void setPierdeTurno(boolean pierdeTurno) {
		this.pierdeTurno = pierdeTurno;
	}
}
