package mensaje;

import entornoGrafico.VentanaJuego;

public class MsjPartidaIniRonda extends Mensaje {

	private static final long serialVersionUID = 1L;
	private int rondaNro;

	public MsjPartidaIniRonda(int ronda) {
		rondaNro = ronda;

		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		listenerClient.getCliente().getPartidaActual().setRondaActual(rondaNro);
		VentanaJuego ventanaActual = (VentanaJuego) listenerClient.getCliente().getVentanaActual();
		ventanaActual.getPanel().nuevaRonda();
	}

	public int getRondaNro() {
		return rondaNro;
	}

	public void setRondaNro(int rondaNro) {
		this.rondaNro = rondaNro;
	}

}
