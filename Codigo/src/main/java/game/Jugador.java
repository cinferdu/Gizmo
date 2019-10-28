package game;

import java.awt.Color;
import java.util.ArrayList;

import casilla.Casilla;
import objeto.Objeto;

public class Jugador {
	private int nroPasos;
	private String nombre;
	private int monedas;
	private int puntos;
	private boolean pierdeTurno;
	private Casilla posicionActual;
	private ArrayList<Objeto> mochila_objetos;
	private Objeto poder;
	private Color color;
	private int miniJuegoPuntos;
	private Personaje personaje;

	// Constructor de jugador con color
	public Jugador(String nombre) {
		this.nombre = nombre;
		pierdeTurno = false;
		posicionActual = null;

		monedas = 0;
		puntos = 0;
		miniJuegoPuntos = 0;

		mochila_objetos = new ArrayList<Objeto>();
		//this.setColor(color);
	}
	
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
	
	public Personaje getPersonaje() {
		return this.personaje;
	}

	public void usarObjeto(int index) {
		// Activa el efecto del objeto
		mochila_objetos.get(index).activarEfecto(this);
		mochila_objetos.remove(index);
	}

	public void usarPoder() {
		// Activa el efecto del poder
		// this.getPoder().efecto(this);
	}

	public Objeto activarPoder() {
		return this.poder;
	}
	/*
	 * public boolean accion() { // TODO int opcion = 0; switch (opcion) { case 1:
	 * this.usarObjeto(); break; case 2: this.usarPoder(); break;
	 * 
	 * 
	 * default: break; }
	 * 
	 * //Finaliza el turno
	 * 
	 * return true; }
	 */

	public void aumentarMonedas(int cantidad) {
		this.monedas += cantidad;
	}

	public void decrementarMonedas(int cantidad) {
		this.monedas = Math.max(monedas - cantidad, 0);
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

	public Casilla getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Casilla posicionActual) {
		this.posicionActual = posicionActual;
	}

	public Objeto getPoder() {
		return poder;
	}

	public void setPoder(Objeto poder) {
		this.poder = poder;
	}

	public int getNroPasos() {
		return nroPasos;
	}

	public void setNroPasos(int nroPasos) {
		this.nroPasos = nroPasos;
	}

	public void decrementarPasos() {
		this.nroPasos--;
	}

	public boolean isPierdeTurno() {
		return pierdeTurno;
	}

	public void setPierdeTurno(boolean pierdeTurno) {
		this.pierdeTurno = pierdeTurno;
	}

	public void activarCasilla() {
		this.posicionActual.activarCasilla(this);
	}
	/*
	 * public Casilla getCaminoElegido() { return caminoElegido; }
	 * 
	 * public void setCaminoElegido(Casilla caminoElegido) { this.caminoElegido =
	 * caminoElegido; }
	 */

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<Objeto> getMochila_objetos() {
		return mochila_objetos;
	}

	public void setMochila_objetos(ArrayList<Objeto> mochila_objetos) {
		this.mochila_objetos = mochila_objetos;
	}

	public Objeto getMochila_objetos(int index) {
		return mochila_objetos.get(index);
	}

	public void addMochila_objetos(Objeto objeto) {
		this.mochila_objetos.add(objeto);
	}

	@Override
	public String toString() {
		return nombre;
	}

	public void setMiniJuegoPuntos(int puntos) {
		miniJuegoPuntos = puntos;
	}
	
	public int getMiniJuegoPuntos() {
		return this.miniJuegoPuntos;
	}

}
