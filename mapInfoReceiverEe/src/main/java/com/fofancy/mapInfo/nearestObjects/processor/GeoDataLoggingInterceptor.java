/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.processor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shaylin3
 */
public class GeoDataLoggingInterceptor {
    @Inject
    private Logger logger;
    
    @AroundInvoke
    public Object logGeoData(InvocationContext ic) throws Exception{
//        int FIRST_PARAMETR = 0;
//        Coords aCoords = (Coords) ic.getParameters()[FIRST_PARAMETR];
//
//        String aLogMessage = String.format(
//                "Longitude : %1f;\n Latitude: %2f;",
//                aCoords.getLongitude(),
//                aCoords.getLatitude()
//        );
//
        logger.log(Level.INFO, "nearest-objects");
        
        return ic.proceed();
    }
}
