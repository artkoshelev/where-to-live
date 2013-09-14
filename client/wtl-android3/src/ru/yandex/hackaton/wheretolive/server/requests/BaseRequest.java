package ru.yandex.hackaton.wheretolive.server.requests;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 5819747284430094767L;

    public JSONObject getJSON() throws JSONException {
        return new JSONObject();
    }
}
