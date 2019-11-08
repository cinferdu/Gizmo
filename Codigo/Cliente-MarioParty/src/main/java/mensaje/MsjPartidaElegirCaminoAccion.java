package mensaje;

import java.util.ArrayList;
import java.util.List;

import casilla.Casilla;
import entornoGrafico.VentanaJuego;
import game.Jugador;

public class MsjPartidaElegirCaminoAccion extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Casilla casillaElegida;
	private ArrayList<Casilla> caminosPosibles;
	private Jugador jugadorActual;
	
	public MsjPartidaElegirCaminoAccion(Jugador jugAct, Casilla casillaElegida) {
		super();
		this.casillaElegida = casillaElegida;
		jugadorActual = jugAct;
		this.clase = this.getClass().getSimpleName();
	}
	
	public MsjPartidaElegirCaminoAccion(Jugador jugAct, List<Casilla> caminosPosibles) {
		super();
		this.caminosPosibles = (ArrayList<Casilla>) caminosPosibles;
		jugadorActual = jugAct;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().mostrarOpcionesCamino(caminosPosibles);
	}

	public Casilla getCasillaElegida() {
		return casillaElegida;
	}

	public ArrayList<Casilla> getCaminosPosibles() {
		return caminosPosibles;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setCasillaElegida(Casilla casillaElegida) {
		this.casillaElegida = casillaElegida;
	}

	public void setCaminosPosibles(ArrayList<Casilla> caminosPosibles) {
		this.caminosPosibles = caminosPosibles;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

	
}
