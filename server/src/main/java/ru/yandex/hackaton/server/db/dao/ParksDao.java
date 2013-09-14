package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Hospitals;
import ru.yandex.hackaton.server.db.model.Parks;

import javax.inject.Inject;
import javax.inject.Singleton;

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
