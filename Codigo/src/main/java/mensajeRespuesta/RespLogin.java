package mensajeRespuesta;

import java.util.ArrayList;

import mensaje.Mensaje;
import servidor.Sala;

public class RespLogin extends Mensaje { // cambiarlo por Paquete de Informacion?

	private boolean resultado;
	// si resultado=false lo siguiente sera NULL
	private ArrayList<Sala> salas;
	
	public RespLogin(boolean resultado, ArrayList<Sala> salas) {
		this.resultado = resultado;
		this.salas = salas;
	}
	
	@Override
	public void ejecutar() { // aca no hace nada, pero del lado Cliente si
		// TODO Auto-generated method stub
		
	}
}
