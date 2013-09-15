package ru.yandex.hackaton.wheretolive.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import ru.yandex.hackaton.wheretolive.R;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class InfoActivity extends Activity {
    public static void show(Context context, int id) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
