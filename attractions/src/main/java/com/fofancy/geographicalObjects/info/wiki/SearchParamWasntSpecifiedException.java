package com.fofancy.geographicalObjects.info.wiki;

import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoException;

/**
 * Created by shaylin3 on 22.04.2017.
 */
public class SearchParamWasntSpecifiedException extends GeographicalObjectsInfoException {
    String paramName = new String();

    /**

     */
    public SearchParamWasntSpecifiedException() {

    }

    public SearchParamWasntSpecifiedException(String paramName) {
        super("\"" + paramName + "\"" + " wasn't specified;");
    }
}
