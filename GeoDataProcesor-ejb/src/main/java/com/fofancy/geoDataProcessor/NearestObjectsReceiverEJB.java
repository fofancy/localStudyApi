/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.geoDataProcessor;

import com.fofancy.mapInfoReceiver.IMapDataReceiverApi;
import com.fofancy.mapInfoReceiver.IMapDataReceiverProperties;
import com.fofancy.mapInfoReceiver.IMapObject;
import com.fofancy.mapInfoReceiver.MapDataReceiverApiFactory;
import com.fofancy.mapInfoReceiver.wiki.WikiMapDataReceiverPropertiesImpl;

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

    IMapDataReceiverApi mapDataReceiverApi;

    @Inject
    private Logger mLogger;

    /*
     * TODO: Add control of mapDataReceiver provider
     * TODO: Implement all factories, all uni builders
     */
    @PostConstruct
    public void init() {
        MapDataReceiverApiFactory factory = MapDataReceiverApiFactory.newInstance();
        factory.setMapDataReceiverProvider("com.fofancy.mapInfoReceiver.wiki.WikiMapDataReceiverApiImpl");
        mapDataReceiverApi = factory.createMapDataReceiverApi();

        WikiMapDataReceiverPropertiesImpl properties = new WikiMapDataReceiverPropertiesImpl();
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
