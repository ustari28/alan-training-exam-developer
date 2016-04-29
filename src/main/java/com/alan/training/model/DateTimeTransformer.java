/**
 * 
 */
package com.alan.training.model;

import org.joda.time.DateTime;

import com.google.api.server.spi.config.Transformer;

/**
 * @author alan
 *
 */
public class DateTimeTransformer implements Transformer<DateTime, String> {

    public String transformTo(DateTime paramTFrom) {
        return paramTFrom.toString();
    }

    public DateTime transformFrom(String paramTTo) {
        return DateTime.parse(paramTTo);
    }

}
