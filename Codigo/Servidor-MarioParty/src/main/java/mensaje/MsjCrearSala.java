package mensaje;

import servidor.Sala;

public class MsjCrearSala extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String nombreSala;
	private String duenio;
	private int capMax;

	public MsjCrearSala(String nombreSala, String duenio, int capacidadMaxima) {
		this.nombreSala = nombreSala;
		this.duenio = duenio;
		this.capMax = capacidadMaxima;
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		Sala nuevaSala = new Sala(nombreSala,duenio,capMax);
		serverListener.agregarSala(nuevaSala);
		
		MsjIngresarSala aEnviar = new MsjIngresarSala(nuevaSala);
		aEnviar.setResultado(true);
		serverListener.enviarPaquete(aEnviar);
	}

}
