/**
 * 
 */
package com.alan.training.providers;

import java.lang.reflect.Type;

import org.joda.time.DateTime;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * @author alan
 *
 */
public class CustomDeserializer implements JsonDeserializer<DateTime> {

    // @Override
    // public DateTime deserialize(JsonParser jp, DeserializationContext paramDeserializationContext) throws
    // IOException,
    // JsonProcessingException {
    // String date = jp.getText();
    // if (date != null && date.length() > 0) {
    // return DateTime.parse(date);
    // }
    // return null;
    // }

    public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String date = json.getAsString();
        if (date != null && date.length() > 0) {
            return DateTime.parse(date);
        }
        return null;
    }

}
