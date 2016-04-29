/**
 * 
 */
package com.alan.training.api;

import javax.ws.rs.HttpMethod;

import com.alan.training.core.GAEResource;
import com.alan.training.model.Token;
import com.alan.training.services.ServiceDAO;
import com.alan.training.services.WizzardService;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

/**
 * @author alangabriel
 * 
 */
@Api(name = "testapi", version = "v1", defaultVersion = AnnotationBoolean.TRUE)
public class ServiciosApi {

    public ServiciosApi() {
        super();
        WizzardService.getInstance().wizzard(this);
    }

    @GAEResource(service = "ServiceDAOImpl")
    private ServiceDAO servicioGAE;

    @ApiMethod(name = "hola", httpMethod = HttpMethod.GET, path = "hola")
    public Token api() {
        Token t = new Token();
        t.setId("alan");
        servicioGAE.hello();
        return t;
    }
}
