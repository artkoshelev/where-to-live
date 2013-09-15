package ru.yandex.hackaton.wheretolive.db.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import ru.yandex.hackaton.wheretolive.db.content.CategoryColumns;
import ru.yandex.hackaton.wheretolive.db.content.DistrictColumns;
import ru.yandex.hackaton.wheretolive.server.entity.Category;
import ru.yandex.hackaton.wheretolive.server.entity.District;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class WtlUtilsImpl implements WtlUtils {
    private ContentResolver mContentResolver;

    public WtlUtilsImpl(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    @Override
    public Uri insertCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put(CategoryColumns.NAME, category.getName());

        return mContentResolver.insert(CategoryColumns.CONTENT_URI, values);
    }

    @Override
    public List<Category> getCategories() {
        Cursor c = mContentResolver.query(CategoryColumns.CONTENT_URI, null, null, null, null);
        List<Category> trackList = new ArrayList<Category>();
        c.moveToFirst();

        for (int i = 0; i < c.getCount(); i++) {
            Category category = new Category();
            category.setId(c.getInt(c.getColumnIndex(CategoryColumns._ID)));
            category.setName(c.getString(c.getColumnIndex(CategoryColumns.NAME)));
            category.setRating(c.getInt(c.getColumnIndex(CategoryColumns.RATING)));
            trackList.add(category);

            c.moveToNext();
        }

        c.close();
        return trackList;
    }

    @Override
    public void updateRating(int id, int rating) {
        String where = String.format("%s = '%s'", CategoryColumns._ID, id);
        ContentValues values = new ContentValues();
        values.put(CategoryColumns.RATING, rating);

        mContentResolver.update(CategoryColumns.CONTENT_URI, values, where, null);
    }

    @Override
    public Uri insertDistrict(District district) {
        ContentValues values = new ContentValues();
        values.put(DistrictColumns.NAME, district.getName());
        values.put(DistrictColumns.BORDERS, district.getBorders());

        return mContentResolver.insert(DistrictColumns.CONTENT_URI, values);
    }

    @Override
    public District getDistrictById(int id) {
        String selection = String.format("%s = %s", DistrictColumns._ID, id);
        Cursor c = null;
        District district = new District();

        c = mContentResolver.query(DistrictColumns.CONTENT_URI, null, selection, null, null);
        if (c.moveToFirst()) {
            district.setId(c.getInt(c.getColumnIndex(DistrictColumns._ID)));
            district.setName(c.getString(c.getColumnIndex(DistrictColumns.NAME)));
            district.setBorders(c.getString(c.getColumnIndex(DistrictColumns.BORDERS)));

            c.close();
            return district;
        }

        c.close();
        return null;
    }
}
