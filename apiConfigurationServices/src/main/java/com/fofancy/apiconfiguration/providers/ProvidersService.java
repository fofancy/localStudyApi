package com.fofancy.apiconfiguration.providers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by shaylin3 on 20.05.2017.
 */
@Path("providers")
@Produces({"text/json"})
public class ProvidersService {
    @Inject
    private ProvidersContext context;

    public ProvidersService() {
    }

    public ProvidersContext getContext() {
        return context;
    }

    public void setContext(ProvidersContext context) {
        this.context = context;
    }

    @GET
    public Response getProviderServices() {

        return Response
                .ok(context.getProviders())
                .build();
    }

    @PUT
    @Path("update")
    public Response updateProviders() {
        // TODO: update providers from xml

        return Response
                .ok()
                .build();
    }
}
