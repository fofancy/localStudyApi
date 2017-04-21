/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.wiki;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fofancy.mapInfo.nearestObjects.IMapObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shaylin3
 *   Method that receives names of surrouding map objects. Queries wikipedia to get info
 *    and serves as the last set of map objects constructing
 *  
 */
public class WikiNearestObjectsResponseProcessor {

    public WikiNearestObjectsResponseProcessor() {
    }
    
    public static List<IMapObject> process(String nearestObjectsJson) {
        List<IMapObject> mapObjects = new ArrayList<IMapObject>();
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        
        try {
            root = mapper.readTree(nearestObjectsJson);
        } catch (IOException ex) {
            Logger.getLogger(WikiNearestObjectsResponseProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JsonNode geosearchNode = root.path("geosearch");
        for(JsonNode mapObjectJson : geosearchNode){
             WikiMapObject mapObject = new WikiMapObject();
             mapObject.setId(mapObjectJson.path("pageid").asText());
             mapObject.setTitle(mapObjectJson.path("title").asText());
             mapObject.setLongitude(mapObjectJson.path("lon").asDouble());
             mapObject.setLatitude(mapObjectJson.path("lat").asDouble());
             mapObject.setDistance(mapObjectJson.path("dist").asDouble());
             
             mapObjects.add(mapObject);
        }
        
        return mapObjects;
    }
}
