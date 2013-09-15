package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Drugs;
import ru.yandex.hackaton.server.db.model.Presets;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class PresetsDao extends CrudDao<Presets> {

    @Inject
    public PresetsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
