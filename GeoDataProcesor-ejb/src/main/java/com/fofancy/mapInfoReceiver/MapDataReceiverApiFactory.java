package com.fofancy.mapInfoReceiver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shaylin3 on 09.04.2017.
 */
public class MapDataReceiverApiFactory {
    IMapDataReceiverApi mapDataReceiverApiProvider;
    String provider;

    private MapDataReceiverApiFactory() {
    }

    public static MapDataReceiverApiFactory newInstance() {
        return  new MapDataReceiverApiFactory();
    }

    public void setMapDataReceiverProvider(String provider){
        this.provider = provider;
    }

    public IMapDataReceiverApi createMapDataReceiverApi() {
        IMapDataReceiverApi mapDataReceiverApi = null;

        Class<?> clazz = null;
        try {
            clazz = Class.forName(provider);
            Constructor<?> ctor = null;
            ctor = clazz.getConstructor();
            mapDataReceiverApi = (IMapDataReceiverApi) ctor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            Logger.getLogger(MapDataReceiverApiFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            Logger.getLogger(MapDataReceiverApiFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InstantiationException e) {
            Logger.getLogger(MapDataReceiverApiFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InvocationTargetException e) {
            Logger.getLogger(MapDataReceiverApiFactory.class.getName()).log(Level.SEVERE, null, e);
        }

        return mapDataReceiverApi;
    }
}
