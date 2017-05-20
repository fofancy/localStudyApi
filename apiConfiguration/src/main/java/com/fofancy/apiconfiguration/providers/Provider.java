package com.fofancy.apiconfiguration.providers;

import javax.xml.ws.Service;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shaylin3 on 25.04.2017.
 */

public class Provider {
    private ConcurrentHashMap<String, ServiceImplementationInfo> servicesImplementations;

    public ConcurrentHashMap<String, ServiceImplementationInfo> getServicesImplementations() {
        return servicesImplementations;
    }

    public void setServicesImplementations(ConcurrentHashMap<String, ServiceImplementationInfo> servicesImplementations) {
        this.servicesImplementations = servicesImplementations;
    }

    public ServiceImplementationInfo getServiceImplementationInfo(String serviceName) {
        return servicesImplementations.get(serviceName);
    }
}
