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
public abstract class AbstractService {

    public AbstractService() {
        start();
    }

    private void start() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            boolean accesible = false;
            System.out.println(f.toGenericString());
            if (f.isAnnotationPresent(GAEResource.class)) {
                System.out.println("elegido");
                GAEResource service = f.getAnnotation(GAEResource.class);
                accesible = f.isAccessible();
                f.setAccessible(true);
                try {
                    f.set(this, ChickensFactory.getInstance().get(service.service()));
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
