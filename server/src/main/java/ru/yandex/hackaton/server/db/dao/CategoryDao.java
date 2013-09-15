package ru.yandex.hackaton.server.db.dao;

import com.google.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import ru.yandex.hackaton.server.db.model.Category;
import ru.yandex.hackaton.server.db.model.Population;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: artkoshelev
 * Date: 14.09.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDao extends CrudDao<Category> {

    @Inject
    protected CategoryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Category> getSortedByName() {
        return list(criteria().addOrder(Order.asc("name")));
    }
}
