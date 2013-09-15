package ru.yandex.hackaton.wheretolive.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapSurfaceView;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.map.MapLayer;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class MapActivity extends Activity {
    public static void show(Context context) {
        Intent i = new Intent(context, MapActivity.class);
        context.startActivity(i);
    }


    private MapView mMapView;
    MapController mMapController;
    OverlayManager mOverlayManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        mMapView = (MapView) findViewById(R.id.map);
        mMapController = mMapView.getMapController();
        mOverlayManager = mMapController.getOverlayManager();
        // Disable determining the user's location
        mOverlayManager.getMyLocation().setEnabled(false);

//        mOverlayManager.
        // A simple implementation of map objects
        showObject();

    }

    public void showObject(){
        // Load required resources
        Resources res = getResources();

        // Create a layer of objects for the map
        Overlay overlay = new Overlay(mMapController);


        // Create an object for the layer
        OverlayItem yandex = new OverlayItem(new GeoPoint(55.734029 , 37.588499), res.getDrawable(R.drawable.ic_launcher));
        // Add the object to the layer
        overlay.addOverlayItem(yandex);

        // Add the layer to the map
        mOverlayManager.addOverlay(overlay);
    }

}
