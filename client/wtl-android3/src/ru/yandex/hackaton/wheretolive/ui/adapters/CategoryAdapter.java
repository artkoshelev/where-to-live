package ru.yandex.hackaton.wheretolive.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.server.entity.Category;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public class CategoryAdapter extends BaseArrayAdapter<Category> {
    public CategoryAdapter(Activity context, List list) {
        super(context, list);
    }

    @Override
    protected int[] getCachedResources() {
        return new int[] {
                R.id.name,
                R.id.ratingBar
        };
    }

    @Override
    protected void initControls(View view, int position, ViewHolder viewHolder) {
        final Category category = getItem(position);

        TextView nameTextView = (TextView) viewHolder.getView(R.id.name);
        final RatingBar ratingBar = (RatingBar) viewHolder.getView(R.id.ratingBar);

        nameTextView.setText(category.getName());
        ratingBar.setRating(category.getRating());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                WtlUtils.Factory.get(mActivity).updateRating(category.getId(), (int) ratingBar.getRating());
            }
        });
    }

    @Override
    protected int getLayoutId(int position) {
        return R.layout.category_list_item;
    }
}
