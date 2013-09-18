package ru.yandex.hackaton.wheretolive.server.handlers;

import org.json.JSONException;
import org.json.JSONObject;

import ru.yandex.hackaton.wheretolive.server.WtlClient;
import ru.yandex.hackaton.wheretolive.server.requests.BaseRequest;
import ru.yandex.hackaton.wheretolive.server.responses.DistrictResponse;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class DistrictHandler extends BaseJsonHandler<BaseRequest, DistrictResponse> {

    public DistrictHandler(BaseRequest request) {
        super(request);
    }

    @Override
    public String getUrl() {
        return "districts";
    }

    @Override
    public JSONObject getRequest() throws JSONException {
        return new JSONObject();
    }

    @Override
    protected DistrictResponse onOK(JSONObject object) throws JSONException {
        return new DistrictResponse(object);
    }

    @Override
    public WtlClient.RequestType getType() {
        return WtlClient.RequestType.Get;
    }
}
