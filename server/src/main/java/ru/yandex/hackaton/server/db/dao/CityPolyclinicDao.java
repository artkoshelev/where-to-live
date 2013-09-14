package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.ChildPolyclinic;
import ru.yandex.hackaton.server.db.model.CityPolyclinic;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class CityPolyclinicDao extends CrudDao<CityPolyclinic> {

    @Inject
    public CityPolyclinicDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
