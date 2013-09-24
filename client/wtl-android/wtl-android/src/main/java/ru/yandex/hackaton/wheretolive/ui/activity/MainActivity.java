package ru.yandex.hackaton.wheretolive.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtilsImpl;
import ru.yandex.hackaton.wheretolive.server.WtlClient;
import ru.yandex.hackaton.wheretolive.server.handlers.CategoryHandler;
import ru.yandex.hackaton.wheretolive.server.handlers.DistrictHandler;
import ru.yandex.hackaton.wheretolive.server.listener.OnResponseListener;
import ru.yandex.hackaton.wheretolive.server.requests.BaseRequest;
import ru.yandex.hackaton.wheretolive.server.responses.CategoryResponse;
import ru.yandex.hackaton.wheretolive.server.responses.DistrictResponse;
import ru.yandex.hackaton.wheretolive.utils.DeviceUtil;

/**
 * Created with IntelliJ IDEA. User: rustamgaifullin Date: 9/14/13 Time: 6:07 PM To change this
 * template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        DeviceUtil.init(this);
        try {
            WtlClient.request(new DistrictHandler(new BaseRequest())
                    .addResponseListener(new OnResponseListener<DistrictResponse>() {
                        @Override
                        public void onOk(DistrictResponse param) {
                            WtlUtilsImpl wtlUtils = WtlUtils.Factory.get(MainActivity.this);
                            wtlUtils.insertDistrict(param.getDistrict());
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
                        }
                    }));
            WtlClient.request(new CategoryHandler(new BaseRequest())
                    .addResponseListener(new OnResponseListener<CategoryResponse>() {
                        @Override
                        public void onOk(CategoryResponse param) {
                            WtlUtilsImpl wtlUtils = WtlUtils.Factory.get(MainActivity.this);
                            wtlUtils.insertCategory(param.getmCategory());
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
                        }
                    }));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void onProfileClick(View v){
        ProfileActivity.show(this);
    }

    public void onParametersClick(View v) {
        SearchActivity.show(this);
    }
}
