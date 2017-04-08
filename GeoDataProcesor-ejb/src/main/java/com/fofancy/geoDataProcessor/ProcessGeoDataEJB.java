/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.geoDataProcessor;

import com.fofancy.geoDataProcessor.Coords;
import com.fofancy.geoDataProcessor.GeoDataLoggingInterceptor;
import com.fofancy.mapInfoReceiver.osm.OutEnum;
import com.fofancy.mapInfoReceiver.osm.OverpassQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

//import com.fofancy.overpassApi.OverpassQuery;
import java.io.IOException;

/**
 *
 * @author shaylin3
 */
@Stateful
public class ProcessGeoDataEJB {

    @Inject
    private Logger mLogger;
    
    public ProcessGeoDataEJB(){ }
    
    @Interceptors(GeoDataLoggingInterceptor.class)
    public void process(Coords coords){
        mLogger.log(Level.INFO, "In ejb");
        try{
              OverpassQuery query = new OverpassQuery();
              query.setNodeAround(1000, coords.getLatitude(), coords.getLongitude());
              query.setOut(OutEnum.BODY);
              String answer = query.send();
              System.out.println(answer);
        } catch (Exception ex) {
            mLogger.log(Level.SEVERE, null, ex);
        }
    }
}
