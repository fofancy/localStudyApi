package com.fofancy.geographicalObjects.info;

import java.io.Serializable;

/**
 * Created by shaylin3 on 07.05.2017.
 */
public class GeographicalObjectInfo implements IGeographicalObjectInfo, Serializable {
    private String name;
    private String description;

    public GeographicalObjectInfo() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
