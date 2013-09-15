package ru.yandex.hackaton.server.geocoder.geo;

import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * @author Sergey Polovko
 */
public class DistrictBorder {

    private final Area area;
    private final Line line;


    public DistrictBorder(Line line) {
        this.area = new Area(toPath2D(line));
        this.line = line;
    }

    private static Path2D toPath2D(Line line) {
        List<Point> f = line.points();

        Path2D path = new Path2D.Double();
        path.moveTo(f.get(0).x(), f.get(0).y());
        for (Point point : f.subList(1, f.size())) {
            path.lineTo(point.x(), point.y());
        }
        path.closePath();
        return path;
    }

    public boolean intersect(Rectangle2D r) {
        return area.intersects(r);
    }

    public boolean contains(Point point) {
        return area.contains(point.x(), point.y());
    }

    public BoundingBox getBBox() {
        return BoundingBox.from(this.line.points());
    }
}
