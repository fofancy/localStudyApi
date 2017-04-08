/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.osm;

/**
 * MapObjectsEnum - independent set of necessary map objects types
 * Each implementation should have a logic which will understand that types and translate them into 
 * it's own. It is supposed, that there are different names and features in each map provider and data receiving type
 */
public enum MapObjectsEnum {
    MUSEUM,
    MONUMENT
}
