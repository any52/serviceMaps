package ru.sample2.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Anna on 19.04.2017.
 */
public class Route {

    private String startPoint;
    private String endPoint;

    public Route() {
    }
    @JsonCreator
    public Route(@JsonProperty("startPoint")String startPoint, @JsonProperty("endPoint")String endPoint) {

        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (!startPoint.equals(route.startPoint)) return false;
        return endPoint.equals(route.endPoint);

    }

    @Override
    public int hashCode() {
        int result = startPoint.hashCode();
        result = 31 * result + endPoint.hashCode();
        return result;
    }
}
