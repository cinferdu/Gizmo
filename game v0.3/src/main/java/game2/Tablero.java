package game2;

import java.util.ArrayList;
import java.util.List;

import casillas.Casilla;

public class Tablero {
	private int filas;
	private int columnas;
	private Casilla casillaInicial = null;
	private Casilla casillaFinal = null;
	private ArrayList<Casilla> casilleros = null;
	private String archivo;

	
	public Tablero(int filas, int columnas, String file) {

		this.filas = filas;
		this.columnas = columnas;
		archivo = file;
		casilleros = new ArrayList<Casilla>();
	}
	
	public void crearTablero(int filas,int columnas) {
		
		setFilas(filas);
		setColumnas(columnas);
		//TODO
		//casilleros = Lectura.cargarTablero(file);
		
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

	public Casilla getCasillaInicial() {
		return casillaInicial;
	}

	public void setCasillaInicial(Casilla casillaInicial) {
		this.casillaInicial = casillaInicial;
	}

	public Casilla getCasillaFinal() {
		return casillaFinal;
	}

	public void setCasillaFinal(Casilla casillaFinal) {
		this.casillaFinal = casillaFinal;
	}
}
