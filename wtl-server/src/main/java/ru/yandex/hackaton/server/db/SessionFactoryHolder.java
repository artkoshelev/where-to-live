package ru.yandex.hackaton.server.db;

import org.hibernate.SessionFactory;

/**
 * @author Sergey Polovko
 */
public interface SessionFactoryHolder {

    SessionFactory getSessionFactory();
}
