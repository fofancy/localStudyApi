package com.fofancy.geographicalObjects.info.wiki;

import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;
import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;

/**
 * Created by shaylin3 on 17.04.2017.
 *
 * Class created for quering wiki for geographical object info receiving
 */
class WikiGeographicalObjectsInfoQuery {
    GeographicalObjectsInfoParameters properties;

    public GeographicalObjectsInfoParameters getProperties() {
        return properties;
    }

    public void setProperties(GeographicalObjectsInfoParameters properties) {
        this.properties = properties;
    }

    public IGeographicalObjectInfo execute() {
        if(properties.containsProperty("name")) {
            String name = (String) properties.getProperty("name");
            String description = WikiGeographicalObjectsInfoApiWrapper.getGeographicalObjectDescriptionByTitle(
                    name,
                    new WikiGeographicalObjectsInfoResponseProcessor()
            );

            WikiGeographicalObjectInfo objectInfo = new WikiGeographicalObjectInfo();

            objectInfo.setName(name);
            objectInfo.setDescription(description);

            return objectInfo;
        }
        else
            throw new SearchParamWasntSpecifiedException("name");
    }
}
