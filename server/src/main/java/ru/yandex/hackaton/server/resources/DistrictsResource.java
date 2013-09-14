package ru.yandex.hackaton.server.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.codahale.dropwizard.hibernate.UnitOfWork;

import ru.yandex.hackaton.server.db.dao.DistrictsDao;
import ru.yandex.hackaton.server.db.dao.HospitalsDao;
import ru.yandex.hackaton.server.db.model.District;
import ru.yandex.hackaton.server.db.model.Hospitals;

/**
 * @author Sergey Polovko
 */
@Path("districts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DistrictsResource {

    @Inject
    private DistrictsDao districtsDao;

    @Inject
    private HospitalsDao hospitalsDao;

    @GET
    @UnitOfWork
    public List<District> getOperations() {
        return districtsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("hospitals")
    public List<Hospitals> getHospitals() {
        return hospitalsDao.findAll();
    }
}
