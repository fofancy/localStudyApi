/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver;

/**
 *
 * @author shaylin3
 */
public interface IMapDataReceiverProperties {
    Object getProperty(String propertyName);
    void setProperty(String propertyName, Object property);
    
    boolean containsProperty(String propertyName);
}
