package ru.yandex.hackaton.server.db;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * @author Sergey Polovko
 */
public class DbModule extends AbstractModule {

    private final SessionFactoryHolder sessionFactoryHolder;


    public DbModule(SessionFactoryHolder sessionFactoryHolder) {
        this.sessionFactoryHolder = sessionFactoryHolder;
    }

    @Provides
    @Singleton
    public org.hibernate.SessionFactory sessionFactory() {
        return sessionFactoryHolder.getSessionFactory();
    }

    @Override
    protected void configure() {
    }
}
