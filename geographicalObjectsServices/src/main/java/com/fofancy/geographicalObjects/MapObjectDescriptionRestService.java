package com.fofancy.geographicalObjects;

import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;
import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;
import com.fofancy.geographicalObjects.processor.GeographicalObjectsInfoEJB;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by shaylin3 on 23.04.2017.
 */
@Path("/map-object-info")
@Produces({ MediaType.APPLICATION_JSON })
public class MapObjectDescriptionRestService {
    @EJB
    GeographicalObjectsInfoEJB geographicalObjectsInfo;


    public GeographicalObjectsInfoEJB getGeographicalObjectsInfo() {
        return geographicalObjectsInfo;
    }

    public void setGeographicalObjectsInfo(GeographicalObjectsInfoEJB geographicalObjectsInfo) {
        this.geographicalObjectsInfo = geographicalObjectsInfo;
    }

    @GET
    public Response getGeographicalObjectInfo(@QueryParam("name") String name) {
        GeographicalObjectsInfoParameters params = new GeographicalObjectsInfoParameters();
        params.setProperty("name", name);

        IGeographicalObjectInfo providerResponse = geographicalObjectsInfo
                .getGeographicalObjectInfo(
                        params,
                        "Wiki"
                );

        return  Response
                .ok(providerResponse)
                .build();
    }
}
