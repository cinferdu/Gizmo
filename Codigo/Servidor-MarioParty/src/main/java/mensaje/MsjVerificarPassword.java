package mensaje;

import sala.Sala;

public class MsjVerificarPassword extends Mensaje {

	private static final long serialVersionUID = 42961652314159484L;
	private String pass;
	private int idSala;
	
	public MsjVerificarPassword(String password, int id_sala) {
		pass = password;
		idSala = id_sala;
		clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		Sala sala = listenerServer.getSalas().get(idSala);
		if (sala.getPassword().equals(pass)) {
			listenerServer.sacarClienteDelLobby(listenerServer.getNombreCliente());
			listenerServer.setSalaActiva(idSala);
			
			listenerServer.enviarMensajeBroadcast(new MsjAvisarNuevoClienteEnSala(listenerServer.getNombreCliente()),
					sala.getNombreJugadores());
			
			sala.addCliente(listenerServer.getNombreCliente());
			MsjIngresarSala msj = new MsjIngresarSala(sala);
			msj.resultado = true;
			listenerServer.enviarMensaje(msj);
		}else {
			listenerServer.enviarMensaje(this);
		}
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

}