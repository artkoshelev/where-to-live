package ru.yandex.hackaton.wheretolive.server.handlers;

import org.json.JSONException;
import org.json.JSONObject;

import ru.yandex.hackaton.wheretolive.server.WtlClient;
import ru.yandex.hackaton.wheretolive.server.requests.ParamRequest;
import ru.yandex.hackaton.wheretolive.server.responses.RatingResponse;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class ParamHandler extends BaseJsonHandler<ParamRequest, RatingResponse> {
    private ParamRequest mRequest;

    public ParamHandler(ParamRequest request) {
        mRequest = request;
    }

    @Override
    public String getUrl() {
        return "districts/search";
    }

    @Override
    public JSONObject getRequest() throws JSONException {
        return mRequest.getJSON();
    }

    @Override
    protected RatingResponse onOK(JSONObject object) throws JSONException {
        return new RatingResponse(object);
    }

    @Override
    public WtlClient.RequestType getType() {
        return WtlClient.RequestType.Post;
    }
}
