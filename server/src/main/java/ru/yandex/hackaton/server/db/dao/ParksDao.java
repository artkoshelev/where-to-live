package ru.yandex.hackaton.server.db.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.hibernate.SessionFactory;

import ru.yandex.hackaton.server.db.model.Parks;

/**
 * @author Sergey Polovko
 */
@Singleton
public class ParksDao extends CrudDao<Parks> {

    @Inject
    public ParksDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
