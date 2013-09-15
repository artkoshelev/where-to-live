package ru.yandex.hackaton.server.cli;

import javax.inject.Inject;

import com.codahale.dropwizard.Application;
import com.codahale.dropwizard.cli.EnvironmentCommand;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;

import ru.yandex.hackaton.server.WtlConfiguration;

/**
 * @author Sergey Polovko
 */
public abstract class AbstractDbCommand extends EnvironmentCommand<WtlConfiguration> {

    @Inject
    private SessionFactory sessionFactory;

    /**
     * Creates a new environment command.
     *
     * @param application the application providing this command
     * @param name        the name of the command, used for command line invocation
     * @param description a description of the command's purpose
     */
    protected AbstractDbCommand(Application<WtlConfiguration> application, String name, String description) {
        super(application, name, description);
    }

    protected void doInSession(Block block) {
        final Session session = sessionFactory.openSession();
        try {
            configureSession(session);
            ManagedSessionContext.bind(session);
            beginTransaction(session);
            try {
                block.apply();
                commitTransaction(session);
            } catch (Exception e) {
                rollbackTransaction(session);
                this.<RuntimeException>rethrow(e);
            }
        } finally {
            session.close();
            ManagedSessionContext.unbind(sessionFactory);
        }
    }

    private void beginTransaction(Session session) {
        session.beginTransaction();
    }

    private void configureSession(Session session) {
        session.setDefaultReadOnly(false);
        session.setCacheMode(CacheMode.NORMAL);
        session.setFlushMode(FlushMode.AUTO);
    }

    private void rollbackTransaction(Session session) {
        final Transaction txn = session.getTransaction();
        if (txn != null && txn.isActive()) {
            txn.rollback();
        }
    }

    private void commitTransaction(Session session) {
        final Transaction txn = session.getTransaction();
        if (txn != null && txn.isActive()) {
            txn.commit();
        }
    }

    @SuppressWarnings("unchecked")
    private <E extends Exception> void rethrow(Exception e) throws E {
        throw (E) e;
    }

    /**
     * @author Sergey Polovko
     */
    public static interface Block {
        void apply();
    }
}
