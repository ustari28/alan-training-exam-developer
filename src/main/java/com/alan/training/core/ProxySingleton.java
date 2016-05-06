/**
 * 
 */
package com.alan.training.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author alan
 *
 */
public class ProxySingleton implements InvocationHandler {

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("method:" + method.getName());
        return method.invoke(obj, args);
    }

}
