package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Dispensary;
import ru.yandex.hackaton.server.db.model.Drugs;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class DrugsDao extends CrudDao<Drugs> {

    @Inject
    public DrugsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
