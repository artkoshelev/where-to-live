package ru.yandex.hackaton.server.geocoder.geo;

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Sergey Polovko
 */
public class BoundingBox extends Rectangle2D.Double {

    public BoundingBox(double lat1, double lon1, double lat2, double lon2) {
        super(lat1, lon1, lat2 - lat1, lon2 - lon1);
    }

    public Point from() {
        return new Point(getMinX(), getMinY());
    }

    public Point to() {
        return new Point(getMaxX(), getMaxY());
    }

    public Point center() {
        return new Point(getCenterX(), getCenterY());
    }

    public Point span() {
        return new Point(getWidth(), getHeight());
    }

    public static BoundingBox from(List<Point> points) {
        return withBorder(points, 0);
    }

    public static BoundingBox withBorder(List<Point> points, double delta) {
        double latMin = java.lang.Double.MAX_VALUE, latMax = java.lang.Double.MIN_VALUE;
        double lonMin = java.lang.Double.MAX_VALUE, lonMax = java.lang.Double.MIN_VALUE;

        for (Point point : points) {
            latMin = Math.min(latMin, point.getLat());
            latMax = Math.max(latMax, point.getLat());

            lonMin = Math.min(lonMin, point.getLat());
            lonMax = Math.max(lonMax, point.getLat());
        }

        return new BoundingBox(latMin - delta, lonMin - delta, latMax + delta, lonMax + delta);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public boolean contains(Point point) {
        return contains(point.x(), point.y());
    }
}
