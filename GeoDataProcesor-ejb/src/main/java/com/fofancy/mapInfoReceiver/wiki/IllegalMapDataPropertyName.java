/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.wiki;

/**
 *
 * @author shaylin3
 */
public class IllegalMapDataPropertyName extends WikiMapDataReceiverException {

    /**
     * Creates a new instance of
     * <code>IllegalMapDataPropertyName</code>
     * without detail message.
     */
    public IllegalMapDataPropertyName() {
    }

    /**
     * Constructs an instance of
     * <code>IllegalMapDataPropertyName</code>
     * with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public IllegalMapDataPropertyName(String msg) {
        super(msg);
    }
}
