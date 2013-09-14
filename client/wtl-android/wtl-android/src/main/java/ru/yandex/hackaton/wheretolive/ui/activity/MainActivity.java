package ru.yandex.hackaton.wheretolive.ui.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.server.WtlClient;

public class MainActivity extends Activity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}