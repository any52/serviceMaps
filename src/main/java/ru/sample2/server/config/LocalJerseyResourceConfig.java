package ru.sample2.server.config;

import com.google.inject.Injector;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.*;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

public class LocalJerseyResourceConfig extends ResourceConfig
{
    @Inject
    public LocalJerseyResourceConfig(ServiceLocator serviceLocator, @Context ServletContext servletContext)
    {
        Injector injector = (Injector)servletContext.getAttribute(Injector.class.getName());

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge g2h = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        g2h.bridgeGuiceInjector(injector);

        LocalApplication localApplication = new LocalApplication();
        registerClasses(localApplication.getClasses());
        registerInstances(localApplication.getSingletons());
    }
}
