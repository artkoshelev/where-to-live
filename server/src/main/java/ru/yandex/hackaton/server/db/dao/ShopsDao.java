package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Parks;
import ru.yandex.hackaton.server.db.model.Shops;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class ShopsDao extends CrudDao<Shops> {

    @Inject
    public ShopsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
