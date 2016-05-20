/**
 * 
 */
package com.alan.common;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author alan
 *
 */
public class JSONCustomDeserializer extends JsonDeserializer<DateTime> {

    @Override
    public DateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException,
            JsonProcessingException {
        DateTime date = null;
        if (arg0.getText() != null & !arg0.getText().isEmpty()) {
            date = DateTime.parse(arg0.getText());
        }
        return date;
    }

}
