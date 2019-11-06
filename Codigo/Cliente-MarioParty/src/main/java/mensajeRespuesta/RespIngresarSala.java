package mensajeRespuesta;

import javax.swing.JFrame;

import com.google.gson.Gson;

import cliente.Sala;
import entornoGrafico.JSala;
import mensaje.Mensaje;
import paquete.PaqueteSala;

public class RespIngresarSala extends Mensaje {

	private static final long serialVersionUID = 1L;

	private Sala sala;
	
	public RespIngresarSala(String cadenaLeida) {
		PaqueteSala paq = new Gson().fromJson(cadenaLeida, PaqueteSala.class);
		sala = paq.getSala();
	}

	@Override
	public void ejecutar() {
		JFrame ventanaAct = listener.getCliente().getVentanaActual();
		ventanaAct.dispose();
		ventanaAct = new JSala(listener.getCliente());
		ventanaAct.setVisible(true);
	}

}
