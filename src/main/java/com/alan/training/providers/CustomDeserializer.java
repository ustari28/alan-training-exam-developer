/**
 * 
 */
package com.alan.training.providers;

import java.io.IOException;

import org.joda.time.DateTime;

import com.google.appengine.repackaged.org.codehaus.jackson.JsonProcessingException;
import com.google.appengine.repackaged.org.codehaus.jackson.map.DeserializationContext;
import com.google.appengine.repackaged.org.codehaus.jackson.map.JsonDeserializer;

/**
 * @author alan
 *
 */
public class CustomDeserializer extends JsonDeserializer<DateTime> {

    @Override
    public DateTime deserialize(com.google.appengine.repackaged.org.codehaus.jackson.JsonParser jp,
            DeserializationContext paramDeserializationContext) throws IOException, JsonProcessingException {
        String date = jp.getText();
        if (date != null && date.length() > 0) {
            return DateTime.parse(date);
        }
        return null;
    }

}
