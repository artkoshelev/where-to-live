package ru.yandex.hackaton.server.db.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sergey Polovko
 */
public class Point {

    @JsonProperty("x")
    private final double lon;
    @JsonProperty("y")
    private final double lat;


    @JsonCreator
    public Point(@JsonProperty("x") double lon, @JsonProperty("y") double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Point");
        sb.append("{lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append('}');
        return sb.toString();
    }
}
