package com.fofancy.geographicalObjects.info.wiki;

/**
 * Created by shaylin3 on 21.04.2017.
 *
 * It is considered that this class could be used for several wiki query processors
 * generic T defines returning type
 */
public interface IWikiResponseProcessor<T> {
    T process(String response);
}
