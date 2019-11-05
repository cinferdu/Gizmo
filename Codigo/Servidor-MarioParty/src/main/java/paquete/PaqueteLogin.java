package paquete;

public class PaqueteLogin extends Paquete {
	private String nombre;
	private String password;
	private boolean resultado;
	
	public PaqueteLogin(String nombre, String pass) {
		this.nombre = nombre;
		this.password = pass;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getPassword() {
		return password;
	}
	public boolean isResultado() {
		return resultado;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	} 
	
	
}
