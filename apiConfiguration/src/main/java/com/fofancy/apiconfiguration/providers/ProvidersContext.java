package com.fofancy.apiconfiguration.providers;

import com.fofancy.apiconfiguration.concurrency.Lock;

import javax.annotation.PostConstruct;
import javax.ejb.LockType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shaylin3 on 25.04.2017.
 */

@Named
@ApplicationScoped
@Lock
public class ProvidersContext {
    private String DEPLOYED_PROVIDERS_XML_CONFIG = "providers/providers-config.xml";
    private String DEPLOYED_PROVIDERS_XML_CONFIG_SCHEMA = "providers/providers-config-schema.xsd";

    private volatile ConcurrentHashMap<String, Provider> providers = new ConcurrentHashMap<String, Provider>();

    @PostConstruct
    public void init() {
        providers = parseProvidersXmlConfig(DEPLOYED_PROVIDERS_XML_CONFIG, DEPLOYED_PROVIDERS_XML_CONFIG_SCHEMA);

        System.out.println("Providers context inited");
    }

    public ProvidersContext() {
    }

    public ConcurrentHashMap<String, Provider> getProviders() {
        return providers;
    }

    public void setProviders(ConcurrentHashMap<String, Provider> providers) {
        this.providers = providers;
    }

    public Provider getProviderByName(String name) {
        if(providers.containsKey(name))
            return providers.get(name);
        else
            throw new NoSuchProviderException();
    }

    @Lock(LockType.WRITE)
    public ConcurrentHashMap<String, Provider> parseProvidersXmlConfig(String providersXmlConfig, String ProvidersXmlSchema) {
        ProvidersXmlConfigParser parser = new ProvidersXmlConfigParser();
        return parser.parse(providersXmlConfig, ProvidersXmlSchema);
    }

    public void updateProviders() {

    }
}
