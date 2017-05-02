package ru.sample2.server.service;

import ru.sample2.shared.RouteDTO;

/**
 * Created by Anna on 19.04.2017.
 */
public interface RouteRepository {
    RouteDTO addRoute(String startPoint, String endPoint,
                      String intermediatePoint1,
                      String intermediatePoint2,
                      String dayweek,
                      String time);
    RouteDTO sendRoute();

    RouteDTO deleteRoute(int id);

    RouteDTO findRoutes(String startPoint, String endPoint);
}
