package ru.yandex.hackaton.wheretolive.server.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class Category implements Serializable{
    private static final long serialVersionUID = 2703207872409787107L;

    private String name;

    public Category(JSONObject o) throws JSONException {
        this.name = o.getString("name");
    }

    public String getName() {
        return name;
    }
}
