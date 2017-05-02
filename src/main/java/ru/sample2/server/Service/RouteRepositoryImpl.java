package ru.sample2.server.service;

import ru.sample2.server.DAO.Impl.RouteDAOImpl;
import ru.sample2.shared.Route;
import ru.sample2.shared.RouteDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 19.04.2017.
 */
public class RouteRepositoryImpl implements RouteRepository {
    private List<Route> routes;
    private RouteDAOImpl routeDAO;

    @Override
    public RouteDTO addRoute(String startPoint,
                             String endPoint,
                             String intermediatePoint1,
                             String intermediatePoint2,
                             String dayweek,
                             String time) {
        if (routes == null) {
            routes = new ArrayList<>();
        }
        if (routeDAO == null) {
            routeDAO = new RouteDAOImpl();
        }

        routes.clear();
        routes.addAll(routeDAO.getAllRoute());
        Route  newRoute = new Route(startPoint, endPoint, intermediatePoint1, intermediatePoint2, dayweek, time);
        routes.add(newRoute);
        routeDAO.addRoute(newRoute);

        RouteDTO routeDTO = new RouteDTO(routes);
        return routeDTO;
    }

    @Override
    public RouteDTO sendRoute() {
        if (routes == null) {
            routes = new ArrayList<>();
        }
        if (routeDAO == null) {
            routeDAO = new RouteDAOImpl();
        }
        routes.clear();
        routes.addAll(routeDAO.getAllRoute());

        RouteDTO routeDTO = new RouteDTO(routes);
        return routeDTO;
    }

    @Override
    public RouteDTO deleteRoute( int id) {
        return null;
    }

    @Override
    public RouteDTO findRoutes(String startPoint, String endPoint) {
        if (routes == null) {
            routes = new ArrayList<>();
        }
        if (routeDAO == null) {
            routeDAO = new RouteDAOImpl();
        }
        routes.clear();
        RouteDTO routeDTO = new RouteDTO(routeDAO.findRoute(startPoint, endPoint));
        return routeDTO;
    }

}
