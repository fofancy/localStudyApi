package com.fofancy.geographicalObjects.info;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shaylin3 on 15.04.2017.
 */
public class GeographicalObjectsInfoFactory {
    String provider;

    protected GeographicalObjectsInfoFactory() {
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public static GeographicalObjectsInfoFactory newInstance() {
        return new GeographicalObjectsInfoFactory();
    }

    public IGeographicalObjectsInfoReceiver createSightDescriptionReceiver() {
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
            Logger.getLogger(GeographicalObjectsInfoFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            Logger.getLogger(GeographicalObjectsInfoFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InstantiationException e) {
            Logger.getLogger(GeographicalObjectsInfoFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InvocationTargetException e) {
            Logger.getLogger(GeographicalObjectsInfoFactory.class.getName()).log(Level.SEVERE, null, e);
        }

        return receiver;
    }
}
