/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.wiki;

import com.fofancy.mapInfo.nearestObjects.Coords;
import com.fofancy.mapInfo.nearestObjects.IMapObject;
import com.fofancy.mapInfo.nearestObjects.INearestObjectsReceiver;
import com.fofancy.mapInfo.nearestObjects.INearestObjectsReceiverProperties;

import java.util.List;


public class WikiNearestObjectsReceiverImpl implements INearestObjectsReceiver {
    Coords coords;
    INearestObjectsReceiverProperties properties;
    
    public WikiNearestObjectsReceiverImpl() {
    }

    @Override
    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    @Override
    public Coords getCoords() {
        return coords;
    }
    
    @Override
    public List<IMapObject> getNearestMapObjects() {
        int radius = Integer.valueOf(String.valueOf(properties.getProperty("radius")));

        WikiNearestObjectsQueryFactory factory = WikiNearestObjectsQueryFactory.newInstance();
        factory.setCoords(coords);
        factory.setRadius(radius);
        WikiNearestObjectsQuery query = factory.createQuery();
        
        return query.execute();
    }

    @Override
    public void setMapDataReceiverProperties(INearestObjectsReceiverProperties properties) {
        this.properties = properties;
    }

    @Override
    public INearestObjectsReceiverProperties getDataReceiverProperties() {
        return properties;
    }
}
