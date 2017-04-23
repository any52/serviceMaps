package ru.sample2.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 19.04.2017.
 */
public class RouteDTO {
    private List<Route> routes =  new ArrayList<>();

    public RouteDTO() {
    }
    @JsonCreator
    public RouteDTO(@JsonProperty("routes")List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }




}
