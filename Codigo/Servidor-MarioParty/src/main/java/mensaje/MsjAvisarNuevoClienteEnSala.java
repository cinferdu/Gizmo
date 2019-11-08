package mensaje;

public class MsjAvisarNuevoClienteEnSala extends Mensaje {

	private static final long serialVersionUID = 1L;

	private String userNuevo; //nuevo en la sala

	public MsjAvisarNuevoClienteEnSala(String userNuevo) {
		this.setUserNuevo(userNuevo);
		this.clase = this.getClass().getSimpleName();
	}
	
	@Override
	public void ejecutar() {
	}

	public String getUserNuevo() {
		return userNuevo;
	}

	public void setUserNuevo(String userNuevo) {
		this.userNuevo = userNuevo;
	}

}
