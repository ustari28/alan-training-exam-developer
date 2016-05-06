/**
 * 
 */
package com.alan.training.services;

import java.util.Map;

import com.google.api.client.util.Maps;

/**
 * @author alan
 *
 */
public class ChickensFactory {

    private static ChickensFactory factory;
    private Map<String, String> mapChickens = Maps.newHashMap();
    private Map<String, Object> mapImplementations = Maps.newHashMap();

    public static ChickensFactory getInstance() {
        if (factory == null) {
            factory = new ChickensFactory();
        }
        return factory;
    }

    public void put(String s, String obj) {
        mapChickens.put(s, obj);
        try {
            mapImplementations.put(obj, Class.forName(obj).newInstance());
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Object get(String s) {
        String clazz = mapChickens.get(s);
        return mapImplementations.get(clazz);
    }

    public Map<String, Object> getImpls() {
        return mapImplementations;
    }

    public void update(Map<String, Object> maps) {
        mapImplementations.putAll(maps);
    }
}
