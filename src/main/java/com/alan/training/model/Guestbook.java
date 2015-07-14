/**
 * 
 */
package com.alan.training.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * @author alangabriel
 * 
 */
@Entity
public class Guestbook {
	/**
	 * id.
	 */
	@Id
	public String book;
}
