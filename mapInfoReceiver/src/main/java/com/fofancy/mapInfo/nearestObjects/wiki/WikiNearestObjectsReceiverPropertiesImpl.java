/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.wiki;

import com.fofancy.mapInfo.nearestObjects.INearestObjectsReceiverProperties;
import com.fofancy.mapInfo.nearestObjects.NearestObjectsReceiverPropertiesEnum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// The class for setting properties for wiki map data receiver
public class WikiNearestObjectsReceiverPropertiesImpl implements INearestObjectsReceiverProperties {
    Map<String, Object> properties;
    
    // Contains all possible properties for Wiki map data receiver
    Set<String> allPossibleProperties;

    public WikiNearestObjectsReceiverPropertiesImpl() {
        properties = new HashMap<>();
        
        initAllPossibleProperties();
    }
    
    @Override
    public Object getProperty(String propertyName) {
        if(properties.containsKey(propertyName)){
            return properties.get(propertyName);
        }
        else 
            throw new IllegalNearestObjectsPropertyName();
    }

    @Override
    public void setProperty(String propertyName, Object property) {
        if(this.allPossibleProperties.contains(propertyName)){
            this.properties.put(propertyName, property);
        }
        else
            throw new IllegalNearestObjectsPropertyName();
    }

    @Override
    public boolean containsProperty(String propertyName) {
        if(this.allPossibleProperties.contains(propertyName)){
            return this.properties.containsKey(propertyName);
        }
        else
            throw new IllegalNearestObjectsPropertyName();
    }
    
    
    // Reads properties form property file
    public void readProperties(String fileName) {
        try {
            Properties properties = new Properties();
            
            InputStream propertiesStream = WikiNearestObjectsReceiverPropertiesImpl.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(propertiesStream);
            
            Set<String> propertyNames = properties.stringPropertyNames();
            
            for(String propertyName : propertyNames){
                setProperty(propertyName, properties.getProperty(propertyName));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WikiNearestObjectsReceiverPropertiesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WikiNearestObjectsReceiverPropertiesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WikiNearestObjectsReceiverException ex) {
            Logger.getLogger(WikiNearestObjectsReceiverPropertiesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Initializes all possible properties for wiki data receiver
    private void initAllPossibleProperties(){
        this.allPossibleProperties = new HashSet<>();
        
        WikiMapDataPropertiesEnum[] wikiProperties = WikiMapDataPropertiesEnum.values();
        for(int i = 0; i < wikiProperties.length; i++)
            allPossibleProperties.add(wikiProperties[i].name().toLowerCase());
        
        NearestObjectsReceiverPropertiesEnum[] generalProperties = NearestObjectsReceiverPropertiesEnum.values();
        for(int i = 0; i < generalProperties.length; i++)
            allPossibleProperties.add(generalProperties[i].name().toLowerCase());
    }
}
