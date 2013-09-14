package ru.yandex.hackaton.server.resources;

import com.codahale.dropwizard.hibernate.UnitOfWork;
import ru.yandex.hackaton.server.db.dao.CategoryDao;
import ru.yandex.hackaton.server.db.dao.PopulationDao;
import ru.yandex.hackaton.server.db.model.Category;
import ru.yandex.hackaton.server.db.model.Population;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Artem Koshelev
 */
@Path("categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    private CategoryDao categoryDao;

    @GET
    @UnitOfWork
    public List<Category> getOperations() {
        return categoryDao.findAll();
    }
}
