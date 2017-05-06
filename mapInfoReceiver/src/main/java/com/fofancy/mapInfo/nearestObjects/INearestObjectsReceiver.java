/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects;

import java.util.List;

/**
 * This is the main interface for api for data receiving from any type of map provider.
 * It is supposed that there will be implementations for each type of map provider and type of data receiving.
 * The usage:
 *  1) Set set of desirable objects
 *  2) Set additional properties 
 */
public interface INearestObjectsReceiver {
    
    void setCoords(Coords coords);
    Coords getCoords();
    
    List<IMapObject> getNearestMapObjects();
    
    /* 
    * It is supposed that implementations will have default properties, but if it will be necessary
    * user can override them.
    */
    void setMapDataReceiverProperties(INearestObjectsReceiverProperties properties);
    INearestObjectsReceiverProperties getDataReceiverProperties();
}
