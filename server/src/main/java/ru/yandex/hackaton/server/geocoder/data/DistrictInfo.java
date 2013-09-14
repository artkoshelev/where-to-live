package ru.yandex.hackaton.server.geocoder.data;

import ru.yandex.hackaton.server.geocoder.geo.Line;

/**
 * @author Sergey Polovko
 */
public class DistrictInfo {

    private final String name;
    private final Line borders;

    public DistrictInfo(String name, Line borders) {
        this.name = name;
        this.borders = borders;
    }

    public String getName() {
        return name;
    }

    public Line getBorders() {
        return borders;
    }
}
