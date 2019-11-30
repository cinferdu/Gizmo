package mensaje;

public class MsjCrearSala extends Mensaje {

	private static final long serialVersionUID = 1L;

	private String duenio;
	private int capMax;
	private String nombreSala;
	private String password;

	public MsjCrearSala(String nombreSala, int capMax, String duenio) {
		super();
		this.nombreSala = nombreSala;
		this.capMax = capMax;
		this.duenio = duenio;
		this.clase = this.getClass().getSimpleName();
	}
	
	public MsjCrearSala(String nombreSala, int capMax, String duenio, String pw) {
		this.nombreSala = nombreSala;
		this.capMax = capMax;
		this.duenio = duenio;
		this.clase = this.getClass().getSimpleName();
		this.password = pw;
	}

	@Override
	public void ejecutar() {

	}

	public String getDuenio() {
		return duenio;
	}

	public int getCapMax() {
		return capMax;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setDuenio(String duenio) {
		this.duenio = duenio;
	}

	public void setCapMax(int capMax) {
		this.capMax = capMax;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
