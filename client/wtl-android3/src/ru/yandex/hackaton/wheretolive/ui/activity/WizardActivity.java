package ru.yandex.hackaton.wheretolive.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import ru.yandex.hackaton.wheretolive.R;

/**
 * Created with IntelliJ IDEA. User: rustamgaifullin Date: 9/14/13 Time: 6:07 PM To change this
 * template use File | Settings | File Templates.
 */
public class WizardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wizard_activity);
    }

    public void onProfileClick(View v){
        ProfileActivity.show(this);
    }

    public void onParametersClick(View v) {
        SettingsActivity.show(this);
    }
}
