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
public enum OutEnum {
    BODY("out%20body;");

    private final String qValue;

    private OutEnum(String qValue){
        this.qValue = qValue;
    }

    @Override
    public String toString(){
        return qValue;
    }
}