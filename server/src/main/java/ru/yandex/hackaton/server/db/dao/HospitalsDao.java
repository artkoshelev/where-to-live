package ru.yandex.hackaton.server.db.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.hibernate.SessionFactory;

import ru.yandex.hackaton.server.db.model.Hospital;

/**
 * @author Sergey Polovko
 */
@Singleton
public class HospitalsDao extends CrudDao<Hospital> {

    @Inject
    public HospitalsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
