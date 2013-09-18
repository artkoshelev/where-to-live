package ru.yandex.hackaton.wheretolive.server;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import ru.yandex.hackaton.wheretolive.server.handlers.BaseJsonHandler;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class WtlClient {
    public enum RequestType {
        Get,
        Post
    }

    private static final String BASE_URL = "http://api.where2live.ru/";

    private static AsyncHttpClient sClient = new AsyncHttpClient();

    public static AsyncHttpClient getClient() {
        return sClient;
    }

    public static void request(BaseJsonHandler<?, ?> handler) throws JSONException {
        final String absoluteUrl = getAbsoluteUrl(handler.getUrl());
        final RequestParams params = new RequestParams("request", handler.getRequest().toString());
        Log.i("WtlClient", MessageFormat.format("URL: {0}\nRequest:{1}", absoluteUrl, params));
        RequestType requestType = handler.getType();

        switch (requestType) {
            case Get:
                sClient.get(absoluteUrl, params, handler);
                break;
            case Post:
                sClient.addHeader("Content-Type", "application/json");
//                sClient.addHeader("Accept", "application/json");
                sClient.post(absoluteUrl, params, handler);
                break;
        }
    }

    public static void request(Context context, BaseJsonHandler<?, ?> handler) throws JSONException, UnsupportedEncodingException {
        final String absoluteUrl = getAbsoluteUrl(handler.getUrl());
        StringEntity entity = new StringEntity(handler.getRequest().toString());
        Log.i("WtlClient", MessageFormat.format("URL: {0}\nRequest:{1}", absoluteUrl, entity.toString()));

        sClient.post(context, absoluteUrl, entity, "application/json", handler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
