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
    private int estado;

    /**
	 * 
	 */
    public Token() {
        estado = 1;
        fecha = new DateTime();
    }

    /**
     * @return the estado
     */
    public final int getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            the estado to set
     */
    public final void setEstado(int estado) {
        this.estado = estado;
    }

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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Token [id=" + id + ", token=" + token + ", fecha=" + fecha + ", estado=" + estado + "]";
    }

}
