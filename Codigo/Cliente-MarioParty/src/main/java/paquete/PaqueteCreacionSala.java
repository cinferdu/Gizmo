package paquete;

public class PaqueteCreacionSala extends Paquete {

	private static final long serialVersionUID = 1L;
	private String duenio;
	private int capMax;
	private String nombreSala;

	public PaqueteCreacionSala(String nombreSala, int capMax, String duenio) {
		super("CrearSala");
		this.nombreSala = nombreSala;
		this.capMax = capMax;
		this.duenio = duenio;
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
	
	

}
