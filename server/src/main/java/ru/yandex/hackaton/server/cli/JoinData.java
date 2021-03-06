package ru.yandex.hackaton.server.cli;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.yandex.hackaton.server.WtlConfiguration;
import ru.yandex.hackaton.server.db.dao.*;
import ru.yandex.hackaton.server.db.model.CategoryInfo;
import ru.yandex.hackaton.server.db.model.District;
import ru.yandex.hackaton.server.geocoder.YandexGeocoder;
import ru.yandex.hackaton.server.geocoder.data.GeoInfo;
import ru.yandex.hackaton.server.geocoder.geo.DistrictBorder;
import ru.yandex.hackaton.server.geocoder.geo.Point;

/**
 * @author Sergey Polovko
 */
public class JoinData extends AbstractDbCommand {

    private static final Logger logger = LoggerFactory.getLogger(JoinData.class);

    private Map<Integer, DistrictBorder> districtsIds;

    @Inject
    private YandexGeocoder geocoder;
    @Inject
    private DistrictsDao districtsDao;
    @Inject
    private HospitalsDao hospitalsDao;
    @Inject
    private ChildPolyclinicDao childPolyclinicDao;
    @Inject
    private ChildTeethPolyclinicDao childTeethPolyclinicDao;
    @Inject
    private CityPolyclinicDao cityPolyclinicDao;
    @Inject
    private DispensaryDao dispensaryDao;
    @Inject
    private DrugsDao drugsDao;
    @Inject
    private ParksDao parksDao;
    @Inject
    private ElementariesDao elementariesDao;
    @Inject
    private NightSchoolsDao nightSchoolsDao;
    @Inject
    private PreSchoolsDao preSchoolsDao;
    @Inject
    private FountainDao fountainDao;
    @Inject
    private WiFiDao wiFiDao;
    @Inject
    private ShopsDao shopsDao;
    @Inject
    private HighSchoolsDao highSchoolsDao;
    @Inject
    private PiknikDao piknikDao;
    @Inject
    private BusStopsDao busStopsDao;
    @Inject
    private LibrariesDao librariesDao;

    public JoinData(Application<WtlConfiguration> application) {
        super(application, "join-data", "Joins loaded data with districts");
    }

    @Override
    protected void run(Environment environment, Namespace namespace, WtlConfiguration configuration) throws Exception {
        loadDistrictsIds();

//        joinToDistrictsFrom(hospitalsDao);
//        joinToDistrictsFrom(childPolyclinicDao);
//        joinToDistrictsFrom(childTeethPolyclinicDao);
//        joinToDistrictsFrom(cityPolyclinicDao);
//        joinToDistrictsFrom(dispensaryDao);
//        joinToDistrictsFrom(drugsDao);
//        joinToDistrictsFrom(parksDao);
//        joinToDistrictsFrom(elementariesDao);
//        joinToDistrictsFrom(nightSchoolsDao);
//        joinToDistrictsFrom(preSchoolsDao);
//        joinToDistrictsFrom(fountainDao);
//        joinToDistrictsFrom(wiFiDao);
//        joinToDistrictsFrom(shopsDao);
//        joinToDistrictsFrom(piknikDao);
//        joinToDistrictsFrom(highSchoolsDao);
//        joinToDistrictsFrom(busStopsDao);
//        joinToDistrictsFrom(librariesDao);
    }

    private void loadDistrictsIds() {
        doInSession(new Block() {
            public void apply() {
                districtsIds = new HashMap<>();

                for (District district : districtsDao.findAll()) {
                    districtsIds.put(district.getId(), new DistrictBorder(district.getWktLine()));
                }
            }
        });
    }

    private Integer findDistrictId(Point point) {
        for (Map.Entry<Integer, DistrictBorder> entry : districtsIds.entrySet()) {
            if (entry.getValue().contains(point)) return entry.getKey();
        }
        return null;
    }

    private <T extends CategoryInfo> void joinToDistrictsFrom(final CrudDao<T> dao) {
        doInSession(new Block() {
            public void apply() {
                for (T categoryInfo : dao.findAll()) {
                    // (1) geocode address -> point
                    GeoInfo geoInfo = geocoder.geocode(categoryInfo.getAddress());
                    if (geoInfo.isEmpty()) {
                        logger.info("Skip address: {}", categoryInfo.getAddress());
                        continue;
                    }

                    // (2) set location
                    Point point = geoInfo.getPoint();
                    logger.info("{} => {}", categoryInfo.getAddress(), point);
                    categoryInfo.setLocation(point.toWkt());

                    // (3) set district
                    Integer districtId = findDistrictId(point);
                    logger.info("{} => {}, {}", point, districtId);
                    categoryInfo.setDistrictId(districtId);

                    // (4) and save it
                    dao.save(categoryInfo);
                }
            }
        });
    }
}
