package game;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	private int filas;
	private int columnas;
	private List<Casillero> casilleros;
	
	public Tablero(int filas, int columnas) {
		super();
		this.filas = filas;
		this.columnas = columnas;
		casilleros = new ArrayList<Casillero>();
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public List<Casillero> getCasilleros() {
		return casilleros;
	}

	public void setCasilleros(List<Casillero> casilleros) {
		this.casilleros = casilleros;
	}
}
