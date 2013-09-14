package ru.yandex.hackaton.server.cli;

import javax.inject.Inject;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.cli.EnvironmentCommand;
import com.codahale.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.yandex.hackaton.server.WtlConfiguration;
import ru.yandex.hackaton.server.db.dao.DistrictsDao;
import ru.yandex.hackaton.server.geocoder.GeoInfo;
import ru.yandex.hackaton.server.geocoder.YandexGeocoder;

/**
 * @author Sergey Polovko
 */
public class JoinData extends EnvironmentCommand<WtlConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(JoinData.class);

    @Inject
    private YandexGeocoder geocoder;
    @Inject
    private DistrictsDao districtsDao;


    public JoinData(Application<WtlConfiguration> application) {
        super(application, "join-data", "Joins loaded data with districts");
    }

    @Override
    protected void run(Environment environment, Namespace namespace, WtlConfiguration configuration) throws Exception {
        GeoInfo geoInfo = geocoder.geocode("Москва, Льва Толстого, 16");
        logger.info(geoInfo.toString());

        // wait outputting last logged message
        Thread.sleep(3000);
    }
}
