package mensaje;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import casilla.ConItem;
import casilla.ConPremio;
import casilla.TipoDeCasilla;
import casilla.Trampa;

public class TipoDeCasillaJsonDeserializer implements JsonDeserializer<TipoDeCasilla> {
	@Override
	public TipoDeCasilla deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		String type = json.getAsJsonObject().get("nombre").getAsString();
		switch (type) {
		case "con Premio":
			return context.deserialize(json, ConPremio.class);
		case "con Item":
			return context.deserialize(json, ConItem.class);
		case "con Trampa":
			return context.deserialize(json, Trampa.class);
		default:
			throw new IllegalArgumentException("No se recibio un objeto TipoDeCasilla");
		}
	}
}