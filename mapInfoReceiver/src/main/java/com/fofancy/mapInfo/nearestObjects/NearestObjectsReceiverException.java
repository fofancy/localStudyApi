/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects;

/**
 *
 * @author shaylin3
 */
public class NearestObjectsReceiverException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>NearestObjectsReceiverException</code>
     * without detail message.
     */
    public NearestObjectsReceiverException() {
    }

    /**
     * Constructs an instance of
     * <code>NearestObjectsReceiverException</code>
     * with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public NearestObjectsReceiverException(String msg) {
        super(msg);
    }
}
