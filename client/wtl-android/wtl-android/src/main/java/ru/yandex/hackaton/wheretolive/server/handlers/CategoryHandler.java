package ru.yandex.hackaton.wheretolive.server.handlers;

import org.json.JSONException;
import org.json.JSONObject;

import ru.yandex.hackaton.wheretolive.server.WtlClient;
import ru.yandex.hackaton.wheretolive.server.requests.BaseRequest;
import ru.yandex.hackaton.wheretolive.server.responses.CategoryResponse;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class CategoryHandler extends BaseJsonHandler<BaseRequest, CategoryResponse> {
    public CategoryHandler(BaseRequest baseRequest) {
        super(baseRequest);
    }

    @Override
    public String getUrl() {
        return "categories";
    }

    @Override
    public JSONObject getRequest() throws JSONException {
        return new JSONObject();
    }

    @Override
    protected CategoryResponse onOK(JSONObject object) throws JSONException {
        return new CategoryResponse(object);
    }

    @Override
    public WtlClient.RequestType getType() {
        return WtlClient.RequestType.Get;
    }
}
