package ru.yandex.hackaton.server.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.codahale.dropwizard.hibernate.UnitOfWork;

import ru.yandex.hackaton.server.db.dao.*;
import ru.yandex.hackaton.server.db.model.*;

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
    @Inject
    private ChildPolyclinicDao childPolyclinicDao;
    @Inject
    private ChildTeethPolyclinicDao childTeethPolyclinicDao;
    @Inject
    private CityPolyclinicDao cityPolyclinicDao;
    @Inject
    private ParksDao parksDao;

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

    @GET
    @UnitOfWork
    @Path("child_polyclinic")
    public List<ChildPolyclinic> getChildPolyclinic() {
        return childPolyclinicDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("child_teeth_polyclinic")
    public List<ChildTeethPolyclinic> getChildTeethPolyclinic() {
        return childTeethPolyclinicDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("city_polyclinic")
    public List<CityPolyclinic> getCityPolyclinic() {
        return cityPolyclinicDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("parks")
    public List<Parks> getParks() {
        return parksDao.findAll();
    }
}
