package com.fofancy.geographicalObjects.info;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shaylin3 on 15.04.2017.
 * Factory designed for creating new instances of GeographicalObjectsInfoReceiver
 */
public class GeographicalObjectsInfoReceiverFactory {
    String provider;

    protected GeographicalObjectsInfoReceiverFactory() {
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public static GeographicalObjectsInfoReceiverFactory newInstance() {
        return new GeographicalObjectsInfoReceiverFactory();
    }

    /* Creates a new instance of  createGeographicalObjectsInfoReceiver
    *  It is consdered that property "provider" would be set before calling this method
    *
    * */
    public IGeographicalObjectsInfoReceiver createGeographicalObjectsInfoReceiver() {
        IGeographicalObjectsInfoReceiver receiver = null;

        Class<?> clazz = null;
        try {
            clazz = Class.forName(provider);
            Constructor<?> ctor = null;
            ctor = clazz.getConstructor();
            receiver = (IGeographicalObjectsInfoReceiver) ctor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            Logger.getLogger(GeographicalObjectsInfoReceiverFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            Logger.getLogger(GeographicalObjectsInfoReceiverFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InstantiationException e) {
            Logger.getLogger(GeographicalObjectsInfoReceiverFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InvocationTargetException e) {
            Logger.getLogger(GeographicalObjectsInfoReceiverFactory.class.getName()).log(Level.SEVERE, null, e);
        }

        return receiver;
    }
}
