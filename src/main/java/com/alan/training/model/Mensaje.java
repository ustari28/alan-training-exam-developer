/**
 * 
 */
package com.alan.training.model;

/**
 * @author alangabriel
 * 
 */
public class Mensaje {

	private String token;
	private String mensaje;
	private String id;

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
	 * @return the mensaje
	 */
	public final String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public final void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
