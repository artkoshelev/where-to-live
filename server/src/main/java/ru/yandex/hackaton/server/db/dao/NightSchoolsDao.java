package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Elementary;
import ru.yandex.hackaton.server.db.model.NightSchool;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class NightSchoolsDao extends CrudDao<NightSchool> {

    @Inject
    public NightSchoolsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
