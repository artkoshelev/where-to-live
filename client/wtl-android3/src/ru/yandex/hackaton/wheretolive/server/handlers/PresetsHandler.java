package ru.yandex.hackaton.wheretolive.server.handlers;

import org.json.JSONException;
import org.json.JSONObject;

import ru.yandex.hackaton.wheretolive.server.WtlClient;
import ru.yandex.hackaton.wheretolive.server.requests.BaseRequest;
import ru.yandex.hackaton.wheretolive.server.responses.PresetsResponse;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class PresetsHandler extends BaseJsonHandler<BaseRequest, PresetsResponse> {
    @Override
    public String getUrl() {
        return "presets";
    }

    @Override
    public JSONObject getRequest() throws JSONException {
        return new JSONObject();
    }

    @Override
    protected PresetsResponse onOK(JSONObject object) throws JSONException {
        return new PresetsResponse(object);
    }

    @Override
    public WtlClient.RequestType getType() {
        return WtlClient.RequestType.Get;
    }
}
