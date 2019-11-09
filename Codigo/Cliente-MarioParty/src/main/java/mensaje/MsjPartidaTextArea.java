package mensaje;

import entornoGrafico.VentanaJuego;

public class MsjPartidaTextArea extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String texto;
	
	public MsjPartidaTextArea(String texto) {
		clase = getClass().getSimpleName();
		this.setTexto(texto);
	}
	
	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().agregarTextoAlTextArea(texto);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
