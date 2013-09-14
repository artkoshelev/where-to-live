package ru.yandex.hackaton.server.cli;

import javax.inject.Inject;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;

import ru.yandex.hackaton.server.WtlConfiguration;
import ru.yandex.hackaton.server.db.dao.DistrictsDao;
import ru.yandex.hackaton.server.db.model.District;
import ru.yandex.hackaton.server.geocoder.MosOpenGeocoder;
import ru.yandex.hackaton.server.geocoder.data.DistrictInfo;

/**
 * @author Sergey Polovko
 */
public class LoadDistrictsCoordinates extends AbstractDbCommand {

    @Inject
    private DistrictsDao districtsDao;
    @Inject
    private MosOpenGeocoder mosOpenGeocoder;


    public LoadDistrictsCoordinates(Application<WtlConfiguration> application) {
        super(application, "load-coords", "Loads districts coordinates");
    }

    @Override
    protected void run(Environment environment, Namespace namespace, WtlConfiguration configuration) throws Exception {
        doInSession(new Block() {
            public void apply() {
                for (District district : districtsDao.findAll()) {
                    DistrictInfo districtInfo = mosOpenGeocoder.geocode(district.getName());
                    district.setBorders(districtInfo.getBorders().toWkt());
                    districtsDao.save(district);
                }
            }
        });
    }
}
