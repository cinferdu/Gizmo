package mensaje;

import game.Jugador;

public class MsjPartidaObjetoUsado extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String texto;
	private Jugador jugadorAct;
	
	public MsjPartidaObjetoUsado(String texto, Jugador jugadorAct) {
		clase = getClass().getSimpleName();
		this.setTexto(texto);
		this.setJugadorAct(jugadorAct);
	}
	
	@Override
	public void ejecutar() {

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
