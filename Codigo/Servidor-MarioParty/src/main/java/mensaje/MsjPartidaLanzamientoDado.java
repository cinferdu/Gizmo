package mensaje;

import game.Jugador;

public class MsjPartidaLanzamientoDado extends Mensaje {

	private static final long serialVersionUID = 1L;
	private int dado;
	
	public MsjPartidaLanzamientoDado(Jugador jugAct, int dadoValor) {
		super();
		setDado(dadoValor);
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

	
}
