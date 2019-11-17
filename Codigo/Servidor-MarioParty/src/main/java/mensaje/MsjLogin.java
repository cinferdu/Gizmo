package mensaje;

import java.util.TreeMap;

import controller.UsuarioController;
import model.Usuario;
import servidor.Sala;

public class MsjLogin extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String user;
	private String pass;
	// si resultado=false lo siguiente sera NULL
	private TreeMap<Integer, Sala> salas;

	public MsjLogin(String user,String pass) {
		super();
		this.user = user;
		this.pass = pass;
		this.resultado = false;
		this.salas = null;
		this.clase = this.getClass().getSimpleName();
	}
	@Override
	public void ejecutar() {
		if(UsuarioController.loggin(new Usuario(user, pass)) && !listenerServer.getClientesConectados().containsKey(user))
			resultado = true;
		
		if (resultado) {
			salas = listenerServer.getSalas();
			
			listenerServer.getClientesConectados().put(user, listenerServer);
			listenerServer.setNombreCliente(user);
			listenerServer.agregarClienteAlLobby(user);
		}
		
		listenerServer.enviarMensaje(this);
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public boolean isResultado() {
		return resultado;
	}

	public TreeMap<Integer, Sala> getSalas() {
		return salas;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public void setSalas(TreeMap<Integer, Sala> salas) {
		this.salas = salas;
	}

}
