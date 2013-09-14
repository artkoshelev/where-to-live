package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.DistrictsSummary;
import ru.yandex.hackaton.server.db.model.Elementary;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class DistrictsSummaryDao extends CrudDao<DistrictsSummary> {

    @Inject
    public DistrictsSummaryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
