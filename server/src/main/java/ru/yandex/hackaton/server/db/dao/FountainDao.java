package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.DistrictsSummary;
import ru.yandex.hackaton.server.db.model.Fountain;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class FountainDao extends CrudDao<Fountain> {

    @Inject
    public FountainDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
