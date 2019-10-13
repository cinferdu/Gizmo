package game;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	private int filas;
	private int columnas;
	private Casillero casillaInicial = null;
	private Casillero casillaFinal = null;
	private ArrayList<Casillero> casilleros = null;

	
	public Tablero(int filas, int columnas) {

		this.filas = filas;
		this.columnas = columnas;
		casilleros = new ArrayList<Casillero>();
	}
	
	public void crearTablero(int filas,int columnas) {
		
		setFilas(filas);
		setColumnas(columnas);
		
		// Mini tablero de dos casillas desde el final al principio
		Casillero casilla2 = new Casillero(0, null, null, null, null,null,null);
		Casillero casilla1 = new Casillero(0, null, casilla2, null, null,null,null);
		
		// Agrego las casillas a la lista
		casilleros.add(casilla1);
		casilleros.add(casilla2);
		
		setCasillaFinal(casilla2);
		setCasillaInicial(casilla1);
		
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





	public Casillero getCasillaInicial() {
		return casillaInicial;
	}

	public void setCasillaInicial(Casillero casillaInicial) {
		this.casillaInicial = casillaInicial;
	}

	public Casillero getCasillaFinal() {
		return casillaFinal;
	}

	public void setCasillaFinal(Casillero casillaFinal) {
		this.casillaFinal = casillaFinal;
	}
}
