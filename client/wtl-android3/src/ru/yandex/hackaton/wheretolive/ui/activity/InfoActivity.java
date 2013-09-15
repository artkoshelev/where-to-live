package ru.yandex.hackaton.wheretolive.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import ru.yandex.hackaton.wheretolive.DataHolder;
import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.ui.adapters.InfoAdapter;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class InfoActivity extends ListActivity {
    private InfoAdapter mAdapter;

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

        int id = getIntent().getIntExtra("id", -1);

        setTitle(DataHolder.getDistricts().get(id).getName());
        mAdapter = new InfoAdapter(DataHolder.getDistricts().get(id).getmParameters(), this);
        setListAdapter(mAdapter);
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
