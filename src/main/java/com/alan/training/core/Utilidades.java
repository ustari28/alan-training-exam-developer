/**
 * 
 */
package com.alan.training.core;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author alangabriel
 * 
 */
public class Utilidades {

	/**
	 * Objecto a json.
	 * 
	 * @param object
	 *            Objecto.
	 * @return Json.
	 */
	public static String parserObjectListToJson(final Object object) {
		String json = "";
		try {
			SimpleModule module = new SimpleModule();
			module.addSerializer(DateTime.class,
			        new JsonSerializer<DateTime>() {
				        @Override
				        public void serialize(DateTime dt,
				                JsonGenerator generator,
				                SerializerProvider provider)
				                throws IOException, JsonProcessingException {
					        if (dt != null) {
						        generator.writeString(dt.toDate().toString());
					        } else {
						        generator.writeNull();
					        }
				        }
			        });
			json = new ObjectMapper().registerModule(module)
			        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
			        .writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace(System.out);
		}
		return json;
	}
}
