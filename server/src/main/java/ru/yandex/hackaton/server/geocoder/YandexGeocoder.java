package ru.yandex.hackaton.server.geocoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ru.yandex.hackaton.server.geocoder.data.GeoInfo;
import ru.yandex.hackaton.server.geocoder.geo.Point;

/**
 * @author Sergey Polovko
 */
@Singleton
public class YandexGeocoder {

    private final DataHost<GeoInfo> dataHost;


    public YandexGeocoder() {
        dataHost = new DataHost<GeoInfo>("geocode-maps.yandex.ru") {
            @Override
            protected GeoInfo parseResponse(InputStream content, Charset charset) throws IOException {
                return parseGeoInfo(content, charset);
            }

            @Override
            protected GeoInfo emptyResponse(Charset charset) {
                return GeoInfo.empty();
            }
        };
    }

    private static GeoInfo parseGeoInfo(InputStream content, Charset charset) throws IOException {
        Document document = Jsoup.parse(content, charset.name(), "");
        Elements tags = document.getElementsByTag("pos");
        if (tags.isEmpty()) return GeoInfo.empty();

        String pos = tags.first().text();
        if (StringUtils.isBlank(pos)) return GeoInfo.empty();

        String address = document.getElementsByTag("AddressLine").text();
        Point point = Point.parseGml(pos);
        return new GeoInfo(address, point);
    }

    public GeoInfo geocode(String address) {
        return dataHost.get("/1.x/", "geocode", address);
    }

    public GeoInfo geocode(Point point) {
        return dataHost.get("/1.x/", "geocode", String.format("%d,%d", point.getLon(), point.getLat()));
    }
}
