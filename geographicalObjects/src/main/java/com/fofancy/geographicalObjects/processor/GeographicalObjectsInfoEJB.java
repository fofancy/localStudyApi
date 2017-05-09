package com.fofancy.geographicalObjects.processor;

import com.fofancy.geographicalObjects.info.*;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by shaylin3 on 22.04.2017.
 */
// TODO: get rid of this EJB from this project
@Stateless
public class GeographicalObjectsInfoEJB {
    @Inject
    @GeographicalObjectsInfoReceiverQualifier
    GeographicalObjectsInfoReceiverFactory factory;

    public GeographicalObjectsInfoEJB() {
    }

    public IGeographicalObjectInfo getGeographicalObjectInfo(GeographicalObjectsInfoParameters params, String provider) {
        factory.setProvider(provider);

        IGeographicalObjectsInfoReceiver receiver = factory.createGeographicalObjectsInfoReceiver();

        return receiver.receiveDescription(params);
    }
}
