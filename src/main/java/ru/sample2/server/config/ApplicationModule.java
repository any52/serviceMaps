package ru.sample2.server.config;

import com.google.inject.AbstractModule;
import ru.sample2.server.service.AddressesRepository;
import ru.sample2.server.service.AddressesRepositoryImpl;
import ru.sample2.server.DAO.RouteDAO;
import ru.sample2.server.DAO.Impl.RouteDAOImpl;
import ru.sample2.server.service.RouteRepository;
import ru.sample2.server.service.RouteRepositoryImpl;

import javax.inject.Singleton;

/**
 * Created by Anna on 23.02.2017.
 */
public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AddressesRepository.class).to(AddressesRepositoryImpl.class).in(Singleton.class);
        bind(RouteRepository.class).to(RouteRepositoryImpl.class).in(Singleton.class);
        bind(RouteDAO.class).to(RouteDAOImpl.class);
    }
}
