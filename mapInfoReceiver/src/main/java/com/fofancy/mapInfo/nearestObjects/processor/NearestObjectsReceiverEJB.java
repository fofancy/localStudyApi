/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.processor;

import com.fofancy.mapInfo.nearestObjects.Coords;
import com.fofancy.mapInfo.nearestObjects.INearestObjectsReceiver;
import com.fofancy.mapInfo.nearestObjects.IMapObject;
import com.fofancy.mapInfo.nearestObjects.NearestObjectsReceiverFactory;
import com.fofancy.mapInfo.nearestObjects.wiki.WikiNearestObjectsReceiverPropertiesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

//import com.fofancy.overpassApi.OverpassQuery;


/**
 *
 * @author shaylin3
 */
@Stateless
public class NearestObjectsReceiverEJB {

    INearestObjectsReceiver mapDataReceiverApi;

    @Inject
    private Logger mLogger;

    /*
     * TODO: Add control of mapDataReceiver provider
     * TODO: Implement all factories, all uni builders
     */
    @PostConstruct
    public void init() {
        NearestObjectsReceiverFactory factory = NearestObjectsReceiverFactory.newInstance();
        factory.setProvider("com.fofancy.mapInfo.nearestObjects.wiki.WikiNearestObjectsReceiverImpl");
        mapDataReceiverApi = factory.createNearestObjectsReceiver();

        WikiNearestObjectsReceiverPropertiesImpl properties = new WikiNearestObjectsReceiverPropertiesImpl();
        properties.readProperties("MapInfoReceiving/nearestObjectProperties.properties");

        mapDataReceiverApi.setMapDataReceiverProperties(properties);
    }
    
    public NearestObjectsReceiverEJB(){ }
    
    @Interceptors(GeoDataLoggingInterceptor.class)
    public List<IMapObject> process(Coords coords){
        List<IMapObject> mapObjects = new ArrayList<>();

        try{
              mapDataReceiverApi.setCoords(coords);

              mapObjects = mapDataReceiverApi.getNearestMapObjects();
        } catch (Exception ex) {
            mLogger.log(Level.SEVERE, null, ex);
        }

        return mapObjects;
    }
}
