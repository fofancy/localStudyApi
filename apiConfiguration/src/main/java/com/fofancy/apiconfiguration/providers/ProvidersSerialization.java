package com.fofancy.apiconfiguration.providers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shaylin3 on 20.05.2017.
 */
public class ProvidersSerialization extends StdSerializer<ConcurrentHashMap> {
    public ProvidersSerialization() {
        this(null);
    }

    public ProvidersSerialization(Class<ConcurrentHashMap> t) {
        super(t);
    }

    @Override
    public void serialize(
            ConcurrentHashMap providers,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        ConcurrentHashMap<String, Provider> castedProviders = (ConcurrentHashMap<String, Provider>)providers;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("providers");
        jsonGenerator.writeStartArray();
        for(Map.Entry<String, Provider> provider : castedProviders.entrySet()) {
            String key = provider.getKey();
            Provider value = provider.getValue();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", key);

            jsonGenerator.writeFieldName("serviceImplementations");
            jsonGenerator.writeStartArray();
            for(Map.Entry<String, ServiceImplementationInfo> serviceImpl : value.getServicesImplementations().entrySet()) {
                String serviceImplName = serviceImpl.getKey();

                jsonGenerator.writeString(serviceImplName);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
