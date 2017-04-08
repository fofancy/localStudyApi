/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.osm;

import com.fofancy.geoDataProcessor.Coords;
import com.fofancy.mapInfoReceiver.IMapDataReceiverApi;
import com.fofancy.mapInfoReceiver.MapDataReceiverPropertiesEnum;
import com.fofancy.mapInfoReceiver.IMapObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import com.fofancy.mapInfoReceiver.IMapDataReceiverProperties;

/**
 *
 * @author shaylin3
 */
@Named("osmDataReceiverImpl")
public class OsmDataReceiverImpl implements IMapDataReceiverApi {
    private Coords coords;
    private Set<MapObjectsEnum> mapObjectsTypes;
    IMapDataReceiverProperties properties;

    /*
    * Sets default values for this implementation
    * Todo: impement setting using XML
    */
    @PostConstruct
    public void init() {
        
    }
    
    public OsmDataReceiverImpl() {
    }

    public OsmDataReceiverImpl(Coords coords) {
        this.coords = coords;
    }

    public OsmDataReceiverImpl(Set<MapObjectsEnum> mapObjectsTypes) {
        this.mapObjectsTypes = mapObjectsTypes;
    }

    public OsmDataReceiverImpl(Coords coords, Set<MapObjectsEnum> mapObjectsTypes) {
        this.coords = coords;
        this.mapObjectsTypes = mapObjectsTypes;
    }

    @Override
    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    @Override
    public Coords getCoords() {
        return coords;
    }
 

    /*
    * To do: 
    * 1) End this method
    * 2) Exception
    */
    @Override
    public ArrayList<IMapObject> getMapObjects() {
        try {
            OverpassQuery query = new OverpassQuery();
            query.setNodeAround(1000, coords.getLatitude(), coords.getLongitude());
            query.setOut(OutEnum.BODY);
            String answer = query.send();
        } catch (IOException ex) {
            Logger.getLogger(OsmDataReceiverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new UnsupportedOperationException("Didn't implemented");
    }

    @Override
    public void setMapDataReceiverProperties(IMapDataReceiverProperties properties) {
        this.properties = properties;
    }

    @Override
    public IMapDataReceiverProperties getDataReceiverProperties() {
        return this.properties;
    }
    
}
