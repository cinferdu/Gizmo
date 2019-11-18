package game;

import static casilla.CreadorDeCasilla.crearInstancia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import casilla.Casilla;
import util.UtilesLog;

public class Lectura {
	private final static Logger LOGGER = Logger.getLogger(Lectura.class);
	
	public static void cargarTablero(String archivo, Tablero tablero) {
		BufferedReader br = null;
		HashMap<Integer, Casilla> casillasHM = new HashMap<Integer, Casilla>();
		int id;
		int posX;
		int posY;
		int tdc; // TIPO DE CASILLA
		String[] siguientes;
		String[] anteriores;

		try {
			LOGGER.info("llego antes del reader!");
			LOGGER.info(Lectura.class.getResourceAsStream( "/" + archivo));
			br = new BufferedReader(new InputStreamReader(Lectura.class.getResourceAsStream( "/" + archivo)));

			String[] linea = br.readLine().split(" ");
			LOGGER.info(linea);
			int cantidadDeCasillas = Integer.valueOf(linea[0]);

			for (int i = 0; i < cantidadDeCasillas; i++) {
				linea = br.readLine().split(" ");
				LOGGER.info(linea.toString());
				id = Integer.valueOf(linea[0]);
				posX = Integer.valueOf(linea[1]);
				posY = Integer.valueOf(linea[2]);
				tdc = Integer.valueOf(linea[3]);
				anteriores = linea[4].split("\\|");
				siguientes = linea[5].split("\\|");

				casillasHM.put(id, new Casilla(posX, posY, crearInstancia(tdc)));

				int id_anterior = 0;
				for (int j = 0; j < anteriores.length
						&& casillasHM.containsKey(id_anterior = Integer.valueOf(anteriores[j])); j++) {
					casillasHM.get(id_anterior).addSiguiente(casillasHM.get(id));
				}
				int id_siguiente = 0;
				for (int j = 0; j < siguientes.length
						&& casillasHM.containsKey(id_siguiente = Integer.valueOf(siguientes[j])); j++) {
					casillasHM.get(id).addSiguiente(casillasHM.get(id_siguiente));
				}

			}
			
			br.close();
			
			ArrayList<Casilla> caminos = new ArrayList<Casilla>();
			for (int i = 0; i < casillasHM.size(); i++) {
				caminos.add(casillasHM.get(i));
			}
			
			tablero.setCasilleros(caminos);
			
		} catch (FileNotFoundException e) {
			UtilesLog.loggerStackTrace(e, Lectura.class);
		} catch (IOException e) {
			UtilesLog.loggerStackTrace(e, Lectura.class);
		}

	}
	
}
