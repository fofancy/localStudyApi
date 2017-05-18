package com.fofancy.mapInfo.nearestObjects.processor;

import com.fofancy.apiconfiguration.providers.Provider;
import com.fofancy.apiconfiguration.providers.ProvidersContext;
import com.fofancy.apiconfiguration.providers.ServiceImplementationInfo;
import com.fofancy.mapInfo.nearestObjects.INearestObjectsReceiver;

import javax.xml.ws.Service;

/**
 * Created by shaylin3 on 09.04.2017.
 */
public class NearestObjectsReceiverFactory {
    private static final String SERVICE_NAME = "nearest-objects";

    ProvidersContext context;
    String provider;

    private NearestObjectsReceiverFactory() {
    }

    public ProvidersContext getContext() {
        return context;
    }

    public void setContext(ProvidersContext context) {
        this.context = context;
    }

    public static NearestObjectsReceiverFactory newInstance() {
        return  new NearestObjectsReceiverFactory();
    }

    public void setProvider(String provider){
        this.provider = provider;
    }

    public INearestObjectsReceiver createNearestObjectsReceiver() {
        INearestObjectsReceiver receiver = null;

        Provider contextProvider = context.getProviderByName(provider);
        ServiceImplementationInfo serviceImplInfo = contextProvider.getServiceImplementationInfo(SERVICE_NAME);

        Service service = serviceImplInfo.getService();
        receiver = service.getPort(serviceImplInfo.getPortName(), INearestObjectsReceiver.class);


        return receiver;
    }
}
