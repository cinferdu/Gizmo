package mensaje;

import javax.swing.JFrame;

import entornoGrafico.JSala;

public class MsjAvisarClienteAbandonoSala extends Mensaje {

	private static final long serialVersionUID = -8556787900292735591L;
	private String clienteNombre;

	public MsjAvisarClienteAbandonoSala(String user) {
		this.clienteNombre = user;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		JFrame ventanaActual = listenerClient.getCliente().getVentanaActual();
		((JSala) ventanaActual).eliminarDeSala(clienteNombre);
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

}