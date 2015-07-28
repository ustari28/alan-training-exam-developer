/**
 * 
 */
package com.alan.training.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alan.training.model.Token;

/**
 * @author alangabriel
 * 
 */
@Path("/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatRestService {

	@GET
	@Path("/login")
	public Response login() {
		final Token token = new Token();
		token.setId("hola");
		token.setToken("kkkkkkkkkkkk");
		return Response.ok(token).build();
	}
}
