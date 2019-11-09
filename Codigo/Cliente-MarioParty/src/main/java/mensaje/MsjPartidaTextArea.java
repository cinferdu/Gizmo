package mensaje;

import entornoGrafico.VentanaJuego;
import game.Jugador;
import game.Partida;

public class MsjPartidaTextArea extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String texto;
	private Jugador jugadorAct;

	public MsjPartidaTextArea(String texto, Jugador jugadorAct) {
		clase = getClass().getSimpleName();
		this.setTexto(texto);
		this.setJugadorAct(jugadorAct);
	}
	
	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().agregarTextoAlTextArea(texto);
		
		Partida game = listenerClient.getCliente().getPartidaActual();
		for (Jugador jugador : game.getJugadores()) {
			if (jugador.getNombre().equals(jugadorAct.getNombre())) {
				jugador.setMonedas(jugadorAct.getMonedas());
			}
		}
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Jugador getJugadorAct() {
		return jugadorAct;
	}

	public void setJugadorAct(Jugador jugadorAct) {
		this.jugadorAct = jugadorAct;
	}

}
