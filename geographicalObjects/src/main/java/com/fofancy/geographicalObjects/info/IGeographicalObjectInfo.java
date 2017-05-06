package com.fofancy.geographicalObjects.info;

import java.io.Serializable;

/**
 * Created by shaylin3 on 14.04.2017.
 * The main class that represents data about the geographical object
 */
public interface IGeographicalObjectInfo extends Serializable{
    String getName();
    void setName(String name);

    String getDescription();
    void setDescription(String description);
}
