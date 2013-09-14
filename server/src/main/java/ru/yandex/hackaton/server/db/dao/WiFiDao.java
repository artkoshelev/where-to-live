package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Fountain;
import ru.yandex.hackaton.server.db.model.WiFi;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class WiFiDao extends CrudDao<WiFi> {

    @Inject
    public WiFiDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
