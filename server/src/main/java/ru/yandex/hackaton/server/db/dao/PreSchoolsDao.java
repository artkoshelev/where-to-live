package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Elementary;
import ru.yandex.hackaton.server.db.model.PreSchool;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class PreSchoolsDao extends CrudDao<PreSchool> {

    @Inject
    public PreSchoolsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
