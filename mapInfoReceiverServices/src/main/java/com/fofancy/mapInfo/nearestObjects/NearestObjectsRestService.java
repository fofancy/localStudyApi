package com.fofancy.mapInfo.nearestObjects;

import com.fofancy.mapInfo.nearestObjects.processor.NearestObjectsReceiverEJB;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaylin3 on 23.04.2017.
 */
@Path("/nearest-objects")
@Produces({ MediaType.APPLICATION_JSON })
public class NearestObjectsRestService {
    private static final String DEFAULT_RADIUS = "3000";

    @EJB
    NearestObjectsReceiverEJB mNearestObjectsReceiverEJB;

    public NearestObjectsReceiverEJB getMProcessGeoDataEJB() {
        return mNearestObjectsReceiverEJB;
    }

    public void setMProcessGeoDataEJB(NearestObjectsReceiverEJB mNearestObjectsReceiverEJB) {
        this.mNearestObjectsReceiverEJB = mNearestObjectsReceiverEJB;
    }

    @GET
    public Response getNearestObjects(
            @QueryParam("latitude") String latitude,
            @QueryParam("longitude") String longitude) {
        Coords aCoords = new Coords();

        aCoords.setLatitude(Double.valueOf(latitude));
        aCoords.setLongitude(Double.valueOf(longitude));

        NearestObjectsReceiverParametersImpl params = new NearestObjectsReceiverParametersImpl();
        params.setProperty("latitude", latitude);
        params.setProperty("longitude", longitude);
        params.setProperty("radius", DEFAULT_RADIUS);

        String provider = "Wiki";

        ArrayList<MapObjectImpl> surroundingMapObjects = mNearestObjectsReceiverEJB.getNearestObjects(params, provider);

        return Response
                .ok(surroundingMapObjects)
                .build();
    }

}
