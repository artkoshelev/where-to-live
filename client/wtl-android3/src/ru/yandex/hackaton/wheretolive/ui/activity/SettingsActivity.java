package ru.yandex.hackaton.wheretolive.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;

import ru.yandex.hackaton.wheretolive.R;

/**
 * Created with IntelliJ IDEA. User: rustamgaifullin Date: 9/14/13 Time: 6:06 PM To change this
 * template use File | Settings | File Templates.
 */
public class SettingsActivity extends PreferenceActivity {
    public static void show(Context context) {
        Intent i = new Intent(context, SettingsActivity.class);
        context.startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_activity);
        setContentView(R.layout.settings_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "Show current settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                onBackPressed();
                return  true;
            case android.R.id.home:
                onBackPressed();
                return true;
            case android.R.id.icon:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }
}
