package ru.yandex.hackaton.wheretolive.db.utils;

import android.content.Context;
import android.net.Uri;

import java.util.List;

import ru.yandex.hackaton.wheretolive.server.entity.Category;
import ru.yandex.hackaton.wheretolive.server.entity.District;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public interface WtlUtils {
    public Uri insertCategory(Category category);
    public List<Category> getCategories();
    public void updateRating(int id, int rating);
    public void updateRating(String key, int rating);

    public Uri insertDistrict(District district);
    public District getDistrictById(int id);

    public static class Factory {
        private static Factory instance = new Factory();

        public static WtlUtilsImpl get(Context context) {
            return instance.newForContext(context);
        }

        public static Factory getInstance() {
            return instance;
        }

        public static void overrideInstance(Factory factory) {
            instance = factory;
        }

        protected WtlUtilsImpl newForContext(Context context) {
            return new WtlUtilsImpl(context.getContentResolver());
        }
    }
}
