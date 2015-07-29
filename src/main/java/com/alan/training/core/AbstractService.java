/**
 * 
 */
package com.alan.training.core;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

/**
 * @author alangabriel
 * 
 */
public abstract class AbstractService {
	protected Objectify service;

	public AbstractService() {
		service = ObjectifyService.ofy();
	}
}
