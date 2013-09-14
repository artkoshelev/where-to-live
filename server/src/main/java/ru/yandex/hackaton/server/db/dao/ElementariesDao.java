package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.ChildPolyclinic;
import ru.yandex.hackaton.server.db.model.Elementary;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class ElementariesDao extends CrudDao<Elementary> {

    @Inject
    public ElementariesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
