package game;

import java.util.ArrayList;

import casillas.Casilla;
import objetos.Objeto;

public class Jugador {
	private int nroPasos;
	private int rondaActual;
	private String nombre;
	private int monedas;
	private int puntos;
	private boolean pierdeTurno;
	private Casilla posicionActual;
	private Objeto objeto;
	private Objeto poder;

	// Constructor de jugador;
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.pierdeTurno = false;
		this.posicionActual = null;
		
		
		this.rondaActual = 0;
		this.monedas = 0;
		this.puntos = 0;
		
	}
	
	// TODO modificar
	public Casilla elegirCamino(Casilla casilleroActual) {
		// Elije el casillero
		int opcion = 0;
		// Agregue metodo de eleccion de camino
		System.out.println("Elija una casilla");
		for (int i = 0; i < casilleroActual.getSiguientesCasillas().size(); i++) {
			System.out.println((i+1) + ". " + casilleroActual.getSiguientesCasillas().get(i).getPosX()+"-"+casilleroActual.getSiguientesCasillas().get(i).getPosY());
		}
		//Leer opcion
		// Segun la eleccion
		return casilleroActual.getSiguientesCasillas().get(opcion);
	}
	
	public Casilla elegirCamino() {
		ArrayList<Casilla> caminosPosibles = (ArrayList<Casilla>) this.posicionActual.getSiguientesCasillas();
		int opcion = 0;
		// Elije el casillero
			// TODO Agregar metodo de eleccion de camino
		
		return caminosPosibles.get(opcion);
	}
	
	public void usarObjeto() {
		//Activa el efecto del objeto
		this.getObjeto().activarEfecto(this);
		
	}
	public void usarPoder() {
		//Activa el efecto del poder
		//this.getPoder().efecto(this);
	}
	
	public Objeto activarPoder() {
		return this.poder;
	}
	
	public boolean accion() {	
		// TODO
		int opcion = 0;
		switch (opcion) {
		case 1:
			this.usarObjeto();
			break;
		case 2:
			this.usarPoder();
			break;
			

		default:
			break;
		}

		//Finaliza el turno
		
		return true;
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

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
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

	public void activarCasilla() {
		this.posicionActual.activarCasilla(this);
	}


}
