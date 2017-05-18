package com.fofancy.geographicalObjects.processor;

import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;
import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by shaylin3 on 22.04.2017.
 */
public class GeographicalObjectsInfoProcessorsTest {


    @Test
    public void geographicalObjectsInfoEJBTest() {
        GeographicalObjectsInfoEJB ejb = new GeographicalObjectsInfoEJB();

        GeographicalObjectsInfoParameters params = new GeographicalObjectsInfoParameters();
        params.setProperty("name", "Louvre");

        IGeographicalObjectInfo response = ejb.getGeographicalObjectInfo(params, "Wiki");

        Assert.assertNotNull(response);
    }
}
