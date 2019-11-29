package mensaje;

public class MsjPartidaEspectador extends Mensaje{

	private static final long serialVersionUID = 3911245659375705969L;
	private int id;
	
	public MsjPartidaEspectador(int id) {
		this.id = id;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
