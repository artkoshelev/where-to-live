package ru.yandex.hackaton.wheretolive.server.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class District {
    private int id;
    private String name;
    private String borders = "";

    public District() {

    }

    public District(JSONObject o) throws JSONException {
        this.id = o.getInt("id");
        this.name = o.getString("name");
        this.borders = o.getString("borders");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBorders() {
        return borders;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }
}
