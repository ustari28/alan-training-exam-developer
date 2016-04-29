/**
 * 
 */
package com.alan.training.services;

import java.lang.reflect.Field;

import com.alan.training.core.GAEResource;

/**
 * @author alan
 *
 */
public class WizzardService {

    private static WizzardService wizzard;

    public static WizzardService getInstance() {
        if (wizzard == null) {
            wizzard = new WizzardService();
        }

        return wizzard;
    }

    public final void wizzard(final Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            boolean accesible = false;
            System.out.println(f.toGenericString());
            if (f.isAnnotationPresent(GAEResource.class)) {
                System.out.println("elegido");
                GAEResource service = f.getAnnotation(GAEResource.class);
                accesible = f.isAccessible();
                f.setAccessible(true);
                try {
                    f.set(obj, ChickensFactory.getInstance().get(service.service()));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                f.setAccessible(accesible);
            }
        }
    }
}
