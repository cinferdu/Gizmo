package servidor;

import java.util.ArrayList;

public class Sala {
	
	private int id_sala;
	private String nombreSala;
	private ArrayList<String> nombreJugadores;
	private String nombreDuenio;
	private String estado; // cambiar por una Clase
	private int limiteJugadores;
	private static int salasCreadas = 0;
	
	public Sala() { // lobby
		this.id_sala = 0;
		nombreSala = "Lobby";
		nombreDuenio = "";
		estado = "";
		limiteJugadores = Integer.MAX_VALUE;
		nombreJugadores = new ArrayList<String>();
	}
	
	public Sala(String nombre, String duenio, int limite) { // salas creadas por los clientes
		this.id_sala = ++salasCreadas;
		nombreSala = nombre;
		nombreDuenio = duenio;
		estado = "Abierta";
		limiteJugadores = limite;
	}

	public void agregarCliente(String nombreCliente) {
		nombreJugadores.add(nombreCliente);
	}
	
	public void sacarCliente(String nombreCliente) {
		nombreJugadores.remove(nombreCliente);
	}

	
}
