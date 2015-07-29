/**
 * 
 */
package com.alan.training.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alan.training.model.Greeting;
import com.alan.training.model.Guestbook;
import com.alan.training.model.Token;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.DateTimeZoneTranslatorFactory;
import com.googlecode.objectify.impl.translate.opt.joda.LocalDateTimeTranslatorFactory;
import com.googlecode.objectify.impl.translate.opt.joda.LocalDateTranslatorFactory;
import com.googlecode.objectify.impl.translate.opt.joda.ReadableInstantTranslatorFactory;

/**
 * @author alangabriel
 * 
 */
public class OfyHelper implements ServletContextListener {

	static {

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ObjectifyService.factory().getTranslators()
		        .add(new ReadableInstantTranslatorFactory());
		ObjectifyService.factory().getTranslators()
		        .add(new LocalDateTranslatorFactory());
		ObjectifyService.factory().getTranslators()
		        .add(new LocalDateTimeTranslatorFactory());
		ObjectifyService.factory().getTranslators()
		        .add(new DateTimeZoneTranslatorFactory());
		ObjectifyService.register(Greeting.class);
		ObjectifyService.register(Guestbook.class);
		ObjectifyService.register(Token.class);
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
