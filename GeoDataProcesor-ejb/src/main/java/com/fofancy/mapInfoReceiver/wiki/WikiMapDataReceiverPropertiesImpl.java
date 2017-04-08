/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.wiki;

import com.fofancy.mapInfoReceiver.IMapDataReceiverProperties;
import com.fofancy.mapInfoReceiver.MapDataReceiverPropertiesEnum;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

// The class for setting properties for wiki map data receiver
public class WikiMapDataReceiverPropertiesImpl implements IMapDataReceiverProperties {
    Map<String, Object> properties;
    
    // Contains all possible properties for Wiki map data receiver
    Set<String> allPossibleProperties;

    public WikiMapDataReceiverPropertiesImpl() {
        properties = new HashMap<>();
        
        initAllPossibleProperties();
    }
    
    @Override
    public Object getProperty(String propertyName) {
        if(properties.containsKey(propertyName)){
            return properties.get(propertyName);
        }
        else 
            throw new IllegalMapDataPropertyName();
    }

    @Override
    public void setProperty(String propertyName, Object property) {
        if(this.allPossibleProperties.contains(propertyName)){
            this.properties.put(propertyName, property);
        }
        else
            throw new IllegalMapDataPropertyName();
    }

    @Override
    public boolean containsProperty(String propertyName) {
        if(this.allPossibleProperties.contains(propertyName)){
            return this.properties.containsKey(propertyName);
        }
        else
            throw new IllegalMapDataPropertyName();
    }
    
    
    // Reads properties form property file
    public void readProperties(String fileName) {
        try {
            Properties properties = new Properties();
            
            FileInputStream propertiesStream = new FileInputStream(fileName);
            properties.load(propertiesStream);
            
            Set<String> propertyNames = properties.stringPropertyNames();
            
            for(String propertyName : propertyNames){
                setProperty(propertyName, properties.getProperty(propertyName));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WikiMapDataReceiverPropertiesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WikiMapDataReceiverPropertiesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WikiMapDataReceiverException ex) {
            Logger.getLogger(WikiMapDataReceiverPropertiesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Initializes all possible properties for wiki data receiver
    private void initAllPossibleProperties(){
        this.allPossibleProperties = new HashSet<>();
        
        WikiMapDataPropertiesEnum[] wikiProperties = WikiMapDataPropertiesEnum.values();
        for(int i = 0; i < wikiProperties.length; i++)
            allPossibleProperties.add(wikiProperties[i].name().toLowerCase());
        
        MapDataReceiverPropertiesEnum[] generalProperties = MapDataReceiverPropertiesEnum.values();
        for(int i = 0; i < generalProperties.length; i++)
            allPossibleProperties.add(generalProperties[i].name().toLowerCase());
    }
}
