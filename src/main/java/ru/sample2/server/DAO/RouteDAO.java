package ru.sample2.server.DAO;

import ru.sample2.shared.Route;

import java.util.List;

/**
 * Created by Anna on 21.04.2017.
 */
public interface RouteDAO {

    void addRoute(Route route);

    List<Route> getAllRoute();

    List<Route> findRoute(String startpoint, String endpoint);
}
