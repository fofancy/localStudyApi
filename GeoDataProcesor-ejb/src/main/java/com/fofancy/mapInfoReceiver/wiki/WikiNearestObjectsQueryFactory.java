/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.wiki;

import com.fofancy.geoDataProcessor.Coords;
import javax.inject.Named;

/**
 * Factory for wiki query building
 * 
 * @author shaylin3
 */
@Named
public class WikiNearestObjectsQueryFactory {
    WikiNearestObjectsApiWrapper apiWrapper;
    Coords coords;
    int radius;
    
    private WikiNearestObjectsQueryFactory() {
        
    }
    
    /*
     * Returns new instance of factory in which each time would be set new parameteres
     * Each Parameter need to be specified
    */
    public static WikiNearestObjectsQueryFactory newInstance(){
        return new WikiNearestObjectsQueryFactory();
    }
    
    public void setCoords(Coords coords) {
        this.coords = coords;
    }
    
    public Coords getCoords() {
        return this.coords;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    
    /*
    * Creates query by before setted parameters
    * All parameters need to be specified
    * TODO : make check for null pointers of parameters
    */
    public WikiNearestObjectsQuery createQuery(){
        WikiNearestObjectsQuery query = new WikiNearestObjectsQuery();
        
        query.setCoords(coords);
        query.setRadius(radius);
        
        return query;
    }
}
