package mensajeRespuesta;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.Sala;
import entornoGrafico.JSala;
import mensaje.Mensaje;
import paquete.PaqueteSala;

public class RespSala extends Mensaje {

	private static final long serialVersionUID = 1L;
	private boolean resultado;
	private Sala sala;

	public RespSala(String cadenaLeida) {
		PaqueteSala paq = new Gson().fromJson(cadenaLeida, PaqueteSala.class);
		resultado = paq.isResultado();
		this.sala = paq.getSala();
	}
	
	@Override
	public void ejecutar() {
		if (resultado) {
			JFrame ventanaActual = this.listener.getCliente().getVentanaActual();
			ventanaActual.dispose();
			ventanaActual = new JSala(listener.getCliente());
			ventanaActual.setVisible(true);
			((JSala) ventanaActual).inicializarSala(this.sala);
			
		} else {
			JOptionPane.showMessageDialog(null, "La sala no fue creada");
		}
		
	}

}
