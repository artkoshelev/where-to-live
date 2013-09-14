package ru.yandex.hackaton.server.geocoder.geo;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

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

    public double x() {
        return lat;
    }

    public double y() {
        return lon;
    }

    public Point minus(Point other) {
        return new Point(lat - other.lat, lon - other.lon);
    }

    public Point plus(Point other) {
        return new Point(lat + other.lat, lon + other.lon);
    }

    public Point mul(double k) {
        return new Point(lat * k, lon * k);
    }

    public double dot(Point other) {
        return lat * other.lat + lon * other.lon;
    }

    public String toGml() {
        return String.format(Locale.ENGLISH, "%f %f", lon, lat);
    }

    public static Point parseGml(String gmlPos) {
        String[] coordinates = StringUtils.split(gmlPos, " ");
        Validate.isTrue(coordinates.length == 2);
        return parseCoordinates(coordinates[1], coordinates[0]);
    }

    public String toWkt() {
        return String.format(Locale.ENGLISH, "%f %f", lat, lon);
    }

    public static Point parseWkt(String wktPart) {
        String[] coordinates = StringUtils.split(wktPart, " ");
        Validate.isTrue(coordinates.length == 2);
        return parseCoordinates(coordinates[0], coordinates[1]);
    }

    public static Point parseCoordinates(String latStr, String lonStr) {
        try {
            double lat = Double.parseDouble(latStr);
            double lon = Double.parseDouble(lonStr);
            return new Point(lat, lon);
        } catch (NumberFormatException e) {
            throw new RuntimeException("coordinates parse error -- [" + latStr + " " + lonStr + "] -- " + e.toString(), e);
        }
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
