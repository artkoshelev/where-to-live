package ru.yandex.hackaton.wheretolive.server.responses;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.ResponseCache;

import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtilsImpl;
import ru.yandex.hackaton.wheretolive.server.entity.District;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class DistrictResponse implements Serializable{
    private static final long serialVersionUID = 7832698252034264829L;

    private District district;

    public DistrictResponse(JSONObject o) throws JSONException {
        district = new District(o);
    }

    public District getDistrict() {
        return district;
    }
}
