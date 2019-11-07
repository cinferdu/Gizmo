package mensaje;

import java.awt.Frame;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import cliente.Sala;
import entornoGrafico.LobbyVentana;

public class MsjLogin extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String nombre;
	// si resultado=false lo siguiente sera NULL
	private TreeMap<Integer, Sala> salas;

	public MsjLogin(String nombre) {
		super();
		this.nombre = nombre;
		this.resultado = false;
		this.salas = null;
		this.clase = this.getClass().getSimpleName();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}

	@Override
	public void ejecutar() {
		if (resultado) {
			Frame ventana = clientListener.getCliente().getVentanaActual();
			
			ventana.dispose();
			ventana = new LobbyVentana(clientListener.getCliente());
			ventana.setVisible(true);
			ventana.setTitle("Bienvenido/a " + nombre);
			((LobbyVentana) ventana).mostrarSala(salas);
			
			clientListener.getCliente().setNombreCliente(nombre);
		}else {
			JOptionPane.showMessageDialog(null, "Ya existe una sesión iniciada con ese usuario.");
		}
	}
}
