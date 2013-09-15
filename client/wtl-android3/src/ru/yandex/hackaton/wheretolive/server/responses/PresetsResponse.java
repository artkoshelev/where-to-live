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
public class PresetsResponse implements Serializable {
    private String name;
    private Map<String, Integer> mParameters = new HashMap<String, Integer>();

    private static final long serialVersionUID = 1901065817085731260L;

    public PresetsResponse(JSONObject object) throws JSONException {
        name = object.getString("presetname");

        Iterator it = object.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (!key.equalsIgnoreCase("presetname")) {
                Integer value = object.getInt(key);
                mParameters.put(key, value);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getParameters() {
        return mParameters;
    }

    public void setParameters(Map<String, Integer> mParameters) {
        this.mParameters = mParameters;
    }
}
