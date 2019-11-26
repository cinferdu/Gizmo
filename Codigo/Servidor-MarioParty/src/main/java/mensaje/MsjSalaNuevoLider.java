package mensaje;

public class MsjSalaNuevoLider extends Mensaje {

	private static final long serialVersionUID = 3277208340981688838L;

	public MsjSalaNuevoLider() {
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
		
	}

}
