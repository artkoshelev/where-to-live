package ru.yandex.hackaton.wheretolive.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.yandex.hackaton.wheretolive.db.content.CategoryColumns;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "wtl_db";
    public static final String CATEGORY = "category";
    public static final String REGION = "region";

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
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY);
        onCreate(db);
    }
}
