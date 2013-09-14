package ru.yandex.hackaton.server.db.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.hibernate.SessionFactory;

import ru.yandex.hackaton.server.db.model.ChildPolyclinic;

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
