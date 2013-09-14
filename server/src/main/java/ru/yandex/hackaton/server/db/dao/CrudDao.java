package ru.yandex.hackaton.server.db.dao;

import java.util.List;

import com.codahale.dropwizard.hibernate.AbstractDAO;
import com.google.common.base.Optional;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ru.yandex.hackaton.server.db.model.BaseModel;

/**
 * @author Sergey Polovko
 */
public class CrudDao<T extends BaseModel> extends AbstractDAO<T> {

    protected CrudDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<T> findAll() {
        return list(criteria());
    }

    public Optional<T> findById(int id) {
        return Optional.fromNullable(findOne(criteria().add(Restrictions.eq("id", id))));
    }

    public void save(T entity) {
        currentSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        currentSession().delete(entity);
    }

    protected T findOne(Criteria criteria) {
        return uniqueResult(criteria.setMaxResults(1));
    }

    public int count() {
        Object count = criteria().setProjection(Projections.rowCount()).uniqueResult();
        return count != null ? ((Number) count).intValue() : 0;
    }
}
