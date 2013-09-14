package ru.yandex.hackaton.server.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.dropwizard.hibernate.UnitOfWork;

import ru.yandex.hackaton.server.db.dao.DistrictsDao;
import ru.yandex.hackaton.server.db.model.District;

/**
 * @author Sergey Polovko
 */
@Path("districts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DistrictsResource {

    @Inject
    private DistrictsDao districtsDao;

    @GET
    @UnitOfWork
    public List<District> getOperations() {
        return districtsDao.findAll();
    }
}
