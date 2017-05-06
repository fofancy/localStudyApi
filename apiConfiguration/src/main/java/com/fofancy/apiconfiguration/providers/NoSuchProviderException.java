package com.fofancy.apiconfiguration.providers;

/**
 * Created by shaylin3 on 30.04.2017.
 */
public class NoSuchProviderException extends ProvidersContextException {

    /**

     */
    public NoSuchProviderException() {

    }

    public NoSuchProviderException(String msg) {
        super(msg);
    }
}