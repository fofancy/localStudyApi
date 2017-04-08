/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.wiki;

import com.fofancy.geoDataProcessor.Coords;
import com.fofancy.mapInfoReceiver.IMapDataReceiverApi;
import com.fofancy.mapInfoReceiver.IMapObject;
import java.util.List;
import com.fofancy.mapInfoReceiver.IMapDataReceiverProperties;


public class WikiMapDataReceiverApiImpl implements IMapDataReceiverApi {
    Coords coords;
    IMapDataReceiverProperties properties;
    
    public WikiMapDataReceiverApiImpl() {
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
    public List<IMapObject> getMapObjects() {
        int radius = (int) properties.getProperty("radius");
        
        WikiNearestObjectsQueryFactory factory = WikiNearestObjectsQueryFactory.newInstance();
        factory.setCoords(coords);
        factory.setRadius(radius);
        WikiNearestObjectsQuery query = factory.createQuery();
        
        return query.execute();
    }

    @Override
    public void setMapDataReceiverProperties(IMapDataReceiverProperties properties) {
        this.properties = properties;
    }

    @Override
    public IMapDataReceiverProperties getDataReceiverProperties() {
        return properties;
    }
}
