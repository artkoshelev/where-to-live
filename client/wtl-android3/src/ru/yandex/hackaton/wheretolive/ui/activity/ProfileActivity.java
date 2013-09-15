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
import java.util.ArrayList;
import java.util.List;

import ru.yandex.hackaton.wheretolive.DataHolder;
import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.server.WtlClient;
import ru.yandex.hackaton.wheretolive.server.handlers.ParamHandler;
import ru.yandex.hackaton.wheretolive.server.handlers.PresetsHandler;
import ru.yandex.hackaton.wheretolive.server.listener.OnResponseListener;
import ru.yandex.hackaton.wheretolive.server.requests.ParamRequest;
import ru.yandex.hackaton.wheretolive.server.responses.RatingResponse;
import ru.yandex.hackaton.wheretolive.server.responses.PresetsResponse;
import ru.yandex.hackaton.wheretolive.ui.adapters.PresetAdapter;

/**
 * Created with IntelliJ IDEA. User: rustamgaifullin Date: 9/14/13 Time: 6:06 PM To change this
 * template use File | Settings | File Templates.
 */
public class ProfileActivity extends ListActivity {
    private List<PresetsResponse> mPresetsList = new ArrayList<PresetsResponse>();
    private PresetAdapter mAdapter;

    public static void show(Context context) {
        Intent i = new Intent(context, ProfileActivity.class);
        context.startActivity(i);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        mAdapter = new PresetAdapter(this, mPresetsList);
        setListAdapter(mAdapter);
        try {
            WtlClient.request(new PresetsHandler().addResponseListener(new OnResponseListener<PresetsResponse>() {
                @Override
                public void onOk(PresetsResponse response) {
                    mPresetsList.add(response);
                    ProfileActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }

                @Override
                public void onError(Throwable throwable) {

                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final PresetsResponse presetResponse = mPresetsList.get(i);
                for (String key : presetResponse.getParameters().keySet()) {
                    Integer value = presetResponse.getParameters().get(key);
                    WtlUtils.Factory.get(ProfileActivity.this).updateRating(key, value);
                }
                RatingActivity.show(ProfileActivity.this);
            }
        });

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
