package ru.sample2.server;

import ru.sample2.shared.Route;
import ru.sample2.shared.RouteDTO;

/**
 * Created by Anna on 19.04.2017.
 */
public interface RouteRepository {
    RouteDTO sendRoute(Route route);
}
