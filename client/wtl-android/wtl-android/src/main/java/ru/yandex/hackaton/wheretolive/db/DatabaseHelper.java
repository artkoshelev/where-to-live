package ru.yandex.hackaton.wheretolive.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.yandex.hackaton.wheretolive.db.content.CategoryColumns;
import ru.yandex.hackaton.wheretolive.db.content.DistrictColumns;
import ru.yandex.hackaton.wheretolive.db.content.PresetColumns;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "wtl_db";
    public static final String CATEGORY = "category";
    public static final String DISTRICT = "district";
    public static final String PRESET = "preset";

    public DatabaseHelper(Context context) {
        super (context, DATABASE_NAME, null, VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CATEGORY + " (" +
                CategoryColumns._ID + " INTEGER PRIMARY KEY," +
                CategoryColumns.NAME + " TEXT," +
                CategoryColumns.SEARCHPARAM + " TEXT," +
                CategoryColumns.RATING + " INTEGER" +
                ");");
        db.execSQL("CREATE TABLE " + DISTRICT + " (" +
                DistrictColumns._ID + " INTEGER PRIMARY KEY," +
                DistrictColumns.NAME + " TEXT," +
                DistrictColumns.BORDERS + " TEXT" +
                ");");
        db.execSQL("CREATE TABLE " + PRESET + " (" +
                PresetColumns._ID + " INTEGER PRIMARY KEY," +
                PresetColumns.KEY + " TEXT," +
                PresetColumns.VALUE + " INTEGER," +
                PresetColumns.CATEGORY_ID + " INTEGER" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY);
        onCreate(db);
    }
}
