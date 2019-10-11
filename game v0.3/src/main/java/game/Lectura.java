package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import casillas.Casilla;
import static casillas.CreadorDeCasilla.crearInstancia;

public class Lectura {

	public static void cargarTablero(String archivo, Tablero tablero) {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		HashMap<Integer, Casilla> casillas = new HashMap<Integer, Casilla>();
		int id;
		int posX;
		int posY;
		int tdc;
		String[] siguientes;
		String[] anteriores;

		try {
			file = new File(archivo);
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String[] linea = br.readLine().split(" ");
			int cantidadDeCasillas = Integer.valueOf(linea[0]);
			int ancho = Integer.valueOf(linea[1]);
			int alto = Integer.valueOf(linea[2]);

			for (int i = 0; i < cantidadDeCasillas; i++) {
				linea = br.readLine().split(" ");

				id = Integer.valueOf(linea[0]);
				posX = Integer.valueOf(linea[1]);
				posY = Integer.valueOf(linea[2]);
				tdc = Integer.valueOf(linea[3]);
				anteriores = linea[4].split("\\|");
				siguientes = linea[5].split("\\|");

				casillas.put(id, new Casilla(posX, posY, crearInstancia(tdc)));

				int id_anterior = 0;
				for (int j = 0; j < anteriores.length
						&& casillas.containsKey(id_anterior = Integer.valueOf(anteriores[j])); j++) {
					casillas.get(id_anterior).addSiguiente(casillas.get(id));
				}
				int id_siguiente = 0;
				for (int j = 0; j < siguientes.length
						&& casillas.containsKey(id_siguiente = Integer.valueOf(siguientes[j])); j++) {
					casillas.get(id).addSiguiente(casillas.get(id_siguiente));
				}

			}
			
			br.close();
			
			ArrayList<Casilla> caminos = new ArrayList<Casilla>();
			for (int i = 0; i < casillas.size(); i++) {
				caminos.add(casillas.get(i));
			}
			
			tablero.setCasilleros(caminos);
			tablero.setFilas(alto);
			tablero.setColumnas(ancho);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
