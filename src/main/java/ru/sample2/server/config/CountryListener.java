package ru.sample2.server.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Created by Anna on 22.02.2017.
 */
public class CountryListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return  Guice.createInjector(new ApplicationModule());
    }
}
