package com.alan.training.providers;

import java.io.IOException;

import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.appengine.repackaged.org.joda.time.DateTime;

/**
 * Configuración genérica de Jackson: no incluir atributos nulos, formato de
 * serialización de fechas de tipo DateTime
 * 
 * @author aaranda
 * 
 */
@Provider
public class CustomJacksonJsonProvider extends JacksonJsonProvider {

	public CustomJacksonJsonProvider() {
		super();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		SimpleModule module = new SimpleModule();
		module.addSerializer(DateTime.class, new JsonSerializer<DateTime>() {
			@Override
			public void serialize(DateTime dt, JsonGenerator generator,
			        SerializerProvider provider) throws IOException,
			        JsonProcessingException {
				if (dt != null) {
					generator.writeString(dt.toDate().toString());
				} else {
					generator.writeNull();
				}
			}
		});
		mapper.registerModule(module);
		setMapper(mapper);
	}
}
