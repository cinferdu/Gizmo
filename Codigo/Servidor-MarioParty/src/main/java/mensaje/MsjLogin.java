package mensaje;

import java.io.DataOutputStream;
import java.util.HashMap;

import com.google.gson.Gson;

import paquete.PaqueteLogin;

public class MsjLogin extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String dni;
	private String domicilio;
	
	public MsjLogin() {}

	public MsjLogin(String cadenaLeida) {
		PaqueteLogin paq = new Gson().fromJson(cadenaLeida, PaqueteLogin.class);
		nombre = paq.getNombre();
		apellido = paq.getNombre();
		dni = paq.getNombre();
		domicilio = paq.getNombre();
	}
	
	public MsjLogin(String nombre, String apellido, String dni, String domicilio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.domicilio = domicilio;
	}

	@Override
	public void ejecutar() {
		HashMap<String, DataOutputStream> clientes = lc.getClientesConectados(); // Modif
		boolean resultado = !clientes.containsKey(nombre);

		PaqueteLogin aEnviar = new PaqueteLogin(nombre);
		aEnviar.setResultado(resultado);
		aEnviar.setSalas(lc.getSalas());
		
		if (resultado) {
			clientes.put(nombre, lc.getSalida());
			lc.setNombreCliente(nombre);
			lc.getLobby().addCliente(nombre);
			aEnviar.setSalas(lc.getSalas());
			lc.enviarPaquete(aEnviar);
		} else {
			lc.enviarPaquete(aEnviar);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
}
