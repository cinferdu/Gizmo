package paquete;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import mensaje.Mensaje;

public class PaqueteToMensaje {
	// se encargar de crear el mensaje correspondiente (con el getComando) y cargarlo con la infomacion (transformando cadenaLeida al paquete que necesite)
	public static Mensaje getMensaje(Paquete paquete, String cadenaLeida) {
		Mensaje msj = null;
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(cadenaLeida);
			Gson gson = new Gson();
			msj = (Mensaje) gson.fromJson(cadenaLeida, Class.forName("mensaje." + json.get("clase")));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msj;
	}
}
