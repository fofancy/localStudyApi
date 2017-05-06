package com.fofancy.apiconfiguration.providers;

import javax.ejb.Singleton;
import java.util.HashMap;

/**
 * Created by shaylin3 on 25.04.2017.
 */

@Singleton
public class ProvidersContext {
    private String PROVIDERS_XML_CONFIG = "providers/providers-config.xml";
    private String PROVIDERS_XML_CONFIG_SCHEMA = "providers/providers-config-schema.xsd";

    private HashMap<String, Provider> providers = new HashMap<String, Provider>();

    public Provider getProviderByName(String name) {
        if(providers.containsKey(name))
            return providers.get(name);
        else
            throw new NoSuchProviderException();
    }

    public void parseProvidersXmlConfig() {
        ProvidersXmlConfigParser parser = new ProvidersXmlConfigParser();
        providers = parser.parse(PROVIDERS_XML_CONFIG, PROVIDERS_XML_CONFIG_SCHEMA);
    }
}
