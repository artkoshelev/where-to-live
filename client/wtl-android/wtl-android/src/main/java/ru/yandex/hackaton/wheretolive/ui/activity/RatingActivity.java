package ru.yandex.hackaton.wheretolive.ui.activity;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

import ru.yandex.hackaton.wheretolive.DataHolder;
import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.server.WtlClient;
import ru.yandex.hackaton.wheretolive.server.handlers.ParamHandler;
import ru.yandex.hackaton.wheretolive.server.listener.OnResponseListener;
import ru.yandex.hackaton.wheretolive.server.requests.ParamRequest;
import ru.yandex.hackaton.wheretolive.server.responses.RatingResponse;
import ru.yandex.hackaton.wheretolive.ui.adapters.RatingAdapter;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class RatingActivity extends ListActivity {
    private final static int MAX = 15;

    private RatingAdapter mAdapter;

    public static void show(Context context) {
        Intent i = new Intent(context, RatingActivity.class);
        context.startActivity(i);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        DataHolder.getDistricts().clear();
        mAdapter = new RatingAdapter(this, DataHolder.getDistricts());
        setListAdapter(mAdapter);

        try {
            WtlClient.request(RatingActivity.this, new ParamHandler(new ParamRequest(RatingActivity.this)).addResponseListener(new OnResponseListener<RatingResponse>() {
                @Override
                public void onOk(RatingResponse response) {
                    if (DataHolder.getDistricts().size() <= MAX) {
                        DataHolder.getDistricts().add(response);
                        RatingActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }

                @Override
                public void onError(Throwable throwable) {

                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WebActivity.show(RatingActivity.this, new int[]
                        {
                            DataHolder.getDistricts().get(i).getDistrictId()
                        });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_map:
                int[] districtsId = new int[MAX];
                for (int i = 0; i < MAX; i++) {
                    districtsId[i] = DataHolder.getDistricts().get(i).getDistrictId();
                }
                WebActivity.show(this, districtsId);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
