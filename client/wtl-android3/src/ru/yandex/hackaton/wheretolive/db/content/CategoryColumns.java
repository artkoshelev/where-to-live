package ru.yandex.hackaton.wheretolive.db.content;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class CategoryColumns implements BaseColumns {
    public static final Uri CONTENT_URI = Uri.parse("content://ru.yandex.hackaton.wheretolive/category");
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.yandex.hackaton.category";
    public static final String CONTENT_ITEMTYPE = "vnd.android.cursor.item/vnd.yandex.hackaton.category";
    public static final String DEFAULT_SORT_ORDER = "_id";

    public static final String NAME = "name";
}
