package game;

public class Jugador {
	private int numero;
	private String nombre;
	private int monedas;
	int puntos;
	boolean finTurno;
	Casillero posicionInicial;
	Objeto objeto;
	Poder poder;

	public Jugador() {
		super();
	}

	public int tirarDado(Dado dado) {
		return 1;
	}
	
	public void elegirCamino(Casillero casillero) {
		// ??
	}
	
	public Objeto usarObjeto() {
		return this.objeto;
	}
	
	public Poder activarPoder() {
		return this.poder;
	}
	
	public void avanzar(int cantidad) {
		//next casillero ?? como llego si no tengo asociado la partida?
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

	public Casillero getPosicionInicial() {
		return posicionInicial;
	}

	public void setPosicionInicial(Casillero posicionInicial) {
		this.posicionInicial = posicionInicial;
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
