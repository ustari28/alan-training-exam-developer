/**
 * 
 */
package com.alan.training.providers;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.alan.training.core.GAEService;
import com.alan.training.services.ChickensFactory;
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
        List<String> packagesChickens = Lists.newArrayList();
        // scan for create factory chickens
        packagesChickens.add("com.alan.training.services");
        for (String pkg : packagesChickens) {
            Set<Class<?>> classes = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(pkg))
                    .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()))
                    .getTypesAnnotatedWith(GAEService.class);
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(GAEService.class)) {
                    ChickensFactory.getInstance().putMap(clazz.getSimpleName(), clazz.getCanonicalName());
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
