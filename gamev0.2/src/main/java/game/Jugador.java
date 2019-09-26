package game;

public class Jugador {
	private int numero;
	private int nroPasos;
	private String nombre;
	private int monedas;
	int puntos;
	boolean finTurno;
	Casillero posicionActual;
	Objeto objeto;
	Poder poder;

	// Constructor de jugador;
	
	public Jugador(int numero,Partida partida,String nombre) {
		this.numero = numero;
		this.nombre = nombre;
		this.monedas = 0;
		this.puntos = 0;
		this.posicionActual = partida.getCasillaInicial();
		
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
		return this.objeto;
	}
	
	public Poder activarPoder() {
		return this.poder;
	}
	
	//Avanza de a uno
	public void avanzar() {
		
		// Seteo la casilla actual con la elejida
		this.setPosicionActual(elegirCamino(this.getPosicionActual()));
		// Resto la cantidad de pasos
		this.setNroPasos(this.getNroPasos()-1);
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
}
