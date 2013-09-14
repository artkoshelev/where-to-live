package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.CityPolyclinic;
import ru.yandex.hackaton.server.db.model.Dispensary;
import ru.yandex.hackaton.server.db.model.District;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class DispensaryDao extends CrudDao<Dispensary> {

    @Inject
    public DispensaryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
