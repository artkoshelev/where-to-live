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

import java.util.List;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtilsImpl;
import ru.yandex.hackaton.wheretolive.server.entity.Category;
import ru.yandex.hackaton.wheretolive.ui.adapters.CategoryAdapter;

/**
 * Created with IntelliJ IDEA. User: rustamgaifullin Date: 9/14/13 Time: 6:06 PM To change this
 * template use File | Settings | File Templates.
 */
public class CategoryActivity extends ListActivity {

    private CategoryAdapter mAdapter;

    public static void show(Context context) {
        Intent i = new Intent(context, CategoryActivity.class);
        context.startActivity(i);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        WtlUtilsImpl wtlUtils = WtlUtils.Factory.get(this);
        List<Category> categoryList = wtlUtils.getCategories();
        mAdapter = new CategoryAdapter(this, categoryList);
        getListView().setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_map:
                MapActivity.show(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.wizard_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
