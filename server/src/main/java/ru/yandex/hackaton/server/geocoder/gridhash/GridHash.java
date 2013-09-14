package ru.yandex.hackaton.server.geocoder.gridhash;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.yandex.hackaton.server.geocoder.geo.BoundingBox;
import ru.yandex.hackaton.server.geocoder.geo.DistrictBorder;
import ru.yandex.hackaton.server.geocoder.geo.Point;

/**
 * @author Sergey Polovko
 */
public class GridHash<T> {

    private final Map<Long, Set<T>> hash = new HashMap<>();

    private final Point base;
    private final double dx;
    private final double dy;


    public GridHash(Point base, double dx, double dy) {
        this.base = base;
        this.dx = dx;
        this.dy = dy;
    }

    public void add(DistrictBorder key, T value) {
        BoundingBox box = key.getBBox();
        List<Cell> cellsRange = cellsRange(new Cell(box.from()), new Cell(box.to()));
        for (Cell c : cellsRange) {
            if (key.intersect(c.toRectangle())) {
                Set<T> val = hash.get(c.toIndex());
                if (val == null) {
                    val = new HashSet<>();
                    hash.put(c.toIndex(), val);
                }
                val.add(value);
            }
        }
    }

    public Set<T> getNearby(Point p) {
        Set<T> value = hash.get(new Cell(p).toIndex());
        return value == null ? Collections.<T>emptySet() : value;
    }

    private List<Cell> cellsRange(Cell from, Cell to) {
        List<Cell> cells = new ArrayList<>(100);
        for (int i = from.x; i <= to.x; ++i) {
            for (int j = from.y; j <= to.y; ++j) {
                cells.add(new Cell(i, j));
            }
        }
        return cells;
    }

    private class Cell {
        private final int x;
        private final int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Cell(Point p) {
            this((int) (p.minus(base).getLat() / dx), (int) (p.minus(base).getLon() / dy));
        }

        public long toIndex() {
            return (long) x << 32 | y;
        }

        public Rectangle2D toRectangle() {
            return new Rectangle2D.Double(x * dx + base.x(), y * dy + base.y(), dx, dy);
        }
    }
}
