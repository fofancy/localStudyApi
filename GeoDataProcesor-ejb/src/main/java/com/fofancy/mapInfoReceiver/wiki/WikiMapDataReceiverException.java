/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.wiki;

import com.fofancy.mapInfoReceiver.MapDataReceiverException;

/**
 *
 * @author shaylin3
 */
public class WikiMapDataReceiverException extends MapDataReceiverException {

    /**
     * Creates a new instance of
     * <code>WikiMapDataReceiverException</code>
     * without detail message.
     */
    public WikiMapDataReceiverException() {
    }

    /**
     * Constructs an instance of
     * <code>WikiMapDataReceiverException</code>
     * with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public WikiMapDataReceiverException(String msg) {
        super(msg);
    }
}
