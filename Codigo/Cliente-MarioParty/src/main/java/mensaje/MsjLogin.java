package mensaje;

public class MsjLogin extends Mensaje {

	private static final long serialVersionUID = 906137322216468141L;
	private String nombre;

	public MsjLogin(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void ejecutar() {
		/*HashMap<String, Socket> clientes = lc.getClientesConectados(); // Modif
		boolean resultado = !clientes.containsKey(nombre);
		
		if (resultado) {
			lc.setNombreCliente(nombre);
			lc.enviarMensaje(new RespLogin(resultado,lc.getSalas()));
		}else {
			lc.enviarMensaje(new RespLogin(resultado,null));
		}
		*/
	}

}
