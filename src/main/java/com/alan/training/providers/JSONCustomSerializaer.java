/**
 * 
 */
package com.alan.training.providers;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author alan
 *
 */
public class JSONCustomSerializaer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(DateTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException,
            JsonProcessingException {
        if (arg0 != null) {
            arg1.writeString(arg0.toString());
        } else {
            arg1.writeNull();
        }

    }

}
