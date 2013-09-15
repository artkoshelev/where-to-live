package ru.yandex.hackaton.server.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.codahale.dropwizard.hibernate.UnitOfWork;

import com.google.common.base.Optional;
import ru.yandex.hackaton.server.db.dao.ChildPolyclinicDao;
import ru.yandex.hackaton.server.db.dao.ChildTeethPolyclinicDao;
import ru.yandex.hackaton.server.db.dao.CityPolyclinicDao;
import ru.yandex.hackaton.server.db.dao.DispensaryDao;
import ru.yandex.hackaton.server.db.dao.DistrictsDao;
import ru.yandex.hackaton.server.db.dao.DistrictsSummaryDao;
import ru.yandex.hackaton.server.db.dao.DrugsDao;
import ru.yandex.hackaton.server.db.dao.ElementariesDao;
import ru.yandex.hackaton.server.db.dao.FountainDao;
import ru.yandex.hackaton.server.db.dao.HospitalsDao;
import ru.yandex.hackaton.server.db.dao.NightSchoolsDao;
import ru.yandex.hackaton.server.db.dao.ParksDao;
import ru.yandex.hackaton.server.db.dao.PreSchoolsDao;
import ru.yandex.hackaton.server.db.dao.WiFiDao;
import ru.yandex.hackaton.server.db.model.ChildPolyclinic;
import ru.yandex.hackaton.server.db.model.ChildTeethPolyclinic;
import ru.yandex.hackaton.server.db.model.CityPolyclinic;
import ru.yandex.hackaton.server.db.model.Dispensary;
import ru.yandex.hackaton.server.db.model.District;
import ru.yandex.hackaton.server.db.model.DistrictsSummary;
import ru.yandex.hackaton.server.db.model.Drugs;
import ru.yandex.hackaton.server.db.model.Elementary;
import ru.yandex.hackaton.server.db.model.Fountain;
import ru.yandex.hackaton.server.db.model.Hospital;
import ru.yandex.hackaton.server.db.model.NightSchool;
import ru.yandex.hackaton.server.db.model.Parks;
import ru.yandex.hackaton.server.db.model.PreSchool;
import ru.yandex.hackaton.server.db.model.WiFi;
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
    private ShopsDao shopsDao;
    @Inject
    private DistrictsSummaryDao districtsSummaryDao;
    @Inject
    private PiknikDao piknikDao;
    @Inject
    private HighSchoolsDao highSchoolsDao;
    @Inject
    private BusStopsDao busStopsDao;
    @Inject
    private LibrariesDao librariesDao;

    @GET
    @UnitOfWork
    public List<District> getOperations() {
        return districtsDao.findAll();
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    public District getDistrict(@PathParam("id") int id ) {
        Optional<District> byId = districtsDao.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new WebApplicationException(404);
    }

    @GET
    @Path("{id}/polygon")
    @UnitOfWork
    public DistrictPolygon getDistrictWrapper(@PathParam("id") int id ) {
        Optional<District> byId = districtsDao.findById(id);
        if (byId.isPresent()) {
            return new DistrictPolygon(byId.get());
        }
        throw new WebApplicationException(404);
    }

    @GET
    @Path("{ids}/polygons")
    @UnitOfWork
    public Object getDistrictWrapper(@PathParam("ids") String ids ) {
        String[] list = ids.split(",");
        if (list.length == 1) {
            return getDistrictWrapper(Integer.parseInt(list[0]));
        } else {
            List<DistrictPolygon> res = new ArrayList<>();
            for (String id : list) {
                res.add(getDistrictWrapper(Integer.parseInt(id)));
            }
            return res;
        }
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
        List<DistrictsSummary> res = districtsSummaryDao.find(params);
        return res;
    }

    @GET
    @UnitOfWork
    @Path("hospitals")
    public List<Hospital> getHospitals() {
        return hospitalsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("shops")
    public List<Shops> getShops() {
        return shopsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("childpolyclinic")
    public List<ChildPolyclinic> getChildPolyclinic() {
        return childPolyclinicDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("childteethpolyclinic")
    public List<ChildTeethPolyclinic> getChildTeethPolyclinic() {
        return childTeethPolyclinicDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("citypolyclinic")
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
    @Path("nightschools")
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

    @GET
    @UnitOfWork
    @Path("piknik")
    public List<Piknik> getPiknik() {
        return piknikDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("highschool")
    public List<HighSchool> getHighSchool() {
        return highSchoolsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("busstops")
    public List<BusStop> getBusStops() {
        return busStopsDao.findAll();
    }

    @GET
    @UnitOfWork
    @Path("libraries")
    public List<Library> getLibraries() {
        return librariesDao.findAll();
    }
}
