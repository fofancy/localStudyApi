/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver;

import java.io.Serializable;

/**
 * The object oriented representation of maps' provider objects 
 * @author shaylin3
 */
public interface IMapObject extends Serializable {
    String getId();
    void setId(String id);
    
    String getTitle();
    void setTitle(String title);
    
    double getLongitude();
    void setLongitude(double longitude);
    
    double getLatitude();
    void setLatitude(double latitude);
    
    double getDistance();
    void setDistance(double dist);
}
