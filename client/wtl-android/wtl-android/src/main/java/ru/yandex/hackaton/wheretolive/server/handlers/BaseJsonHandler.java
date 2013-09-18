package ru.yandex.hackaton.wheretolive.server.handlers;

import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import ru.yandex.hackaton.wheretolive.server.ParseResponseException;
import ru.yandex.hackaton.wheretolive.server.WtlClient;
import ru.yandex.hackaton.wheretolive.server.listener.OnResponseListener;
import ru.yandex.hackaton.wheretolive.server.requests.BaseRequest;
import ru.yandex.hackaton.wheretolive.utils.DeviceUtil;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public abstract class BaseJsonHandler <Request extends BaseRequest, Response> extends JsonHttpResponseHandler {
    public static final String FIELD_ERROR_MESSAGE = "error_msg";
    public static final String FIELD_ERROR_CODE = "error_code";
    public static final String RESULT_OK = "0";

    protected List<OnResponseListener<Response>> responseListenerList = new ArrayList<OnResponseListener<Response>>();
    protected Request request;

    public BaseJsonHandler() {
    }

    public BaseJsonHandler(final Request request) {
        this.request = request;
    }

    public abstract JSONObject getRequest() throws JSONException;

    public String getUrl() {
        return "";
    }

    protected abstract Response onOK(final JSONObject object) throws JSONException;

//    @Override
//    protected void handleSuccessMessage(final String s) {
//        Log.d("BaseJsonHandler", MessageFormat.format("Message: {0}", s));
//        super.handleSuccessMessage(s);
//    }

    @Override
    public void onSuccess(JSONArray jsonArray) {
        Log.d("BaseJsonHandler", MessageFormat.format("Received JSON object by request: {0}", request != null ? request.toString() : "{}"));
        super.onSuccess(jsonArray);

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (!responseListenerList.isEmpty()) {
                    final Response response = onOK(jsonObject);
                    for (final OnResponseListener<Response> listener : responseListenerList) {
                        listener.onOk(response);
                        DeviceUtil.hideProgressDialog();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onSuccess(final JSONObject jsonObject) {
        Log.d("BaseJsonHandler", MessageFormat.format("Received JSON object by request: {0}", request != null ? request.toString() : "{}"));
        super.onSuccess(jsonObject);
        try {
            String status = null;
            try {
                status = jsonObject.getString(FIELD_ERROR_CODE);
            } catch (JSONException e) {
                Log.e("BaseJsonHandler", e.getMessage(), e);
            }
            if (status == null || status.equals(RESULT_OK)) {
                final Response response = onOK(jsonObject);
                if (!responseListenerList.isEmpty()) {
                    for (final OnResponseListener<Response> listener : responseListenerList) {
                        listener.onOk(response);
                        DeviceUtil.hideProgressDialog();
                    }
                }
            } else {
                final String message = jsonObject.getString(FIELD_ERROR_MESSAGE);
                onFailure(new ParseResponseException(message, jsonObject.getInt(FIELD_ERROR_CODE)), message);
            }
        } catch (JSONException e) {
            onFailure(e, e.getMessage());
        }
    }

    @Override
    public void onFailure(final Throwable throwable, String message) {
        DeviceUtil.hideProgressDialog();
        if (throwable instanceof ConnectException) {
            Log.e("BaseJsonHandler", MessageFormat.format("Handler {0} failed - not have connect", getUrl()));
            DeviceUtil.showNotification("Необходимо подключение к интернету!", Toast.LENGTH_SHORT);
        } else {
            Log.e("BaseJsonHandler", MessageFormat.format("Handler {0} failed", getUrl()), throwable);
            if (!responseListenerList.isEmpty()) {
                for (final OnResponseListener<Response> listener : responseListenerList) {
                    listener.onError(throwable);
                }
            }
        }
    }

    public BaseJsonHandler<Request, Response> addResponseListener(final OnResponseListener<Response> responseListener) {
        responseListenerList.add(responseListener);
        return this;
    }

    public abstract WtlClient.RequestType getType();
}
