/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.osm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The query builder for Overpass Api which was created for finding objects on OSM
 * Was implemented as so-called liquid query ( Example query.setAround(...).setOut(...))
 * Includes method send that sends itself to overpass server. Receives xml string
 * 
 * To Do: 
 *  1) Add additional tags to filter the query result
 *  2) Add tags for ways
 *
 * @author shaylin3
 */
public class OverpassQuery {
    
    // At the same time sets the order for criterias in TreeMap
    // TreeMap will sort it's values with the same the order of values of enum Criteria
    public enum Criteria{
        NODE_AROUND,
        OUT
    }
    
    TreeMap<Criteria, CriteriaValue> criteria;
    String QUERY_PREFIX = "http://overpass-api.de/api/interpreter?data=";
    
    public OverpassQuery() {
        criteria = new TreeMap<Criteria, CriteriaValue>();
    }
    
    public OverpassQuery setNodeAround(int radius, double latitude, double longitude){
        Around around = new Around(radius, latitude, longitude);
        criteria.put(Criteria.NODE_AROUND, around);
        
        return this;
    }
    
    public OverpassQuery setOut(OutEnum out){
        Out outCriteria = new Out(out);
        criteria.put(Criteria.OUT, outCriteria);
        
        return this;
    }
   
    // Returns the whole xml string of objects sent from the overpass server
    public String send() throws MalformedURLException, IOException{
        try(InputStream is = new URL(toString()).openStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream()){
            int length;
            byte[] buffer = new byte[1024];
            while((length = is.read(buffer)) != -1){
                result.write(buffer, 0, length);
            }
            
            return result.toString("UTF-8");
        }
    }
    
    public String toString(){
        StringBuilder query = new StringBuilder();
        query.append(QUERY_PREFIX);
        
        for(Map.Entry<Criteria, CriteriaValue> entry : criteria.entrySet()){
            query.append(entry.getValue().toString());
        }
        
        return query.toString();
    }
}
