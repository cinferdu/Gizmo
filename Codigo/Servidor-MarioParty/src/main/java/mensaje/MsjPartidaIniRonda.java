package mensaje;

public class MsjPartidaIniRonda extends Mensaje {

	private static final long serialVersionUID = 1L;
	private int rondaNro;
	
	public MsjPartidaIniRonda(int ronda) {
		super();
		rondaNro = ronda;
		
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		
	}
	
	public int getRondaNro() {
		return rondaNro;
	}

	public void setRondaNro(int rondaNro) {
		this.rondaNro = rondaNro;
	}


	
}
