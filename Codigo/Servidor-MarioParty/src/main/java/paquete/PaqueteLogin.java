package paquete;

import java.util.TreeMap;

import servidor.Sala;

public class PaqueteLogin extends Paquete {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private TreeMap<Integer, Sala> salas;
	private boolean resultado;
	
	public PaqueteLogin(String nombre) {
		super("Login");
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public boolean isResultado() {
		return resultado;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	} 
	
}
