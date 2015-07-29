/**
 * 
 */
package com.alan.training.model;

import org.joda.time.DateTime;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * @author alangabriel
 * 
 */
@Entity
@Cache
public class Token {
	@Id
	private String id;
	private String token;
	private DateTime fecha;

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the token
	 */
	public final String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public final void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the fecha
	 */
	public final DateTime getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public final void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}

}
