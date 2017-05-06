package com.fofancy.mapInfo.nearestObjects;

import com.fofancy.apiconfiguration.cors.CorsFilter;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shaylin3 on 23.04.2017.
 */
@ApplicationPath("/")
public class ApplicationConfig extends Application {
    private final Set<Class<?>> classes;
    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(NearestObjectsRestService.class);
        c.add(MOXyJsonProvider.class);
        c.add(CorsFilter.class);

        classes = Collections.unmodifiableSet(c);
    }
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
