package ru.sample2.server.config;

import com.google.inject.AbstractModule;
import ru.sample2.server.AddressesRepository;
import ru.sample2.server.AddressesRepositoryFromBaseImpl;
import ru.sample2.server.DAO.RouteDAO;
import ru.sample2.server.DAO.RouteDAOImpl;
import ru.sample2.server.RouteRepository;
import ru.sample2.server.RouteRepositoryImpl;

import javax.inject.Singleton;

/**
 * Created by Anna on 23.02.2017.
 */
public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AddressesRepository.class).to(AddressesRepositoryFromBaseImpl.class).in(Singleton.class);
        bind(RouteRepository.class).to(RouteRepositoryImpl.class).in(Singleton.class);
        bind(RouteDAO.class).to(RouteDAOImpl.class);
    }
}
