package ru.sample2.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Anna on 19.04.2017.
 */
public class Route {

    private String startPoint;
    private String endPoint;
    private String intermediatePoint1;
    private String intermediatePoint2;
    private String dayWeek;
    private String time;

    public Route() {
    }
    @JsonCreator
    public Route(@JsonProperty("startPoint")String startPoint,
                 @JsonProperty("endPoint")String endPoint,
                 @JsonProperty ("intermediatePoint1") String intermediatePoint1,
                 @JsonProperty ("intermediatePoint2") String intermediatePoint2,
                 @JsonProperty ("dayWeek") String dayweek,
                 @JsonProperty ("time") String time) {

        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.intermediatePoint1 = intermediatePoint1;
        this.intermediatePoint2 = intermediatePoint2;
        this.dayWeek = dayweek;
        this.time = time;
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

    public String getIntermediatePoint1() {
        return intermediatePoint1;
    }

    public void setIntermediatePoint1(String intermediatePoint1) {
        this.intermediatePoint1 = intermediatePoint1;
    }

    public String getIntermediatePoint2() {
        return intermediatePoint2;
    }

    public void setIntermediatePoint2(String intermediatePoint2) {
        this.intermediatePoint2 = intermediatePoint2;
    }

    public String getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (!startPoint.equals(route.startPoint)) return false;
        if (!endPoint.equals(route.endPoint)) return false;
        if (!intermediatePoint1.equals(route.intermediatePoint1)) return false;
        if (!intermediatePoint2.equals(route.intermediatePoint2)) return false;
        if (!dayWeek.equals(route.dayWeek)) return false;
        return time.equals(route.time);

    }

    @Override
    public int hashCode() {
        int result = startPoint.hashCode();
        result = 31 * result + endPoint.hashCode();
        result = 31 * result + intermediatePoint1.hashCode();
        result = 31 * result + intermediatePoint2.hashCode();
        result = 31 * result + dayWeek.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", intermediatePoint1='" + intermediatePoint1 + '\'' +
                ", intermediatePoint2='" + intermediatePoint2 + '\'' +
                ", dayWeek='" + dayWeek + '\'' +
                ", time='" + time + '\'' +
                '}'+ "\n";
    }
}
