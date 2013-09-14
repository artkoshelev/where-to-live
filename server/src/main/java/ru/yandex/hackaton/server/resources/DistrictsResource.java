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
    private DispensaryDao dispensaryDao;
    @Inject
    private DrugsDao drugsDao;
    @Inject
    private ParksDao parksDao;
    @Inject
    private ElementariesDao elementariesDao;
    @Inject
    private NightSchoolsDao nightSchoolsDao;
    @Inject
    private PreSchoolsDao preSchoolsDao;
    @Inject
    private FountainDao fountainDao;
    @Inject
    private WiFiDao wiFiDao;
    @Inject
    private DistrictsSummaryDao districtsSummaryDao;

    @GET
    @UnitOfWork
    public List<District> getOperations() {
        return districtsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("summary")
    public List<DistrictsSummary> getSummary() {
        return districtsSummaryDao.findAll();
    }

    @POST
    @UnitOfWork
    @Path("search")
    public List<DistrictsSummary> getSearchResult(SearchParams params) {
        System.out.println(params.getParams());
        return districtsSummaryDao.findAll();
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

    @GET
    @UnitOfWork
    @Path("dispensary")
    public List<Dispensary> getDispensary() {
        return dispensaryDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("drugs")
    public List<Drugs> getDrugs() {
        return drugsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("elementaries")
    public List<Elementary> getElementaries() {
        return elementariesDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("night_schools")
    public List<NightSchool> getNightSchools() {
        return nightSchoolsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("preschool")
    public List<PreSchool> getPreSchools() {
        return preSchoolsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("wifi")
    public List<WiFi> getWiFi() {
        return wiFiDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("fountains")
    public List<Fountain> getFountains() {
        return fountainDao.findAll();
    }
}
