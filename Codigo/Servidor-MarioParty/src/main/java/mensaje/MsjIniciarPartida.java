package mensaje;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import game.Jugador;
import game.Partida;
import game.Personaje;
import game.Sprite;
import objeto.CajaMisteriosa;
import servidor.PartidaThread;
import servidor.Servidor;

public class MsjIniciarPartida extends Mensaje {
	
	private final static Logger LOGGER = Logger.getLogger(MsjIniciarPartida.class);

	private static final long serialVersionUID = 1L;
	private ArrayList<String> nombresJugadores;
	private Partida game;

	public MsjIniciarPartida(ArrayList<String> nombresJugadores) {
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		int cont = 0;
		ArrayList<Jugador> participantes = new ArrayList<Jugador>();
		for (String name : nombresJugadores) {
			Jugador jug = new Jugador(name);
			jug.setPersonaje(new Personaje(Sprite.SpriteById(cont % Sprite.values().length).getNombre()));
			jug.addMochila_objetos(new CajaMisteriosa());
			participantes.add(jug);
			cont ++;
		}

		this.game = new Partida(participantes, 50); // EN LA VENTANA DE CREAR SALA AGREGAR "OBJETIVO" o "LIMITE DE MONEDAS"
		LOGGER.info(game);
		PartidaThread hiloPartida = listenerServer.crearHiloPartida(game, nombresJugadores);
		listenerServer.asignarThread(game.getIdpartida(), nombresJugadores);
		
		synchronized (Servidor.partidas) {
			Servidor.partidas.put(game.getIdpartida(), hiloPartida);
		}
		listenerServer.enviarMensajeBroadcast(this, nombresJugadores);
	}

	public ArrayList<String> getNombresJugadores() {
		return nombresJugadores;
	}

	public Partida getGame() {
		return game;
	}

	public void setNombresJugadores(ArrayList<String> nombresJugadores) {
		this.nombresJugadores = nombresJugadores;
	}

	public void setGame(Partida game) {
		this.game = game;
	}

}
