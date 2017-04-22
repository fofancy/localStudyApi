package com.fofancy.geographicalObjects.info.wiki;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shaylin3 on 19.04.2017.
 * Class designed for some roughing logic performing
 */
class WikiGeographicalObjectsInfoQueryHelper {
    private static String apiRoot;
    private static Properties apiQueriesResource = new Properties();

    /* Configuration files */
    private static final String API_ROOT_RESOURCE_FILE_NAME = "geographicalObjects/wiki/wikiApiRoot.properties";
    private static final String API_QUERIES_RESOURCE_FILE_NAME = "geographicalObjects/wiki/wikiApiQueries.properties";

    private static final String API_ROOT_PROPERTY_NAME = "wiki.api.root";
    private static final String API_QUERIES_GET_DESCRIPTION_PROPERTY_NAME = "wiki.api.getDescription";

    static {
        try {
            InputStream rootPropFile = WikiGeographicalObjectsInfoQueryHelper.class.getClassLoader().getResourceAsStream(API_ROOT_RESOURCE_FILE_NAME);
            InputStream apiQueriesFile = WikiGeographicalObjectsInfoQueryHelper.class.getClassLoader().getResourceAsStream(API_QUERIES_RESOURCE_FILE_NAME);

            Properties apiRootProp = new Properties();
            apiRootProp.load(rootPropFile);
            apiRoot = apiRootProp.getProperty(API_ROOT_PROPERTY_NAME);

            apiQueriesResource.load(apiQueriesFile);
        } catch (IOException ex) {
            Logger.getLogger(WikiGeographicalObjectsInfoQueryHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getApiRoot() {
        return apiRoot;
    }

    public static void setApiRoot(String apiRoot) {
        WikiGeographicalObjectsInfoQueryHelper.apiRoot = apiRoot;
    }

    public static Properties getApiQueriesResource() {
        return apiQueriesResource;
    }

    public static void setApiQueriesResource(Properties apiQueriesResource) {
        WikiGeographicalObjectsInfoQueryHelper.apiQueriesResource = apiQueriesResource;
    }


    public static String getGeographicalObjectsDescriptionQuery(String title) {
        StringBuilder endpointQueryString = new StringBuilder();
        String query = apiQueriesResource.getProperty(API_QUERIES_GET_DESCRIPTION_PROPERTY_NAME);

        endpointQueryString.append(apiRoot);

        title = title.replaceAll("\\s", "%20");

        query = query.replace(":title", String.valueOf(title));

        endpointQueryString.append(query);

        return endpointQueryString.toString();
    }
}
