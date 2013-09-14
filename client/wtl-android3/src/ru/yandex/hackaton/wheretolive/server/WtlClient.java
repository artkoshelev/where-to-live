package ru.yandex.hackaton.wheretolive.server;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

import java.text.MessageFormat;

import ru.yandex.hackaton.wheretolive.server.handlers.BaseJsonHandler;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class WtlClient {

    private static final String BASE_URL = "http://api.twitter.com/1/";

    private static AsyncHttpClient sClient = new AsyncHttpClient();

    public static AsyncHttpClient getClient() {
        return sClient;
    }

    public static void request(BaseJsonHandler<?, ?> handler) throws JSONException {
        final String absoluteUrl = getAbsoluteUrl(handler.getUrl());
        final RequestParams params = new RequestParams("request", handler.getRequest().toString());
        Log.i("WtlClient", MessageFormat.format("URL: {0}\nRequest:{1}", absoluteUrl, params));
        sClient.post(absoluteUrl, params, handler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
