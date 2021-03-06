package com.fofancy.geographicalObjects.info;

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
 * Created by shaylin3 on 17.04.2017.
 *
 * Parameters for query. It is considered that params will be put as a parameter of main method
 * in GeographicalobjectsInfoReceiver.receive(...);
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class GeographicalObjectsInfoParameters implements Serializable {
    /* The main reason why String was used instead of GeographicalObjectsInfoParametersEnum
    * was simplifing data structures in order to provide simplified serialization and
    * sending parameters using data exchanging protocols
    * */
    HashMap<String, Object> properties;

    // Contains all possible properties for Wiki map data receiver
    HashSet<String> allPossibleProperties;

    public GeographicalObjectsInfoParameters() {
        properties = new HashMap<>();

        initAllPossibleProperties();
    }

    public Object getProperty(String propertyName) {
        if(properties.containsKey(propertyName)){
            return properties.get(propertyName);
        }
        else
            throw new IllegalPropertyNameException();
    }

    public void setProperty(String propertyName, Object property) {
        if(this.allPossibleProperties.contains(propertyName)){
            this.properties.put(propertyName, property);
        }
        else
            throw new IllegalPropertyNameException();
    }

    public boolean containsProperty(String propertyName) {
        if(this.allPossibleProperties.contains(propertyName)){
            return this.properties.containsKey(propertyName);
        }
        else
            throw new IllegalPropertyNameException();
    }


    // Reads properties form property file
    public void readProperties(String fileName) {
        try {
            Properties properties = new Properties();

            InputStream propertiesStream = GeographicalObjectsInfoParameters.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(propertiesStream);

            Set<String> propertyNames = properties.stringPropertyNames();

            for(String propertyName : propertyNames){
                setProperty(propertyName, properties.getProperty(propertyName));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeographicalObjectsInfoParameters.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeographicalObjectsInfoParameters.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeographicalObjectsInfoException ex) {
            Logger.getLogger(GeographicalObjectsInfoParameters.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Initializes all possible properties for wiki data receiver
    private void initAllPossibleProperties(){
        this.allPossibleProperties = new HashSet<>();

        GeographicalObjectsInfoParametersEnum[] propertiesEnums = GeographicalObjectsInfoParametersEnum.values();
        for(int i = 0; i < propertiesEnums.length; i++)
            allPossibleProperties.add(propertiesEnums[i].name().toLowerCase());

//        NearestObjectsReceiverPropertiesEnum[] generalProperties = NearestObjectsReceiverPropertiesEnum.values();
//        for(int i = 0; i < generalProperties.length; i++)
//            allPossibleProperties.add(generalProperties[i].name().toLowerCase());
    }
}
