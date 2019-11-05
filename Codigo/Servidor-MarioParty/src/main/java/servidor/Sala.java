package servidor;

import java.util.ArrayList;

public class Sala implements Comparable<Sala> {
	
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

	public int getId_sala() {
		return id_sala;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public ArrayList<String> getNombreJugadores() {
		return nombreJugadores;
	}

	public String getNombreDuenio() {
		return nombreDuenio;
	}

	public String getEstado() {
		return estado;
	}

	public int getLimiteJugadores() {
		return limiteJugadores;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public void setNombreJugadores(ArrayList<String> nombreJugadores) {
		this.nombreJugadores = nombreJugadores;
	}

	public void setNombreDuenio(String nombreDuenio) {
		this.nombreDuenio = nombreDuenio;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setLimiteJugadores(int limiteJugadores) {
		this.limiteJugadores = limiteJugadores;
	}

	public void addCliente(String nombreCliente) {
		nombreJugadores.add(nombreCliente);
	}
	
	public void removeCliente(String nombreCliente) {
		nombreJugadores.remove(nombreCliente);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_sala;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (id_sala != other.id_sala)
			return false;
		return true;
	}

	@Override
	public int compareTo(Sala o) {
		return o.id_sala - this.id_sala;
	}
	
	
}
