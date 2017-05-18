/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.processor;

import com.fofancy.mapInfo.nearestObjects.INearestObjectsReceiver;
import com.fofancy.mapInfo.nearestObjects.MapObjectImpl;
import com.fofancy.mapInfo.nearestObjects.NearestObjectsReceiverParametersImpl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.logging.Logger;

//import com.fofancy.mapInfo.nearestObjects.wiki.WikiNearestObjectsReceiverPropertiesImpl;

//import com.fofancy.overpassApi.OverpassQuery;


/**
 *
 * @author shaylin3
 */
@Stateless
public class NearestObjectsReceiverEJB {
    @Inject
    private Logger mLogger;

    @Inject
    @NearestObjectsReceiverQualifier
    private NearestObjectsReceiverFactory factory;

    /*
     * TODO: Add control of mapDataReceiver provider
     * TODO: Implement all factories, all uni builders
     */
    @PostConstruct
    public void init() {
//        NearestObjectsReceiverFactory factory = NearestObjectsReceiverFactory.newInstance();
//        factory.setProvider("com.fofancy.mapInfo.nearestObjects.wiki.WikiNearestObjectsReceiverImpl");
//        mapDataReceiverApi = factory.createNearestObjectsReceiver();
//
//        WikiNearestObjectsReceiverPropertiesImpl properties = new WikiNearestObjectsReceiverPropertiesImpl();
//        properties.readProperties("MapInfoReceiving/nearestObjectProperties.properties");
//
//        mapDataReceiverApi.setMapDataReceiverProperties(properties);
    }
    
    public NearestObjectsReceiverEJB(){ }
    
    @Interceptors(GeoDataLoggingInterceptor.class)
    public ArrayList<MapObjectImpl> getNearestObjects(NearestObjectsReceiverParametersImpl parameters, String provider){
        factory.setProvider(provider);

        INearestObjectsReceiver receiver = factory.createNearestObjectsReceiver();

        // TODO: DEBUG - remove
        System.out.println(parameters.getProperty("latitude"));
        return receiver.receiveNearestMapObjects(parameters);
    }
}
