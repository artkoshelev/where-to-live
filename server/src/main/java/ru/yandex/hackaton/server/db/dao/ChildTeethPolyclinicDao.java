package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.ChildPolyclinic;
import ru.yandex.hackaton.server.db.model.ChildTeethPolyclinic;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class ChildTeethPolyclinicDao extends CrudDao<ChildTeethPolyclinic> {

    @Inject
    public ChildTeethPolyclinicDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
