package mensaje;

import java.io.Serializable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import servidor.ListenerThread;

public abstract class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;
	protected transient ListenerThread listenerServer;
	protected String clase;
	protected boolean resultado;
	
	private final static Gson gson = new Gson();

	public abstract void ejecutar();

	public void setListener(ListenerThread lc) {
		this.listenerServer = lc;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	// se encargar de crear el mensaje correspondiente (con el getComando) y
	// cargarlo con la infomacion (transformando cadenaLeida al paquete que
	// necesite)
	public static Mensaje getMensaje(String cadenaLeida) {
		Mensaje msj = null;
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(cadenaLeida);
			msj = (Mensaje) gson.fromJson(cadenaLeida, Class.forName("mensaje." + json.get("clase")));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return msj;
	}
}
