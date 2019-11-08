package mensaje;

public class MsjPartidaLanzamientoDado extends Mensaje {

	private static final long serialVersionUID = 1L;
	private int dado;
	private String jugadorAct;
	
	public MsjPartidaLanzamientoDado(String jugAct, int dadoValor) {
		super();
		setDado(dadoValor);
		jugadorAct = jugAct;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		
	}
	
	public int getDado() {
		return dado;
	}

	public void setDado(int dado) {
		this.dado = dado;
	}

	public String getJugadorAct() {
		return jugadorAct;
	}

	public void setJugadorAct(String jugadorAct) {
		this.jugadorAct = jugadorAct;
	}

}
