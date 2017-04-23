package ru.sample2.server.config;

import ru.sample2.server.EndPointImpl;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class LocalApplication extends Application
{
    public Set<Class<?>> getClasses()
    {
        final Set<Class<?>> result = new HashSet<>();
        result.add(EndPointImpl.class);
        return result;
    }
}