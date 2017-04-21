package com.fofancy.geographicalObjects.info.wiki;

/**
 * Created by shaylin3 on 21.04.2017.
 */
public interface IWikiResponseProcessor<T> {
    T process(String response);
}
