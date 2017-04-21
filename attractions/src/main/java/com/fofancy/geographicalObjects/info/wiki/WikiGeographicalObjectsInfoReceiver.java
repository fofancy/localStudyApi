package com.fofancy.geographicalObjects.info.wiki;

import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;
import com.fofancy.geographicalObjects.info.IGeographicalObjectsInfoReceiver;
import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;

/**
 * Created by shaylin3 on 15.04.2017.
 */
public class WikiGeographicalObjectsInfoReceiver implements IGeographicalObjectsInfoReceiver {

    @Override
    public IGeographicalObjectInfo receiveDescription(GeographicalObjectsInfoParameters properties) {
        WikiGeographicalObjectsInfoQueryFactory factory = WikiGeographicalObjectsInfoQueryFactory.newInstance();
        factory.setParameters(properties);
        WikiGeographicalObjectsInfoQuery query = factory.createGeographicalObjectsInfoQuery();

        IGeographicalObjectInfo response = query.execute();

        return response;
    }
}
