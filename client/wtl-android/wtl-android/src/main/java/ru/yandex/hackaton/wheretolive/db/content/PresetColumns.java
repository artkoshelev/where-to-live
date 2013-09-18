package ru.yandex.hackaton.wheretolive.db.content;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class PresetColumns implements BaseColumns {
    public static final Uri CONTENT_URI = Uri.parse("content://ru.yandex.hackaton.wheretolive/preset");
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.yandex.hackaton.preset";
    public static final String CONTENT_ITEMTYPE = "vnd.android.cursor.item/vnd.yandex.hackaton.preset";
    public static final String DEFAULT_SORT_ORDER = "_id";

    public static final String KEY = "key";
    public static final String VALUE = "value";
    public static final String CATEGORY_ID = "categoryId";
}
