package com.fofancy.geographicalObjects.processor;

import com.fofancy.apiconfiguration.providers.ProvidersContext;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 * Created by shaylin3 on 09.05.2017.
 */
public class GeographicalObjectsProducersConfig {
    @Inject
    ProvidersContext context;

    @Produces
    @GeographicalObjectsInfoReceiverQualifier
    public GeographicalObjectsInfoReceiverFactory createGeographicalObjectsInfoReceiverFactory(
            InjectionPoint injectionPoint
    ){
        GeographicalObjectsInfoReceiverFactory factory = GeographicalObjectsInfoReceiverFactory.newInstance();

        factory.setContext(context);
        return factory;
    }
}
