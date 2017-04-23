package ru.sample2.server;

import ru.sample2.server.DAO.RouteDAOImpl;
import ru.sample2.shared.Route;
import ru.sample2.shared.RouteDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 19.04.2017.
 */
public class RouteRepositoryImpl implements RouteRepository {
    private List<Route> routes;
    private RouteDAOImpl routeDAO  = new RouteDAOImpl();

    @Override
    public RouteDTO sendRoute(Route route) {
        if (routes == null) {
            routes = new ArrayList<>();
        }
        routes.addAll(routeDAO.getAllRoute());
        routes.add(route);

        RouteDTO routeDTO = new RouteDTO(routes);
        return routeDTO;
    }
}
