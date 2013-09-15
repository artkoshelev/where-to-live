package ru.yandex.hackaton.wheretolive.server.responses;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class RatingResponse implements Serializable{
    private static final long serialVersionUID = -7596295997011417276L;

    private int id;
    private int districtId;
    private int summ;

    public RatingResponse(JSONObject object) throws JSONException {
        id = object.getInt("id");
        districtId = object.getInt("districtid");
        summ = object.getInt("summ");
    }

    public int getId() {
        return id;
    }

    public int getDistrictId() {
        return districtId;
    }

    public int getSumm() {
        return summ;
    }
}
