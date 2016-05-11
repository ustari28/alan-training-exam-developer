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

    private static final String PARAM_PACKAGE = "packages";
    private static final String PARAM_SEPARATOR = ",";

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {

        List<String> packagesChickens = Lists.newArrayList(sce.getServletContext().getInitParameter(PARAM_PACKAGE)
                .split(PARAM_SEPARATOR));
        // scan for create factory chickens
        for (String pkg : packagesChickens) {
            Set<Class<?>> classes = new Reflections(new ConfigurationBuilder().setUrls(
                    ClasspathHelper.forPackage(pkg.trim())).setScanners(new SubTypesScanner(),
                    new TypeAnnotationsScanner())).getTypesAnnotatedWith(GAEService.class);
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(GAEService.class)) {
                    ChickensFactory.getInstance().put(clazz.getSimpleName(), clazz.getCanonicalName());
                }
            }
        }
        // scan @GAEResource at chickens
        ChickensFactory.getInstance().refill();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        ChickensFactory.getInstance().destroy();

    }

}
