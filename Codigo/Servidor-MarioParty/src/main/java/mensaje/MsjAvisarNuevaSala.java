package mensaje;

import java.util.TreeMap;

import servidor.Sala;

public class MsjAvisarNuevaSala extends Mensaje {

	private static final long serialVersionUID = 1L;
	private TreeMap<Integer, Sala> salas;
	
	
	public MsjAvisarNuevaSala(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {

	}

}
