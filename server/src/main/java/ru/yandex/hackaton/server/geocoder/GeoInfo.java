package ru.yandex.hackaton.server.geocoder;

import ru.yandex.hackaton.server.db.model.Point;

/**
 * @author Sergey Polovko
 */
public class GeoInfo {

    private static final GeoInfo EMPTY = new GeoInfo("", new Point(0, 0));

    private final String address;
    private final Point point;


    public GeoInfo(String address, Point point) {
        this.address = address;
        this.point = point;
    }

    public static GeoInfo empty() {
        return EMPTY;
    }

    public String getAddress() {
        return address;
    }

    public Point getPoint() {
        return point;
    }
}
