package com.fofancy.geographicalObjects.processor;

import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoFactory;
import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;
import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;
import com.fofancy.geographicalObjects.info.IGeographicalObjectsInfoReceiver;

import javax.ejb.Stateless;

/**
 * Created by shaylin3 on 22.04.2017.
 */
@Stateless
public class GeographicalObjectsInfoEJB {
    public GeographicalObjectsInfoEJB() {
    }

    public IGeographicalObjectInfo getGeographicalObjectInfo(GeographicalObjectsInfoParameters params, String provider) {
        GeographicalObjectsInfoFactory factory = GeographicalObjectsInfoFactory.newInstance();

        factory.setProvider(provider);

        IGeographicalObjectsInfoReceiver receiver = factory.createSightDescriptionReceiver();

        return receiver.receiveDescription(params);
    }
}
