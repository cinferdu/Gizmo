package mensaje;

public class MsjLogin extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String dni;
	private String domicilio;
	private String clase;

	public MsjLogin(String nombre, String apellido, String dni, String domicilio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.domicilio = domicilio;
		this.clase = this.getClass().getSimpleName();
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

	public String getLala() {
		return clase;
	}

	public void setLala(String lala) {
		this.clase = lala;
	}

	@Override
	public void ejecutar() {
	}
}
