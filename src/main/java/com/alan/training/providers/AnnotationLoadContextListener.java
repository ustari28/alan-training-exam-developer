/**
 * 
 */
package com.alan.training.providers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.alan.training.core.GAEScan;
import com.alan.training.core.GAEService;
import com.google.common.collect.Lists;

/**
 * @author alan
 *
 */
public class AnnotationLoadContextListener implements ServletContextListener {

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        List<String> packages = Lists.newArrayList();
        packages.add("com.alan.training.services");

        for (String pkg : packages) {
            Set<Class<?>> classes = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(pkg))
                    .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()))
                    .getTypesAnnotatedWith(GAEScan.class);
            for (Class<?> clazz : classes) {
                System.out.println(clazz.getName());
                Field[] fields = clazz.getDeclaredFields();
                for (Field f : fields) {
                    System.out.println(f.toGenericString());
                    if (f.isAnnotationPresent(GAEService.class)) {
                        System.out.println("elegido");
                        GAEService service = f.getAnnotation(GAEService.class);
                        try {
                            f.set(null, Class.forName(service.service()).newInstance());
                        } catch (InstantiationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
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
                }
            }

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

}
