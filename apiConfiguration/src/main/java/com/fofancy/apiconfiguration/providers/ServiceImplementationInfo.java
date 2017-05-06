package com.fofancy.apiconfiguration.providers;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * Created by shaylin3 on 30.04.2017.
 */
public class ServiceImplementationInfo {
    private Service service;
    private QName portName;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public QName getPortName() {
        return portName;
    }

    public void setPortName(QName portName) {
        this.portName = portName;
    }
}
