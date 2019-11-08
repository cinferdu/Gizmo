package mensaje;

import java.util.TreeMap;

import servidor.Sala;

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
	@Override
	public void ejecutar() {
		resultado = !listenerServer.getClientesConectados().containsKey(nombre);
		
		if (resultado) {
			salas = listenerServer.getSalas();
			
			listenerServer.getClientesConectados().put(nombre, listenerServer.getSalida());
			listenerServer.setNombreCliente(nombre);
			listenerServer.agregarClienteAlLobby(nombre);
		}
		
		listenerServer.enviarMensaje(this);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isResultado() {
		return resultado;
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}

}
