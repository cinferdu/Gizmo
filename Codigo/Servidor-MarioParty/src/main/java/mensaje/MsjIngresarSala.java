package mensaje;

import servidor.Sala;

public class MsjIngresarSala extends Mensaje {

	private static final long serialVersionUID = 1L;

	private Sala sala;
	private int id_sala;

	public MsjIngresarSala(Sala sala) {
		this.setSala(sala);
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		// si alguien pide entrar en una sala
		Sala salaSolicitada = listenerServer.getSalas().get(id_sala);
		if ((listenerServer.getSalas().containsKey(id_sala) == true)
				&& salaSolicitada.getNombreJugadores().size() < salaSolicitada.getLimiteJugadores()) {
			this.resultado = true;
			listenerServer.sacarClienteAlLobby(listenerServer.getNombreCliente());
			this.sala = salaSolicitada;
			listenerServer.setSalaActiva(salaSolicitada.getId_sala());
			
			listenerServer.enviarMensajeBroadcast(new MsjAvisarNuevoClienteEnSala(listenerServer.getNombreCliente()),
					sala.getNombreJugadores());
			
			this.sala.addCliente(listenerServer.getNombreCliente());
		} else {
			this.resultado = false;
		}

		listenerServer.enviarMensaje(this);
		
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

}
