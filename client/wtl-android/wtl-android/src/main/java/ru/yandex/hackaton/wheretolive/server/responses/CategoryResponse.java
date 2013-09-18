package ru.yandex.hackaton.wheretolive.server.responses;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import ru.yandex.hackaton.wheretolive.server.entity.Category;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class CategoryResponse implements Serializable {
    private static final long serialVersionUID = -7454080908492771779L;

    private Category mCategory;

    public CategoryResponse(JSONObject o) throws JSONException {
        mCategory = new Category(o);
    }

    public Category getmCategory() {
        return mCategory;
    }
}
