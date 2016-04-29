/**
 * 
 */
package com.alan.training.providers;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.google.appengine.repackaged.org.codehaus.jackson.JsonProcessingException;
import com.google.appengine.repackaged.org.codehaus.jackson.map.JsonSerializer;
import com.google.appengine.repackaged.org.codehaus.jackson.map.SerializerProvider;

/**
 * @author alan
 *
 */
public class CustomSerializer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(DateTime dt, com.google.appengine.repackaged.org.codehaus.jackson.JsonGenerator generator,
            SerializerProvider paramSerializerProvider) throws IOException, JsonProcessingException {
        if (dt != null) {
            generator.writeString(ISODateTimeFormat.dateHourMinuteSecond().print(dt));
        } else {
            generator.writeNull();
        }

    }

}
