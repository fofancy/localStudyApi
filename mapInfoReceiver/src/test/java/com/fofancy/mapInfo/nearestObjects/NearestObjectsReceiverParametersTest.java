package com.fofancy.mapInfo.nearestObjects;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by shaylin3 on 09.05.2017.
 */
public class NearestObjectsReceiverParametersTest {

    @Test
    public void allPossiblePropertiesTest() {
        NearestObjectsReceiverParametersImpl parameters = new NearestObjectsReceiverParametersImpl();

        parameters.setProperty("latitude", 46);

        assertEquals(new Integer(46), Integer.valueOf(String.valueOf(parameters.getProperty("latitude"))));
    }
}
