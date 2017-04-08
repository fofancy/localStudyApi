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
public class MapDataReceiverException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>MapDataReceiverException</code>
     * without detail message.
     */
    public MapDataReceiverException() {
    }

    /**
     * Constructs an instance of
     * <code>MapDataReceiverException</code>
     * with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public MapDataReceiverException(String msg) {
        super(msg);
    }
}
