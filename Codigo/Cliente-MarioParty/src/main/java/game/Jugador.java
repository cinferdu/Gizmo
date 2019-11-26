package game;

import java.util.ArrayList;

import casilla.Casilla;
import objeto.Objeto;

public class Jugador {
	private int nroPasos;
	private String nombre;
	private int monedas;
	private boolean pierdeTurno;
	private Casilla posicionActual;
	private ArrayList<Objeto> mochila_objetos;
	private int miniJuegoPuntos;
	private Personaje personaje;

	// Constructor de jugador con color
	public Jugador(String nombre) {
		this.nombre = nombre;
		pierdeTurno = false;
		posicionActual = null;

		monedas = 0;
		miniJuegoPuntos = 0;

		mochila_objetos = new ArrayList<Objeto>(3);
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public Personaje getPersonaje() {
		return this.personaje;
	}

	public void usarObjeto(int indice) {
		// Activa el efecto del objeto
		mochila_objetos.get(indice).activarEfecto(this);
		mochila_objetos.remove(indice);
	}

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

	public Casilla getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Casilla posicionActual) {
		this.posicionActual = posicionActual;
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
		if (mochila_objetos.size() < 3) {
			mochila_objetos.add(objeto);
		}
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

	public boolean isMochilaVacia() {
		return mochila_objetos.isEmpty();
	}

}
