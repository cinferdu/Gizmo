package mensaje;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import entornoGrafico.VentanaJuego;
import game.Partida;

public class MsjIniciarPartida extends Mensaje {

	private final static Logger LOGGER = Logger.getLogger(MsjIniciarPartida.class);
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> nombresJugadores;
	private Partida game;
	
	public MsjIniciarPartida(ArrayList<String> nombresJugadores) {
		this.nombresJugadores = nombresJugadores;
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		JFrame ventana = listenerClient.getCliente().getVentanaActual();
		ventana.dispose();
		listenerClient.getCliente().setPartidaActual(game);
		LOGGER.info("Cierra ventana!!!");
		ventana = new VentanaJuego(listenerClient.getCliente());
		LOGGER.info("Abre ventana!!!");
		listenerClient.getCliente().setVentanaActual(ventana);
		ventana.setVisible(true);
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
