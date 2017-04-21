package com.fofancy.geographicalObjects.info.wiki;

import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shaylin3 on 21.04.2017.
 */
class WikiGeographicalObjectsInfoApiWrapper {
    public WikiGeographicalObjectsInfoApiWrapper() {
    }

    public static String getGeographicalObjectDescriptionByTitle(
            String title,
            IWikiResponseProcessor<String> processor) {
        String wikiResponse = queryWiki(title);

        return processor.process(wikiResponse);
    }

    private static String queryWiki(String title) {
        String queryString = WikiGeographicalObjectsInfoQueryHelper
                .getGeographicalObjectsDescriptionQuery(title);
        String queryResult = new String();

        try(InputStream is = new URL(queryString).openStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream()){
            int length;
            byte[] buffer = new byte[1024];
            while((length = is.read(buffer)) != -1){
                result.write(buffer, 0, length);
            }

            queryResult = result.toString("UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(WikiGeographicalObjectsInfoQueryHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return queryResult;
    }


}
