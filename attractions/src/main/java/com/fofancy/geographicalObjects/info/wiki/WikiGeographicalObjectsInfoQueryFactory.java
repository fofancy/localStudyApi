package com.fofancy.geographicalObjects.info.wiki;

import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;

/**
 * Created by shaylin3 on 17.04.2017.
 */
public class WikiGeographicalObjectsInfoQueryFactory {
    GeographicalObjectsInfoParameters parameters;

    protected WikiGeographicalObjectsInfoQueryFactory() {

    }

    public static WikiGeographicalObjectsInfoQueryFactory newInstance() {
        return new WikiGeographicalObjectsInfoQueryFactory();
    }

    public GeographicalObjectsInfoParameters getParameters() {
        return parameters;
    }

    public void setParameters(GeographicalObjectsInfoParameters parameters) {
        this.parameters = parameters;
    }

    public WikiGeographicalObjectsInfoQuery createGeographicalObjectsInfoQuery() {
        WikiGeographicalObjectsInfoQuery query = new WikiGeographicalObjectsInfoQuery();

        query.setProperties(parameters);

        return query;
    }
}
