package ru.yandex.hackaton.wheretolive.server.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class Category implements Serializable{
    private static final long serialVersionUID = 2703207872409787107L;

    private int id;
    private String name;
    private int rating;

    public Category() {

    }

    public Category(JSONObject o) throws JSONException {
        this.id = o.getInt("id");
        this.name = o.getString("name");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
