package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.BusStop;
import ru.yandex.hackaton.server.db.model.HighSchool;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class BusStopsDao extends CrudDao<BusStop> {

    @Inject
    public BusStopsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
