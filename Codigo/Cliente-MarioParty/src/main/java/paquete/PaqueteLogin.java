package paquete;

public class PaqueteLogin extends Paquete {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private boolean resultado;
	
	public PaqueteLogin(String nombre) {
		this.nombre = nombre;
		this.setComando("login");
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
	
	
}
