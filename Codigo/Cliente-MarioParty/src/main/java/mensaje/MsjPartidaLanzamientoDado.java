package mensaje;

import entornoGrafico.VentanaJuego;

public class MsjPartidaLanzamientoDado extends Mensaje {

	private static final long serialVersionUID = 1L;
	private int dado;
	private String jugadorAct;

	public MsjPartidaLanzamientoDado(String jugAct, int dadoValor) {
		super();
		setDado(dadoValor);
		jugadorAct = jugAct;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().lanzamiento_dado(jugadorAct, dado);

	}

	public int getDado() {
		return dado;
	}

	public void setDado(int dado) {
		this.dado = dado;
	}

	public String getJugadorAct() {
		return jugadorAct;
	}

	public void setJugadorAct(String jugadorAct) {
		this.jugadorAct = jugadorAct;
	}

}
