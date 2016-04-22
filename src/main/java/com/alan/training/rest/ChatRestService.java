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
import com.alan.training.model.PushGMail;
import com.alan.training.model.Token;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.PubsubScopes;
import com.google.api.services.pubsub.model.PushConfig;
import com.google.api.services.pubsub.model.Subscription;
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

    @POST
    @Path("/pushemail")
    public Response pushEmail(PushGMail pushGmail) {
        LOG.info(pushGmail.toString());
        return Response.ok().build();
    }

    @GET
    @Path("/registerwatch")
    public Response registerWatch() {
        String pushEndpoint = "https://alan-training-exam-developer.appspot.com/rest/chat/pushemail";
        PushConfig pushConfig = new PushConfig().setPushEndpoint(pushEndpoint);
        Pubsub pubsub;
        try {
            pubsub = createPubsubClient();
            Subscription subscription = new Subscription()
            // The name of the topic from which this subscription
            // receives messages
                    .setTopic("Couchbase")
                    // Ackowledgement deadline in second
                    .setAckDeadlineSeconds(10)
                    // Only needed if you are using push delivery
                    .setPushConfig(pushConfig);
            Subscription newSubscription = pubsub.projects().subscriptions().create("miregistro1", subscription)
                    .execute();
            LOG.info(newSubscription.toPrettyString());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return Response.ok().build();
    }

    // Default factory method.
    public static Pubsub createPubsubClient() throws IOException {
        return createPubsubClient(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory());
    }

    // A factory method that allows you to use your own HttpTransport
    // and JsonFactory.
    public static Pubsub createPubsubClient(HttpTransport httpTransport, JsonFactory jsonFactory) throws IOException {
        Preconditions.checkNotNull(httpTransport);
        Preconditions.checkNotNull(jsonFactory);
        GoogleCredential credential = GoogleCredential.getApplicationDefault(httpTransport, jsonFactory);
        // In some cases, you need to add the scope explicitly.
        if (credential.createScopedRequired()) {
            credential = credential.createScoped(PubsubScopes.all());
        }
        // Please use custom HttpRequestInitializer for automatic
        // retry upon failures. We provide a simple reference
        // implementation in the "Retry Handling" section.
        HttpRequestInitializer initializer = new RetryHttpInitializerWrapper(credential);
        return new Pubsub.Builder(httpTransport, jsonFactory, initializer).build();
    }
}
