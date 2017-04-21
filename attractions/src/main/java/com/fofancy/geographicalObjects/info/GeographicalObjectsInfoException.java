package com.fofancy.geographicalObjects.info;

/**
 * Created by shaylin3 on 17.04.2017.
 */
public class GeographicalObjectsInfoException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>NearestObjectsReceiverException</code>
     * without detail message.
     */
    public GeographicalObjectsInfoException() {
    }

    /**
     * Constructs an instance of
     * <code>NearestObjectsReceiverException</code>
     * with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public GeographicalObjectsInfoException(String msg) {
        super(msg);
    }
}
