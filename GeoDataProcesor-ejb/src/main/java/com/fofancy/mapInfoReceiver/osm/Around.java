/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.osm;

/**
 *
 * @author shaylin3
 */
public class Around implements CriteriaValue {
    private int radius;
    private double latitude;
    private double longitude;

    public Around(int radius, double latitude, double longitude) {
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    @Override
    public String toString(){
        StringBuilder query = new StringBuilder();
        
         query
                .append("node(around:")
                .append(radius).append(",")
                .append(latitude).append(",")
                .append(longitude).append(");");
        
        return query.toString();
    }
}
