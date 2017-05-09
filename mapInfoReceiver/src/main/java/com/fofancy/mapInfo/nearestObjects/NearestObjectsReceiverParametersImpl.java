package com.fofancy.mapInfo.nearestObjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: rename poperty properties to params
 *
 * Created by shaylin3 on 08.05.2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class NearestObjectsReceiverParametersImpl implements INearestObjectsReceiverParameters, Serializable {
    HashMap<String, Object> properties;

    // Contains all possible properties for Wiki map data receiver
    HashSet<String> allPossibleProperties;

    public NearestObjectsReceiverParametersImpl() {
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

            InputStream propertiesStream = NearestObjectsReceiverParametersImpl.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(propertiesStream);

            Set<String> propertyNames = properties.stringPropertyNames();

            for(String propertyName : propertyNames){
                setProperty(propertyName, properties.getProperty(propertyName));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NearestObjectsReceiverParametersImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NearestObjectsReceiverParametersImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NearestObjectsReceiverException ex) {
            Logger.getLogger(NearestObjectsReceiverParametersImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Initializes all possible properties for wiki data receiver
    private void initAllPossibleProperties(){
        this.allPossibleProperties = new HashSet<>();

        NearestObjectsReceiverParametersEnum[] generalProperties = NearestObjectsReceiverParametersEnum.values();
        for(int i = 0; i < generalProperties.length; i++)
            allPossibleProperties.add(generalProperties[i].name().toLowerCase());
    }
}
