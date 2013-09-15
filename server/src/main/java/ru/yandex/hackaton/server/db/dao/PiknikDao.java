package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Drugs;
import ru.yandex.hackaton.server.db.model.Piknik;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class PiknikDao extends CrudDao<Piknik> {

    @Inject
    public PiknikDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
