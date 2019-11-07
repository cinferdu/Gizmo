package mensaje;

import java.io.Serializable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import cliente.Listener;

public abstract class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -6544865381140109432L;
	protected transient Listener listenerClient;
	protected String clase;
	protected boolean resultado;
	
	public abstract void ejecutar();
	
	public void setListener(Listener lc) {
		this.listenerClient = lc;
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
	
	// se encargar de crear el mensaje correspondiente (con el getComando) y cargarlo con la infomacion (transformando cadenaLeida al paquete que necesite)
	public static Mensaje getMensaje(String cadenaLeida) {
		Mensaje msj = null;
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(cadenaLeida);
			Gson gson = new Gson();
			msj = (Mensaje) gson.fromJson(cadenaLeida, Class.forName("mensaje." + json.get("clase")));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return msj;
	}
}
