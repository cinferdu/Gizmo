package mensajeCliente;

public class MsjClienteLogin extends MensajeCliente {
	private static final long serialVersionUID = -4786285125051083274L;
	private String nombre;

	public MsjClienteLogin(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void ejecutar() {
		
	}

}
