/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.wiki;


import com.fofancy.mapInfo.nearestObjects.Coords;
import com.fofancy.mapInfo.nearestObjects.IMapObject;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author shaylin3
 */
public class WikiMapDataQueryHelperTest {
    
    public WikiMapDataQueryHelperTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void queryStringBuildingTest() {
         try{
            String queryString = WikiMapDataQueryHelper.getNearestObjectQueryString(46.487121,30.731056, 1000);

             assertEquals(queryString,
                     "https://en.wikipedia.org/w/api.php?action=query&list=geosearch&gscoord=46.487121%7C30.731056&gsradius=1000&gslimit=1000&format=json");
         }
         catch(Exception ex){
             System.out.println(ex.getMessage());
         }
     }
     
     @Test
     public void wikiQueryResponseTest() {
        Coords coords = new Coords(46.487121, 30.731056);
         
        String wikiQueryResponse = com.fofancy.mapInfo.nearestObjects.wiki.WikiNearestObjectsApiWrapper.queryWiki(coords, 1000);
        
         assertNotEquals(wikiQueryResponse, "");
     }
     
//     @Test
//     public void jacksonParsingTest() throws IOException {
//        Coords coords = new Coords(46.487121, 30.731056);
//         
//        String wikiQueryResponse = com.fofancy.mapInfo.nearestObjects.wiki.WikiNearestObjectsApiWrapper.queryWiki(coords, 1000);
//        
//        List<IMapObject> mapObjects = WikiNearestObjectsResponseProcessor.process(wikiQueryResponse);
//        
//         assertTrue(mapObjects.size() > 0);
//     }
}
