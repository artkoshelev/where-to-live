package ru.yandex.hackaton.wheretolive.server.requests;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.server.entity.Category;
import ru.yandex.hackaton.wheretolive.server.responses.PresetsResponse;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class ParamRequest extends BaseRequest {
    private static final long serialVersionUID = -4391211403089407945L;
    private Context mContext;

    public ParamRequest(Context context) {
        this.mContext = context;
    }

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject object = new JSONObject();

        JSONObject param = new JSONObject();

        List<Category> categoryList = WtlUtils.Factory.get(mContext).getCategories();
        for (Category category : categoryList) {
            param.put(category.getSearchparam(), category.getRating());
        }

        object.put("params", param);

        return object;
    }
}
