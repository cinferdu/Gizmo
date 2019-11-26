package mensaje;

import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cliente.Sala;
import entornoGrafico.LobbyVentana;

public class MsjLogin extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String user;
	private String pass;
	// si resultado=false lo siguiente sera NULL
	private TreeMap<Integer, Sala> salas;

	public MsjLogin(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
		this.resultado = false;
		this.salas = null;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		if (resultado) {
			JFrame ventana = listenerClient.getCliente().getVentanaActual();

			ventana.dispose();
			ventana = new LobbyVentana(listenerClient.getCliente());
			ventana.setVisible(true);
			ventana.setTitle("Bienvenido/a " + user);
			((LobbyVentana) ventana).mostrarSala(salas);

			listenerClient.getCliente().setVentanaActual(ventana);
			listenerClient.getCliente().setNombreCliente(user);
		} else {
			JOptionPane.showMessageDialog(null, "El nombre de usuario o la contraseña son incorrectas.");
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}
}
