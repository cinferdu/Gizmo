package mensaje;

import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entornoGrafico.LobbyVentana;
import sala.Sala;

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
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		if (resultado) {
			
			JFrame ventana = listenerClient.getCliente().getVentanaActual();
			listenerClient.getCliente().setNombreCliente(user);
	
			ventana.dispose();
			ventana = new LobbyVentana(listenerClient.getCliente());
			((LobbyVentana) ventana).mostrarSala(salas);
			ventana.setVisible(true);
	
			listenerClient.getCliente().setVentanaActual(ventana);
		} else {
			JOptionPane.showMessageDialog(null, "User o pass incorrectos");
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
