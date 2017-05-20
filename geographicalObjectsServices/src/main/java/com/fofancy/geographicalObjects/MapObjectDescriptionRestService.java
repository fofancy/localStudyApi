package com.fofancy.geographicalObjects;

import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;
import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;
import com.fofancy.geographicalObjects.processor.GeographicalObjectsInfoEJB;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by shaylin3 on 23.04.2017.
 */
@Path("/map-object-info")
@Produces({ MediaType.APPLICATION_JSON })
public class MapObjectDescriptionRestService {
    private static final String DEFAULT_PROVIDER = "Wiki";
    @EJB
    private GeographicalObjectsInfoEJB geographicalObjectsInfo;


    public GeographicalObjectsInfoEJB getGeographicalObjectsInfo() {
        return geographicalObjectsInfo;
    }

    public void setGeographicalObjectsInfo(GeographicalObjectsInfoEJB geographicalObjectsInfo) {
        this.geographicalObjectsInfo = geographicalObjectsInfo;
    }

    @GET
    public Response getGeographicalObjectInfo(
            @QueryParam("name") String name,
            @HeaderParam("Provider-Name") String providerName
    ) {
        String provider = DEFAULT_PROVIDER;

        GeographicalObjectsInfoParameters params = new GeographicalObjectsInfoParameters();
        params.setProperty("name", name);

        if(providerName != null)
            provider = providerName;

        IGeographicalObjectInfo providerResponse = geographicalObjectsInfo
                .getGeographicalObjectInfo(
                        params,
                        provider
                );

        return  Response
                .ok(providerResponse)
                .build();
    }


}
