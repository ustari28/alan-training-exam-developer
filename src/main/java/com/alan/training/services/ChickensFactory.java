/**
 * 
 */
package com.alan.training.services;

import java.lang.reflect.Field;
import java.util.Map;

import com.alan.training.core.GAEResource;
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
            e.printStackTrace(System.out);
        } catch (IllegalAccessException e) {
            e.printStackTrace(System.out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    public void refill() {
        for (String key : mapImplementations.keySet()) {
            Object obj = mapImplementations.get(key);
            for (Field f : obj.getClass().getDeclaredFields()) {
                if (f.isAnnotationPresent(GAEResource.class)) {
                    boolean isAccessible = f.isAccessible();
                    GAEResource annotation = f.getAnnotation(GAEResource.class);
                    Object resource = get(annotation.service());
                    if (resource != null) {
                        try {
                            f.setAccessible(true);
                            f.set(obj, resource);
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace(System.out);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace(System.out);
                        } finally {
                            f.setAccessible(isAccessible);
                        }
                        mapImplementations.put(key, obj);
                    } else {
                        System.err.println("no se ha encontrado el objeto:" + annotation.service());
                    }
                }
            }
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

    public void destroy() {
        this.mapImplementations.clear();
        this.mapChickens.clear();
    }
}
