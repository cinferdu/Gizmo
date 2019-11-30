package mensaje;

import java.util.List;

import controller.HistorialController;
import model.Historial;

public class MsjHistorial extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String user;
	private List<Historial> tabla;

	public MsjHistorial(String user) {
		super();
		this.user = user;
		this.clase = this.getClass().getSimpleName();
	}

	@Override
	public void ejecutar() {
		this.tabla = HistorialController.get(user);
		listenerServer.enviarMensaje(this);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Historial> getTabla() {
		return tabla;
	}

	public void setTabla(List<Historial> tabla) {
		this.tabla = tabla;
	}
}
