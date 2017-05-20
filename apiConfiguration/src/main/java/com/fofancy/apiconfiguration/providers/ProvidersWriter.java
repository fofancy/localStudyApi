package com.fofancy.apiconfiguration.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shaylin3 on 20.05.2017.
 */
@javax.ws.rs.ext.Provider
@Produces("text/json")
public class ProvidersWriter implements MessageBodyWriter<ConcurrentHashMap<String, Provider>> {


    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(ConcurrentHashMap<String, Provider> providers, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        long size = 0;
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(ConcurrentHashMap.class, new ProvidersSerialization());

        mapper.registerModule(module);
        String serialized = null;
        try {
            serialized = mapper.writeValueAsString(providers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        size += "{providers:[".length();
//
//        for(Map.Entry<String, Provider> provider : providers.entrySet()) {
//            String key = provider.getKey();
//            Provider value = provider.getValue();
//
//            size += "{\"".length() + "name".length() + "\":".length();
//            size += "\"".length() + key.length() + "\",".length();
//            size += "\"".length() + "serviceImplementations".length() + "\":".length();
//
//            size += "[".length();
//            for(Map.Entry<String, ServiceImplementationInfo> serviceImpl : value.getServicesImplementations().entrySet()) {
//                String serviceImplName = serviceImpl.getKey();
//                size += "\"".length() + serviceImplName.length() + "\"".length();
//            }
//
//            size += "]".length();
//            // Commas for service implementations array
//            size += value.getServicesImplementations().size() - 1;
//        }
//
//        // Commas for providers' array
//        size += providers.size() - 1;
//        size += "]}".length();
//        return size;

        return serialized.length();
    }

    @Override
    public void writeTo(
            ConcurrentHashMap<String, Provider> providers,
            Class<?> aClass, Type type,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, Object> multivaluedMap,
            OutputStream outputStream) throws IOException, WebApplicationException {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(ConcurrentHashMap.class, new ProvidersSerialization());

        mapper.registerModule(module);

       // String serialized = mapper.writeValueAsString(providers);
        mapper.writeValue(outputStream, providers);
    }
}