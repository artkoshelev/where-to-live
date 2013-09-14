package ru.yandex.hackaton.wheretolive.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.yandexmapkit.MapView;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class MapActivity extends Activity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapView = (MapView) findViewById(R.id.map);
    }
}
