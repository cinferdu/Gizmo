package mensaje;

import java.util.HashMap;

import cliente.Cliente;

public class MsjResultadosMiniJuego extends Mensaje {
	private static final long serialVersionUID = 1L;
	private HashMap<String, int[]> jugadas;

	public MsjResultadosMiniJuego(Cliente cliente,int[] resultados) {
		jugadas.put(cliente.getNombreCliente(), resultados);
		this.clase = getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		
	}
}
