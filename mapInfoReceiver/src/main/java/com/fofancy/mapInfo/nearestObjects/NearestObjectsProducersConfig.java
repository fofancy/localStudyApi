package com.fofancy.mapInfo.nearestObjects;

import com.fofancy.apiconfiguration.providers.ProvidersContext;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 * Created by shaylin3 on 09.05.2017.
 */
public class NearestObjectsProducersConfig {

    @Inject
    ProvidersContext context;

    @Produces
    @NearestObjectsReceiverQualifier
    public NearestObjectsReceiverFactory createNearestObjectsReceiverFactory(
            InjectionPoint injectionPoint
    ) {
        NearestObjectsReceiverFactory factory = NearestObjectsReceiverFactory.newInstance();

        factory.setContext(context);

        return factory;
    }
}
