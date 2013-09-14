package ru.yandex.hackaton.server.resources;

import com.codahale.dropwizard.hibernate.UnitOfWork;
import ru.yandex.hackaton.server.db.dao.*;
import ru.yandex.hackaton.server.db.model.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Sergey Polovko
 */
@Path("presets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PresetResource {

    @Inject
    private PresetsDao presetsDao;

    @GET
    @UnitOfWork
    public List<Presets> getPresets() {
        return presetsDao.findAll();
    }
}
