/**
 * 
 */
package com.alan.tasks.entities;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author alan
 *
 */
public class File {

    private String name;
    @Value("${ENTORNO}")
    private String entorno;

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @return the entorno
     */
    public final String getEntorno() {
        return entorno;
    }

    /**
     * @param entorno
     *            the entorno to set
     */
    public final void setEntorno(String entorno) {
        this.entorno = entorno;
    }

}
