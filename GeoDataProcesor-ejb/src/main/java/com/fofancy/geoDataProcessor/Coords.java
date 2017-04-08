/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.geoDataProcessor;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO Coords class 
 * 
 * @author shaylin3
 */
public class Coords {
    private double mLongitude;
    private double mLatitude;
    
    public Coords(){
    }
    
    public Coords(
        @JsonProperty("longitude") double longitude, 
        @JsonProperty("latitude") double latitude
    ){
        mLongitude = longitude;
        mLatitude = latitude;
    }

    /**
     * @return the mLongitude
     */
    public double getLongitude() {
        return mLongitude;
    }

    /**
     * @param mLongitude the mLongitude to set
     */
    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    /**
     * @return the mLatitude
     */
    public double getLatitude() {
        return mLatitude;
    }

    /**
     * @param mLatitude the mLatitude to set
     */
    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }
}
