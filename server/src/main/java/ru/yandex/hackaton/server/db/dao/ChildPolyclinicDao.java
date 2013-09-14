package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.ChildPolyclinic;
import ru.yandex.hackaton.server.db.model.Hospitals;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class ChildPolyclinicDao extends CrudDao<ChildPolyclinic> {

    @Inject
    public ChildPolyclinicDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
