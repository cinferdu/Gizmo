package game;

import java.util.ArrayList;

import casilla.Casilla;

public class Tablero {
	private Casilla casillaInicial = null;
	private ArrayList<Casilla> casilleros = null;

	public Tablero(int filas, int columnas) {
		casilleros = new ArrayList<Casilla>();
	}

	public Tablero(String file) {
		Lectura.cargarTablero(file, this);
		// Supongo que la primera casilla del arraylist es la inicial
		casillaInicial = casilleros.get(0);
	}

	public Casilla getCasillaInicial() {
		return casillaInicial;
	}

	public void setCasillaInicial(Casilla casillaInicial) {
		this.casillaInicial = casillaInicial;
	}

	public ArrayList<Casilla> getCasilleros() {
		return casilleros;
	}

	public void setCasilleros(ArrayList<Casilla> casilleros) {
		this.casilleros = casilleros;
	}
}
