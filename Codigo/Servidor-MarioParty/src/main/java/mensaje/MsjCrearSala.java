package mensaje;

import sala.Sala;

public class MsjCrearSala extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String nombreSala;
	private String duenio;
	private int capMax;
	private String password;

	public MsjCrearSala(String nombreSala, String duenio, int capacidadMaxima) {
		this.nombreSala = nombreSala;
		this.duenio = duenio;
		this.capMax = capacidadMaxima;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		Sala nuevaSala = new Sala(nombreSala, duenio, capMax);
		nuevaSala.addCliente(duenio);
		if (password != null && password.length() > 0) {
			nuevaSala.setPassword(password);
		}
		
		listenerServer.agregarSala(nuevaSala);
		listenerServer.setSalaActiva(nuevaSala.getId_sala());
		
		MsjIngresarSala aEnviar = new MsjIngresarSala(nuevaSala);
		aEnviar.setResultado(true);
		listenerServer.getLobby().removeCliente(duenio);
		listenerServer.enviarMensaje(aEnviar);
		
		listenerServer.enviarMensajeBroadcast(new MsjAvisarActualizarSalas(listenerServer.getSalas()),listenerServer.getLobby().getNombreJugadores());
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
