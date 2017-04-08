/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.wiki;

import com.fofancy.geoDataProcessor.Coords;
import com.fofancy.mapInfoReceiver.IMapObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class created to wrap logic that is responsible for getting map objects using wiki api
 * 
 * @author shaylin3
 */
class WikiNearestObjectsApiWrapper { 

    public WikiNearestObjectsApiWrapper() {
    }
    
    /* The main method that is used to get nearest objects to appropriate coordinates
    *  Objects consist of their geological data and description from wikipedia. (see IMapObject.java) 
    */
    public static List<IMapObject> getNearestObjects(Coords coords, int radius) {
        String nearestObjectsJson = queryWiki(coords, radius);
        
        return WikiNearestObjectsResponseProcessor.process(nearestObjectsJson);
    }
    
    /* Returns json query to wiki response string
     */
    public static String queryWiki(Coords coords, int radius){
        String queryString = WikiMapDataQueryHelper
                .getNearestObjectQueryString(coords.getLongitude(), coords.getLatitude(), radius);
        String queryResult = new String();
        
        try(InputStream is = new URL(queryString).openStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream()){
            int length;
            byte[] buffer = new byte[1024];
            while((length = is.read(buffer)) != -1){
                result.write(buffer, 0, length);
            }
            
            queryResult = result.toString("UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(WikiNearestObjectsApiWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  cutWikiResult(queryResult);
    }
    
    private static String cutWikiResult(String resultJson){
        String endResult = new String();
        String regex = "\\{\"geosearch\":\\[.*\\]\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(resultJson);
        
        if(matcher.find()){
            endResult = matcher.group();
        }
        
        return endResult;
    }
}
