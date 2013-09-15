package ru.yandex.hackaton.server.db.dao;

import org.hibernate.SessionFactory;
import ru.yandex.hackaton.server.db.model.Drugs;
import ru.yandex.hackaton.server.db.model.Library;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Polovko
 */
@Singleton
public class LibrariesDao extends CrudDao<Library> {

    @Inject
    public LibrariesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
