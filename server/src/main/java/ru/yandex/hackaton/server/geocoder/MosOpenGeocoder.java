package ru.yandex.hackaton.server.geocoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.inject.Singleton;

import ru.yandex.hackaton.server.geocoder.data.DistrictInfo;

/**
 * @author Sergey Polovko
 */
@Singleton
public class MosOpenGeocoder {

    private final DataHost<DistrictInfo> dataHost = new DataHost<DistrictInfo>("mosopen.ru") {
        @Override
        protected DistrictInfo parseResponse(InputStream content, Charset charset) throws IOException {
            return null;
        }

        @Override
        protected DistrictInfo emptyResponse(Charset charset) {
            return null;
        }
    };

    public DistrictInfo geocode(String districtName) {
        String translitName = districtName; // TODO: translit
        return dataHost.get("/public/ymapsml.php", "p", String.format("region/%s/map_xml", translitName));
    }
}
