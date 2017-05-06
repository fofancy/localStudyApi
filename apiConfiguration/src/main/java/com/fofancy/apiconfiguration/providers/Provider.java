package com.fofancy.apiconfiguration.providers;

import javax.xml.ws.Service;
import java.util.HashMap;

/**
 * Created by shaylin3 on 25.04.2017.
 */
public class Provider {
    private HashMap<String, ServiceImplementationInfo> servicesImplementations;

    public HashMap<String, ServiceImplementationInfo> getServicesImplementations() {
        return servicesImplementations;
    }

    public void setServicesImplementations(HashMap<String, ServiceImplementationInfo> servicesImplementations) {
        this.servicesImplementations = servicesImplementations;
    }
}
