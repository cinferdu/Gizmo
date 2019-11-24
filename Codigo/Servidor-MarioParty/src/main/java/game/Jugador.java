package game;

import java.awt.Color;
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
	private Color color;
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

	public Objeto usarObjeto(int indice) {
		// Activa el efecto del objeto
		mochila_objetos.get(indice).activarEfecto(this);
		return mochila_objetos.remove(indice);
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

	public Objeto usarObjeto(int objetoSelecionado, Jugador jugadorSeleccionado) {
		if (mochila_objetos.get(objetoSelecionado).isConObjetivo()) {
			mochila_objetos.get(objetoSelecionado).setVictima(jugadorSeleccionado);
		}
		return usarObjeto(objetoSelecionado);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Jugador other = (Jugador) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
