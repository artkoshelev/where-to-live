package ru.yandex.hackaton.server.db.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.hibernate.SessionFactory;

import ru.yandex.hackaton.server.db.model.District;

/**
 * @author Sergey Polovko
 */
@Singleton
public class DistrictsDao extends CrudDao<District> {

    @Inject
    public DistrictsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
