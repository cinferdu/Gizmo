package mensajeRespuesta;

import java.awt.Frame;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.Sala;
import entornoGrafico.LobbyVentana;
import mensaje.Mensaje;
import paquete.PaqueteLogin;

public class RespLogin extends Mensaje { // cambiarlo por Paquete de Informacion?

	private String nombre;
	private boolean resultado;
	// si resultado=false lo siguiente sera NULL
	private TreeMap<Integer, Sala> salas;
	
	public RespLogin(boolean resultado, TreeMap<Integer, Sala> salas) {
		this.resultado = resultado;
		this.salas = salas;
	}
	
	public RespLogin(String cadenaLeida) {
		PaqueteLogin paq = new Gson().fromJson(cadenaLeida, PaqueteLogin.class);
		resultado = paq.isResultado();
		if (resultado) {
			nombre = paq.getNombre();
			salas = paq.getSalas();
		}
	}
	
	@Override
	public void ejecutar() { // aca no hace nada, pero del lado Cliente si
		if (resultado) {
			Frame ventana = listener.getCliente().getVentanaActual();
			ventana.dispose();
			ventana = new LobbyVentana();
			ventana.setVisible(true);
			ventana.setTitle("Bienvenido/a " + nombre);
		}else {
			JOptionPane.showMessageDialog(null, "Ya existe una sesión iniciada con ese usuario.");
		}
	}
}
