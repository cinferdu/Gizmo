package mensaje;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import objeto.CajaMisteriosa;
import objeto.DadoDorado;
import objeto.GuanteBlanco;
import objeto.Objeto;
import objeto.PistolaCongelante;

public class ObjetoDeserializer implements JsonDeserializer<Objeto> {
	@Override
	public Objeto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		String type = json.getAsJsonObject().get("nombre").getAsString();
		switch (type) {
		case "Caja Misteriosa":
			return context.deserialize(json, CajaMisteriosa.class);
		case "Dado dorado":
			return context.deserialize(json, DadoDorado.class);
		case "Guante Blanco":
			return context.deserialize(json, GuanteBlanco.class);
		case "Pistola Congelante":
			return context.deserialize(json, PistolaCongelante.class);
		default:
			throw new IllegalArgumentException("Se espera un objeto de tipo Objeto");
		}
	}

}
