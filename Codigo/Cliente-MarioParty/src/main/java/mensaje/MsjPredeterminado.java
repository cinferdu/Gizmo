package mensaje;

import entornoGrafico.VentanaJuego;

public class MsjPredeterminado extends Mensaje {

	private static final long serialVersionUID = 1L;

	private String emisor;
	private String msjpredeterminado;
	
	public MsjPredeterminado(String msj) {
		msjpredeterminado = msj;
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		((VentanaJuego) listenerClient.getCliente().getVentanaActual()).getPanel().mostrar_Mensaje(emisor, msjpredeterminado);
	}

	public String getMsjpredeterminado() {
		return msjpredeterminado;
	}

	public void setMsjpredeterminado(String msjpredeterminado) {
		this.msjpredeterminado = msjpredeterminado;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	
}
