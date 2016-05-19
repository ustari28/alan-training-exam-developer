/**
 * 
 */
package com.alan.training.providers;

import java.util.logging.Logger;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author alan
 *
 */
public class CustomObjectMapper extends ObjectMapper {

    /**
     * 
     */
    private static final long serialVersionUID = -1991651388073499064L;
    private static final Logger LOG = Logger.getLogger(CustomObjectMapper.class.getName());

    public CustomObjectMapper() {
        LOG.info("adding the new custom modules");
        SimpleModule module = new SimpleModule();
        module.addSerializer(DateTime.class, new JSONCustomSerializaer());
        module.addDeserializer(DateTime.class, new JSONCustomDeserializer());
        this.registerModule(module);
    }
}
