package mensaje;

import java.util.TreeMap;

import sala.Sala;

public class MsjSalirDeLaSala extends Mensaje {

	private static final long serialVersionUID = -8856954052534448631L;

	private TreeMap<Integer, Sala> salas;
	
	public MsjSalirDeLaSala() {
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		listenerServer.eliminarClienteDeSala();
		listenerServer.getLobby().addCliente(listenerServer.getNombreCliente());
		listenerServer.setSalaActiva(0);
		
		salas = listenerServer.getSalas();
		listenerServer.enviarMensaje(this);
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}
	
}