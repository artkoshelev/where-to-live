package ru.yandex.hackaton.wheretolive.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.server.entity.District;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class WebActivity extends Activity {
    private static final String POLYGON_FORMAT = "var myPolygon = new ymaps.Polygon(%s{\n" +
            "        hintContent: \"Многоугольник\"\n" +
            "    }, {\n" +
            "        fillColor: '#00FF0088',\n" +
            "        strokeWidth: 5\n" +
            "    });\n" +
            "    myMap.geoObjects.add(myPolygon);";

    public static void show(Context context) {
        Intent i = new Intent(context, WebActivity.class);
        context.startActivity(i);
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.web_activity);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);


        StringBuilder polygons = new StringBuilder();
        for (int i = 1; i < 125; i++) {
            District district = WtlUtils.Factory.get(this).getDistrictById(i);
            String polygon = String.format(POLYGON_FORMAT, getCoordinates(district));
            polygons.append(polygon).append(" \n");
        }

        String data = String.format(readRawTextFile(this, R.raw.polygon), polygons.toString());

        webView.loadDataWithBaseURL("blarg://ignored", data, "text/html", "UTF-8", "");
    }

    public static String readRawTextFile(Context ctx, int resId) {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }

        return text.toString();
    }

    public String getCoordinates(District district) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n[\n");

        String borders[] = district.getBorders().split("[,]+");
        for (int i = 0; i < borders.length; i++) {
            String border = borders[i];
            sb.append("[").append(border.replace(" ", ", ")).append("]");
            if (i != borders.length - 1) {
                sb.append(",\n");
            } else {
                sb.append("\n");
            }
        }

        sb.append("\n]\n], ");

        return sb.toString();
    }

    private static class JavaScriptInterface {
        Context mContext;
        District mDistrict;

        public JavaScriptInterface(Context c, District d) {
            mContext = c;
            mDistrict = d;
        }


        @JavascriptInterface
        public String getName() {
            return mDistrict.getName();
        }
    }

}
