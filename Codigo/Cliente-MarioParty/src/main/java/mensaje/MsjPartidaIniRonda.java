package mensaje;

import comunicacionObserver.Operacion;
import entornoGrafico.VentanaJuego;

public class MsjPartidaIniRonda extends Mensaje {

	private static final long serialVersionUID = 1L;
	private Operacion operacion;
	private int rondaNro;
	
	public MsjPartidaIniRonda(Operacion op, int ronda) {
		operacion = op;
		rondaNro = ronda;
		
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		listenerClient.getCliente().getPartidaActual().setRondaActual(rondaNro);
		VentanaJuego ventanaActual = (VentanaJuego) listenerClient.getCliente().getVentanaActual();
		ventanaActual.getPanel().nuevaRonda();
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public int getRondaNro() {
		return rondaNro;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	public void setRondaNro(int rondaNro) {
		this.rondaNro = rondaNro;
	}

	
}
