package ru.yandex.hackaton.server.cli;

import java.util.Set;

import javax.inject.Inject;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.yandex.hackaton.server.WtlConfiguration;
import ru.yandex.hackaton.server.db.dao.ChildPolyclinicDao;
import ru.yandex.hackaton.server.db.dao.ChildTeethPolyclinicDao;
import ru.yandex.hackaton.server.db.dao.CityPolyclinicDao;
import ru.yandex.hackaton.server.db.dao.CrudDao;
import ru.yandex.hackaton.server.db.dao.DispensaryDao;
import ru.yandex.hackaton.server.db.dao.DistrictsDao;
import ru.yandex.hackaton.server.db.dao.DrugsDao;
import ru.yandex.hackaton.server.db.dao.ElementariesDao;
import ru.yandex.hackaton.server.db.dao.FountainDao;
import ru.yandex.hackaton.server.db.dao.HospitalsDao;
import ru.yandex.hackaton.server.db.dao.NightSchoolsDao;
import ru.yandex.hackaton.server.db.dao.ParksDao;
import ru.yandex.hackaton.server.db.dao.PreSchoolsDao;
import ru.yandex.hackaton.server.db.dao.WiFiDao;
import ru.yandex.hackaton.server.db.model.CategoryInfo;
import ru.yandex.hackaton.server.db.model.District;
import ru.yandex.hackaton.server.geocoder.YandexGeocoder;
import ru.yandex.hackaton.server.geocoder.data.GeoInfo;
import ru.yandex.hackaton.server.geocoder.geo.DistrictBorder;
import ru.yandex.hackaton.server.geocoder.geo.Point;
import ru.yandex.hackaton.server.geocoder.gridhash.GridHash;

/**
 * @author Sergey Polovko
 */
public class JoinData extends AbstractDbCommand {

    private static final Logger logger = LoggerFactory.getLogger(JoinData.class);

    private final GridHash<District> districtsGeoHash = new GridHash<>(new Point(0, 0), 0.1, 0.1);

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


    public JoinData(Application<WtlConfiguration> application) {
        super(application, "join-data", "Joins loaded data with districts");
    }

    @Override
    protected void run(Environment environment, Namespace namespace, WtlConfiguration configuration) throws Exception {
        loadDistrictsGridHash();

        joinToDistrictsFrom(hospitalsDao);
        joinToDistrictsFrom(childPolyclinicDao);
        joinToDistrictsFrom(childTeethPolyclinicDao);
        joinToDistrictsFrom(cityPolyclinicDao);
        joinToDistrictsFrom(dispensaryDao);
        joinToDistrictsFrom(drugsDao);
        joinToDistrictsFrom(parksDao);
        joinToDistrictsFrom(elementariesDao);
        joinToDistrictsFrom(nightSchoolsDao);
        joinToDistrictsFrom(preSchoolsDao);
        joinToDistrictsFrom(fountainDao);
        joinToDistrictsFrom(wiFiDao);
    }

    private void loadDistrictsGridHash() {
        for (District district : districtsDao.findAll()) {
            districtsGeoHash.add(new DistrictBorder(district.getWktLine()), district);
        }
    }

    private <T extends CategoryInfo> void joinToDistrictsFrom(final CrudDao<T> dao) {
        doInSession(new Block() {
            public void apply() {
                for (T categoryInfo : dao.findAll()) {
                    GeoInfo geoInfo = geocoder.geocode(categoryInfo.getAddress());
                    Set<District> districts = districtsGeoHash.getNearby(geoInfo.getPoint());
                    Validate.isTrue(districts.size() == 1, "there too many districts for point " + geoInfo);
                    categoryInfo.setDistrictId(districts.iterator().next().getId());
                    dao.save(categoryInfo);
                }
            }
        });
    }
}
