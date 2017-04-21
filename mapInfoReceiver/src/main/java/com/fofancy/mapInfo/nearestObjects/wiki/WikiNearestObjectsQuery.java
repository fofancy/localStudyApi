/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.wiki;

import com.fofancy.mapInfo.nearestObjects.Coords;
import com.fofancy.mapInfo.nearestObjects.IMapObject;

import java.util.List;

/**
 *
 * @author shaylin3
 */
public class WikiNearestObjectsQuery {
    WikiNearestObjectsApiWrapper apiWrapper;
    Coords coords;
    int radius;
    
    public WikiNearestObjectsQuery() {
        
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
   
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    /*
    * ToDO
    */
    public List<IMapObject> execute(){
        return WikiNearestObjectsApiWrapper.getNearestObjects(coords, radius);
    }

}
