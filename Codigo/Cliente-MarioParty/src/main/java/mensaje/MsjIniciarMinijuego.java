package mensaje;

import cliente.Cliente;
import minijuego.Loteria;

public class MsjIniciarMinijuego extends Mensaje{
	
	@Override
	public void ejecutar() {
		Loteria game = new Loteria();
		Cliente cliente = listenerClient.getCliente();
		this.clase = this.getClass().getSimpleName();
		cliente.enviarMensaje(new MsjResultadosMiniJuego(cliente,game.getNumerosElegidos()));
	}
	
}
