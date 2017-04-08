/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.wiki;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.MathContext;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** It was created just to clean up WikiNearestObjectApiWrapper from unpleasant logic
 *  connected with resource bundles, string, builders and so on...
 *  
 * @author shaylin3
 */
class WikiMapDataQueryHelper {
    /*
     * The core of the query. The home page. It was considered to instantiation an apropriate query root
     * based on a locale
     */
    private static String apiRoot;
    private static Properties apiQueriesResource = new Properties();
    
    private static final String API_ROOT_RESOURCE_FILE_NAME = "MapInfoReceiving/WikiReceiving/wikiApiRoot.properties";
    private static final String API_QUERIES_RESOURCE_FILE_NAME = "MapInfoReceiving/WikiReceiving/wikiApiQueries.properties";
    
    private static final String API_ROOT_PROPERTY_NAME = "wiki.api.root";
    private static final String API_QUERIES_NEAREST_OBJECT_PROPERTY_NAME = "wiki.api.getNearestObject";

    static {
        try {
            InputStream rootPropFile = WikiMapDataQueryHelper.class.getClassLoader().getResourceAsStream(API_ROOT_RESOURCE_FILE_NAME);
            InputStream apiQueriesFile = WikiMapDataQueryHelper.class.getClassLoader().getResourceAsStream(API_QUERIES_RESOURCE_FILE_NAME);
            
            Properties apiRootProp = new Properties();
            apiRootProp.load(rootPropFile);
            apiRoot = apiRootProp.getProperty(API_ROOT_PROPERTY_NAME);

            apiQueriesResource.load(apiQueriesFile);
        } catch (IOException ex) {
            Logger.getLogger(WikiMapDataQueryHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getApiRoot() {
        return apiRoot;
    }

    public static void setApiRoot(String apiRoot) {
        WikiMapDataQueryHelper.apiRoot = apiRoot;
    }

    public static Properties getApiQueriesResource() {
        return apiQueriesResource;
    }

    public static void setApiQueriesResource(Properties apiQueriesResource) {
        WikiMapDataQueryHelper.apiQueriesResource = apiQueriesResource;
    }
    
    
    public static String getNearestObjectQueryString(double latitude, double longitude, int radius) {
        StringBuilder endpointQueryString = new StringBuilder();
        String query = apiQueriesResource.getProperty(API_QUERIES_NEAREST_OBJECT_PROPERTY_NAME);
        
        endpointQueryString.append(apiRoot);
       
        query = query.replace(":lat", String.valueOf(latitude));
        query = query.replace(":long", String.valueOf(longitude));
        query = query.replace(":radius", String.valueOf(radius));
        
        endpointQueryString.append(query);
        
        return endpointQueryString.toString();
    }
}
