/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects.wiki;

/**
 *
 * @author shaylin3
 */
public class IllegalNearestObjectsPropertyName extends WikiNearestObjectsReceiverException {

    /**
     * Creates a new instance of
     * <code>IllegalNearestObjectsPropertyName</code>
     * without detail message.
     */
    public IllegalNearestObjectsPropertyName() {
    }

    /**
     * Constructs an instance of
     * <code>IllegalNearestObjectsPropertyName</code>
     * with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public IllegalNearestObjectsPropertyName(String msg) {
        super(msg);
    }
}
