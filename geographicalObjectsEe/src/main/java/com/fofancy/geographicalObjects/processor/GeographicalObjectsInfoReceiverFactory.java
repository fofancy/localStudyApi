package com.fofancy.geographicalObjects.processor;

import com.fofancy.apiconfiguration.providers.Provider;
import com.fofancy.apiconfiguration.providers.ProvidersContext;
import com.fofancy.apiconfiguration.providers.ServiceImplementationInfo;
import com.fofancy.geographicalObjects.info.IGeographicalObjectsInfoReceiver;

import javax.xml.ws.Service;

/**
 * Created by shaylin3 on 15.04.2017.
 * Factory designed for creating new instances of GeographicalObjectsInfoReceiver
 */
public class GeographicalObjectsInfoReceiverFactory {
    private static final String SERVICE_NAME = "geographical-objects-info";

    private ProvidersContext context;
    private String provider;

    protected GeographicalObjectsInfoReceiverFactory() {
    }

    public ProvidersContext getContext() {
        return context;
    }

    public void setContext(ProvidersContext context) {
        this.context = context;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public static GeographicalObjectsInfoReceiverFactory newInstance() {
        return new GeographicalObjectsInfoReceiverFactory();
    }

    /* Creates a new instance of  createGeographicalObjectsInfoReceiver
    *  It is consdered that property "provider" would be set before calling this method
    *
    * */
    public IGeographicalObjectsInfoReceiver createGeographicalObjectsInfoReceiver() {
        IGeographicalObjectsInfoReceiver receiver = null;

        // DEBUG
        System.out.println(provider);
        System.out.println("Context" + context.toString());
        Provider contextProvider = context.getProviderByName(provider);
        ServiceImplementationInfo serviceImplInfo = contextProvider.getServiceImplementationInfo(SERVICE_NAME);

        System.out.println(serviceImplInfo.getPortName());
        Service service = serviceImplInfo.getService();
        receiver = service.getPort(serviceImplInfo.getPortName(), IGeographicalObjectsInfoReceiver.class);

        return receiver;
    }
}
