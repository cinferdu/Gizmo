package mensaje;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import casilla.TipoDeCasilla;
import deserializer.ObjetoDeserializer;
import deserializer.TipoDeCasillaJsonDeserializer;
import objeto.Objeto;
import servidor.ListenerThread;
import util.UtilesLog;

public abstract class Mensaje implements Serializable {
	
	private final static Logger LOGGER = Logger.getLogger(Mensaje.class);

	private static final long serialVersionUID = 1L;
	protected transient ListenerThread listenerServer;
	protected String clase;
	protected boolean resultado;
	
	private final static Gson gson = new GsonBuilder()
			.registerTypeAdapter(TipoDeCasilla.class, new TipoDeCasillaJsonDeserializer())
			.registerTypeAdapter(Objeto.class, new ObjetoDeserializer())
			.create();

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
		LOGGER.info(cadenaLeida);
		Mensaje msj = null;
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(cadenaLeida);
			msj = (Mensaje) gson.fromJson(cadenaLeida, Class.forName("mensaje." + json.get("clase")));
		} catch (ParseException e) {
			UtilesLog.loggerStackTrace(e, Mensaje.class);
		} catch (ClassNotFoundException e) {
			UtilesLog.loggerStackTrace(e, Mensaje.class);
		}
		return msj;
	}
}
