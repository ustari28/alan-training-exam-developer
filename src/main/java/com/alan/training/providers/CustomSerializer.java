/**
 * 
 */
package com.alan.training.providers;

import java.lang.reflect.Type;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author alan
 *
 */
public class CustomSerializer implements JsonSerializer<DateTime> {

    public JsonElement serialize(DateTime dt, Type typeOfSrc, JsonSerializationContext generator) {
        if (dt != null) {
            return new JsonPrimitive(ISODateTimeFormat.dateHourMinuteSecond().print(dt));
        } else {
            return JsonNull.INSTANCE;
        }
    }

    // @Override
    // public void serialize(DateTime dt, com.google.appengine.repackaged.org.codehaus.jackson.JsonGenerator generator,
    // SerializerProvider paramSerializerProvider) throws IOException, JsonProcessingException {
    // if (dt != null) {
    // generator.writeString(ISODateTimeFormat.dateHourMinuteSecond().print(dt));
    // } else {
    // generator.writeNull();
    // }
    //
    // }

}
