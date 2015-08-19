/**
 * 
 */
package com.alan.training.api;

import javax.ws.rs.HttpMethod;

import com.alan.training.model.Token;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

/**
 * @author alangabriel
 * 
 */
@Api(name = "testapi", version = "v1", defaultVersion = AnnotationBoolean.TRUE)
public class ServiciosApi {

	@ApiMethod(name = "hola", httpMethod = HttpMethod.GET, path = "hola")
	public Token api() {
		Token t = new Token();
		t.setId("alan");
		return t;
	}
}
