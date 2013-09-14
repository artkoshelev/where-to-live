package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.HighSchool;
import ru.yandex.hackaton.server.db.model.PreSchool;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class HighSchoolsDao extends CrudDao<HighSchool> {

    @Inject
    public HighSchoolsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
