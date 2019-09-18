package tp;

import java.util.ArrayList;

public class Mapa {
	private ArrayList mapa = new ArrayList();
	private String objetivo;
	
	public Mapa(int numero)
	{
		//1 Camino Normal
		//0 No puedo pasar
		//-1 Perder Puntos
		//2 Punto de Partida/Lugar para dar vuelta al tablero
		
		if(numero==1)
		{
			this.mapa = {{1, 1, 1, 1, 1, 1},{1, 0, 0, 0, 0, 1},{1, 0, 0, 0, 0, 1},{1, 0, 0, 0, 0, 1},{1, 1, -1, 1, 1, 1}};
			this.objetivo = "Maxima Cantidad de Puntos";
		}
	}
}
