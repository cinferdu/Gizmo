package mensaje;

import java.util.ArrayList;

import javax.swing.JFrame;

import entornoGrafico.VentanaJuego;
import game.Partida;

public class MsjIniciarPartida extends Mensaje {

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
		ventana = new VentanaJuego(listenerClient.getCliente());
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
