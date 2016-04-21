/**
 * 
 */
package com.alan.training.rest;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.io.IOUtils;

import com.alan.training.core.Utilidades;
import com.alan.training.dao.TokenService;
import com.alan.training.model.ErrorMensaje;
import com.alan.training.model.Mensaje;
import com.alan.training.model.Token;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author alangabriel
 * 
 */
@Path("/chat")
public class ChatRestService {

    /**
     * logger.
     */
    private final static Logger LOG = Logger.getLogger(ChatRestService.class.getCanonicalName());
    private TokenService tokenService;

    /**
	 * 
	 */
    public ChatRestService() {
        tokenService = new TokenService();
    }

    /**
     * Obtiene el login.
     * 
     * @param ui
     *            context.
     * @return login.
     */
    @GET
    @Path("/login")
    public Response login(@Context UriInfo ui) {
        UserService servicio = UserServiceFactory.getUserService();
        Token token = new Token();
        User usuario = servicio.getCurrentUser();
        if (usuario == null) {
            String url = servicio.createLoginURL(ui.getBaseUri().toString());
            ErrorMensaje error = new ErrorMensaje();
            error.setMensaje(url);
            return Response.ok(error).build();
        }
        ChannelService channelService = ChannelServiceFactory.getChannelService();
        token.setId(usuario.getEmail());
        token.setToken(channelService.createChannel(usuario.getEmail()));
        if (!tokenService.existe(token)) {
            tokenService.guardar(token);
        } else {
            token = tokenService.recuperar(usuario.getEmail());
        }
        return Response.ok(token).build();
    }

    @GET
    @Path("/conectados")
    public Response conectados() {
        UserService servicio = UserServiceFactory.getUserService();
        User usuario = servicio.getCurrentUser();
        return Response.ok(tokenService.listado(usuario.getEmail())).build();
    }

    @POST
    @Path("/sendMessage")
    public Response sendMessage(final Mensaje mensaje) {
        ChannelService channelService = ChannelServiceFactory.getChannelService();
        final String channel = mensaje.getToken();
        UserService servicio = UserServiceFactory.getUserService();
        User usuario = servicio.getCurrentUser();
        mensaje.setToken(null);
        mensaje.setId(usuario.getEmail());
        final ChannelMessage channelMessage = new ChannelMessage(channel, Utilidades.parserObjectListToJson(mensaje));
        channelService.sendMessage(channelMessage);
        return Response.ok().build();
    }

    @POST
    @Path("/testlogin")
    public Response testLogin(@Context HttpServletRequest request) {
        try {
            System.out.println("test login:" + IOUtils.toString(request.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/testlogin")
    public Response testLogin1(@Context HttpServletRequest request) {
        try {
            System.out.println("test login:" + IOUtils.toString(request.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Response.ok().build();
    }
}
