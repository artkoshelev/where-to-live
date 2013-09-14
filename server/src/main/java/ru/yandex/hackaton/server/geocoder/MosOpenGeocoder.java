package ru.yandex.hackaton.server.geocoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.yandex.hackaton.server.geocoder.data.DistrictInfo;
import ru.yandex.hackaton.server.geocoder.geo.Line;
import ru.yandex.hackaton.server.geocoder.geo.Point;

/**
 * @author Sergey Polovko
 */
@Singleton
public class MosOpenGeocoder {

    private final DataHost<DistrictInfo> dataHost = new DataHost<DistrictInfo>("mosopen.ru") {
        @Override
        protected DistrictInfo parseResponse(InputStream content, Charset charset) throws IOException {
            System.out.println(content);
            return null;
        }

        @Override
        protected DistrictInfo emptyResponse(Charset charset) {
            return null;
        }
    };

    public DistrictInfo geocode(String districtName) {
        String translitName = TransLiterator.translitRustoEng(districtName);
        return dataHost.get("/public/ymapsml.php", "p", String.format("region/%s/map_xml", translitName));
    }

    public DistrictInfo parseDistrictInfo(String xml, Charset charset, String districtName) throws IOException {
        Document document = Jsoup.parse(new File(xml), charset.name());
        String name = districtName;
        List<Point> points = new ArrayList<Point>();
        for (Element pos : document.getElementsByTag("gml:pos")) {
             points.add(new Point(
                     Double.valueOf(pos.text().split(" ")[0]),
                     Double.valueOf(pos.text().split(" ")[1])));
        }
        Line borders = new Line(points);
        DistrictInfo result = new DistrictInfo(name, borders);
        return result;
    }
}
