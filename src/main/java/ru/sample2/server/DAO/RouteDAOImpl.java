package ru.sample2.server.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.sample2.shared.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 21.04.2017.
 */
public class RouteDAOImpl implements RouteDAO {
    private Session session;

    @Override
    public void addRoute(Route route) {
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();
        Transaction tx = session.beginTransaction();

        RoutesEntity newRouteEntity = new RoutesEntity();
        newRouteEntity.setStartpoint(route.getStartPoint());
        newRouteEntity.setEndpoint(route.getEndPoint());

        session.save(newRouteEntity);
        tx.commit();

        session.close();
    }

    @Override
    public List<Route> getAllRoute() {
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        session = sessions.openSession();
        Query query = session.createQuery("FROM RoutesEntity ");

        List <RoutesEntity> routesEntities =  query.list();
        List <Route> routes = new ArrayList<>();
        for (RoutesEntity routeEntity : routesEntities) {
            Route route = new Route();
            route.setStartPoint(routeEntity.getStartpoint());
            route.setEndPoint(routeEntity.getEndpoint());
            routes.add(route);

        }
        session.close();
        return routes;
    }

}
