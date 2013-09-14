package ru.yandex.hackaton.wheretolive.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

import ru.yandex.hackaton.wheretolive.db.content.CategoryColumns;
import ru.yandex.hackaton.wheretolive.db.content.RegionColumns;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class WtlContentProvider extends ContentProvider {
    protected static final String MAIN_URL = "ru.yandex.hackaton.wheretolive";
    protected static final int CATEGORY = 0;
    protected static final int CATEGORY_ID = 1;
    protected static final int REGION = 2;
    protected static final int REGION_ID = 3;
    protected SQLiteDatabase db;
    DatabaseHelper helper;
    protected final static UriMatcher urlMatcher;

    static {
        urlMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        urlMatcher.addURI(MAIN_URL, "category", CATEGORY);
        urlMatcher.addURI(MAIN_URL, "category/#", CATEGORY_ID);
        urlMatcher.addURI(MAIN_URL, "region/", REGION);
        urlMatcher.addURI(MAIN_URL, "region/#", REGION_ID);
    }

    @Override
    public boolean onCreate() {
        helper = new DatabaseHelper(getContext());
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (urlMatcher.match(uri)) {
            case CATEGORY:
                return CategoryColumns.CONTENT_TYPE;
            case CATEGORY_ID:
                return CategoryColumns.CONTENT_ITEMTYPE;
            case REGION:
                return RegionColumns.CONTENT_TYPE;
            case REGION_ID:
                return RegionColumns.CONTENT_ITEMTYPE;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = urlMatcher.match(uri);
        long rowId = db.insert(getTableNameById(match), BaseColumns._ID, values);
        if (rowId > 0) {
            Uri appedUri = ContentUris.appendId(getTableContentUriById(match).buildUpon(), rowId).build();
            getContext().getContentResolver().notifyChange(uri, null, true);
            return appedUri;
        }

        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = db.delete(getTableNameById(urlMatcher.match(uri)), selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null, true);

        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String orderBy = null;
        queryBuilder.setTables(getTableNameById(urlMatcher.match(uri)));
        if (sortOrder != null) {
            orderBy = sortOrder;
        } else {
            orderBy = BaseColumns._ID;
        }
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null,orderBy);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        String segment;
        int count;
        int match = urlMatcher.match(uri);
        switch (match) {
            case CATEGORY:
                count = db.update(DatabaseHelper.CATEGORY, values, selection,
                        selectionArgs);
                break;
            case CATEGORY_ID:
                segment = uri.getPathSegments().get(1);
                count = db.update(DatabaseHelper.CATEGORY, values, "_id="
                        + segment
                        + (!TextUtils.isEmpty(selection) ? " AND (" + selection
                        + ')' : ""), selectionArgs);
                break;
            case REGION:
                count = db.update(DatabaseHelper.REGION, values, selection,
                        selectionArgs);
                break;
            case REGION_ID:
                segment = uri.getPathSegments().get(1);
                count = db.update(DatabaseHelper.CATEGORY, values, "_id="
                        + segment
                        + (!TextUtils.isEmpty(selection) ? " AND (" + selection
                        + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null, true);
        return count;
    }

    private String getTableNameById(int matchId) {
        switch (matchId) {
            case CATEGORY:
                return DatabaseHelper.CATEGORY;
            case CATEGORY_ID:
                return DatabaseHelper.CATEGORY;
            case REGION:
                return DatabaseHelper.REGION;
            case REGION_ID:
                return DatabaseHelper.REGION;
            default:
                return null;
        }
    }

    private Uri getTableContentUriById(int matchId) {
        switch (matchId) {
            case CATEGORY:
                return CategoryColumns.CONTENT_URI;
            case REGION:
                return RegionColumns.CONTENT_URI;
            default:
                return null;
        }
    }
}
