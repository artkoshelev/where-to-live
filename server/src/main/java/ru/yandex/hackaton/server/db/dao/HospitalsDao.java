package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.District;
import ru.yandex.hackaton.server.db.model.DistrictInfo;
import ru.yandex.hackaton.server.db.model.Hospitals;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class HospitalsDao extends CrudDao<Hospitals> {

    @Inject
    public HospitalsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
