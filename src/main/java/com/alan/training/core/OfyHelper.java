/**
 * 
 */
package com.alan.training.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alan.training.model.Greeting;
import com.alan.training.model.Guestbook;
import com.googlecode.objectify.ObjectifyService;

/**
 * @author alangabriel
 * 
 */
public class OfyHelper implements ServletContextListener {

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ObjectifyService.register(Greeting.class);
		ObjectifyService.register(Guestbook.class);

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
