package com.fofancy.mapInfo.nearestObjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shaylin3 on 09.04.2017.
 */
public class NearestObjectsReceiverFactory {
    String provider;

    private NearestObjectsReceiverFactory() {
    }

    public static NearestObjectsReceiverFactory newInstance() {
        return  new NearestObjectsReceiverFactory();
    }

    public void setProvider(String provider){
        this.provider = provider;
    }

    public INearestObjectsReceiver createNearestObjectsReceiver() {
        INearestObjectsReceiver nearestObjectsReceiver = null;

        Class<?> clazz = null;
        try {
            clazz = Class.forName(provider);
            Constructor<?> ctor = null;
            ctor = clazz.getConstructor();
            nearestObjectsReceiver = (INearestObjectsReceiver) ctor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            Logger.getLogger(NearestObjectsReceiverFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            Logger.getLogger(NearestObjectsReceiverFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InstantiationException e) {
            Logger.getLogger(NearestObjectsReceiverFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InvocationTargetException e) {
            Logger.getLogger(NearestObjectsReceiverFactory.class.getName()).log(Level.SEVERE, null, e);
        }

        return nearestObjectsReceiver;
    }
}
