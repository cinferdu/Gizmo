package mensaje;

import com.google.gson.Gson;

import paquete.PaqueteCreacionSala;
import paquete.PaqueteSala;
import servidor.Sala;

public class MsjCrearSala extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String nombreSala;
	private String duenio;
	private int capMax;

	public MsjCrearSala(String cadenaLeida) {
		PaqueteCreacionSala paq = new Gson().fromJson(cadenaLeida, PaqueteCreacionSala.class);
		this.nombreSala = paq.getNombreSala();
		this.capMax = paq.getCapMax();
		this.duenio = paq.getDuenio();
	}
	
	@Override
	public void ejecutar() {
		Sala nuevaSala = new Sala(nombreSala,duenio,capMax);
		lc.agregarSala(nuevaSala);
		
		PaqueteSala paquete = new PaqueteSala(nuevaSala);
		paquete.setResultado(true);
		
		lc.enviarPaquete(paquete);
	}

}
