package com.fofancy.apiconfiguration.providers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by shaylin3 on 27.04.2017.
 */
public class ProvidersXmlConfigSaxHandler extends DefaultHandler {
    private HashMap<String, Provider> providers = new HashMap<String, Provider>();

    private Stack<String> elementStack = new Stack<String>();

    private ServiceHelper serviceHelper = new ServiceHelper();
    private ServiceImplementationInfo currentImplementationInfo = new ServiceImplementationInfo();
    private HashMap<String, ServiceImplementationInfo> currentServicesImplementations = new HashMap<String, ServiceImplementationInfo>();
    private Provider currentProvider = new Provider();
    private String currentProviderName = new String();

    public ProvidersXmlConfigSaxHandler() {
    }

    public HashMap<String, Provider> getProviders() {
        return providers;
    }

    public void setProviders(HashMap<String, Provider> providers) {
        this.providers = providers;
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        this.elementStack.push(qName);

        if(qName.equals("service")){
            serviceHelper = new ServiceHelper();
        }
        else if(qName.equals("services")){
            currentServicesImplementations = new HashMap<String, ServiceImplementationInfo>();
        }
        else if(qName.equals("service-implementation-info")){
            currentImplementationInfo = new ServiceImplementationInfo();
        }
        else if(qName.equals("provider")) {
            currentProvider = new Provider();
        }
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        this.elementStack.pop();

        if(qName.equals("service-implementation-info")) {
            buildServiceImplementation();
            currentServicesImplementations.put(serviceHelper.getServiceName(), currentImplementationInfo);
        }
        else if(qName.equals("services-implementations")){
            currentProvider.setServicesImplementations(currentServicesImplementations);
        }
        else if(qName.equals("provider")) {
            providers.put(currentProviderName, currentProvider);
        }
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {

        String value = new String(ch, start, length).trim();
        if(value.length() == 0) return; // ignore white space

        if(currentElement().equals("wsdl-url"))
            serviceHelper.setWsdlUrl(value);
        else if(currentElement().equals("namespace"))
            serviceHelper.setNamespace(value);
        else if(currentElement().equals("port-name"))
            serviceHelper.setPortName(value);

        else if(currentElement().equals("service-name"))
            serviceHelper.setServiceName(value);

        else if(currentElement().equals("provider-name"))
            currentProviderName = value;
    }

    private String currentElement() {
        return this.elementStack.peek();
    }

    public void error(SAXParseException e) {
        throw new ProvidersXmlConfigParsingException(e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXException {
        throw new ProvidersXmlConfigParsingException(e.getMessage());
    }

    private String currentElementParent() {
        if(this.elementStack.size() < 2) return null;
        return this.elementStack.get(this.elementStack.size()-2);
    }

    private boolean isServiceInfoTag(String qName) {
        return "wsdl-url".equals(qName) ||
                "namespace".equals(qName) ||
                "port-name".equals(qName);
    }

    private void buildServiceImplementation() {
        URL wsdl = null;

        try {
            wsdl = new URL(serviceHelper.getWsdlUrl());
        } catch (MalformedURLException e) {
            throw new ProvidersXmlConfigParsingException(e.getMessage());
        }

        String namespace = serviceHelper.getNamespace();
        String serviceName = serviceHelper.getServiceName();
        String portName = serviceHelper.getPortName();


        QName qName = new QName(namespace, serviceName);
        QName qPort = new QName(namespace, portName );

        Service service = Service.create(wsdl, qName);

        currentImplementationInfo.setService(service);
        currentImplementationInfo.setPortName(qPort);
    }
}
