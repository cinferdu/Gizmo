package mensaje;

import java.util.TreeMap;

import javax.swing.JFrame;

import entornoGrafico.LobbyVentana;
import sala.Sala;

public class MsjSalirDeLaSala extends Mensaje {

	private static final long serialVersionUID = -8856954052534448631L;
	
	private TreeMap<Integer, Sala> salas;
	
	public MsjSalirDeLaSala() {
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		JFrame ventana = listenerClient.getCliente().getVentanaActual();

		ventana.dispose();
		ventana = new LobbyVentana(listenerClient.getCliente());
		((LobbyVentana) ventana).mostrarSala(salas);
		ventana.setVisible(true);

		listenerClient.getCliente().setVentanaActual(ventana);
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}

}
