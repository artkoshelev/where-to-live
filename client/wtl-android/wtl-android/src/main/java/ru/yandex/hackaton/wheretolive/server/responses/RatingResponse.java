package ru.yandex.hackaton.wheretolive.server.responses;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class RatingResponse implements Serializable{
    private static final long serialVersionUID = -7596295997011417276L;
    private final Map<String, Integer> mParameters = new HashMap<String, Integer>();

    private String name;
    private int id;
    private int districtId;
    private int summ;

    public RatingResponse(JSONObject object) throws JSONException {
        id = object.getInt("id");
        districtId = object.getInt("districtid");
        summ = object.getInt("summ");
        name = object.getString("districtname");

        Iterator it = object.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (!key.equalsIgnoreCase("id") &&
                    !key.equalsIgnoreCase("districtid") &&
                    !key.equalsIgnoreCase("summ") &&
                    !key.equalsIgnoreCase("districtname") &&
                    !key.equalsIgnoreCase("size")) {
                if (!object.isNull(key)) {
                    Integer value = object.getInt(key);
                    mParameters.put(key, value);
                }
            }
        }
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

    public String getName() {
        return name;
    }

    public Map<String, Integer> getmParameters() {
        return mParameters;
    }
}
