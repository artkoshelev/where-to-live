package ru.yandex.hackaton.wheretolive.server;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class WtlClient {

    private static final String BASE_URL = "http://api.twitter.com/1/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
