package ru.yandex.hackaton.wheretolive.ui.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.server.entity.District;
import ru.yandex.hackaton.wheretolive.server.responses.RatingResponse;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class RatingAdapter extends BaseArrayAdapter<RatingResponse> {
    public RatingAdapter(Activity context, List list) {
        super(context, list);
    }

    @Override
    protected int[] getCachedResources() {
        return new int[] {
                R.id.name,
                R.id.rating
        };
    }

    @Override
    protected void initControls(View view, int position, ViewHolder viewHolder) {
        RatingResponse rating = getItem(position);

        District district = WtlUtils.Factory.get(mActivity).getDistrictById(rating.getDistrictId());
        TextView nameTextView = (TextView) viewHolder.getView(R.id.name);
        nameTextView.setText(district.getName());

        TextView ratingTextView = (TextView) viewHolder.getView(R.id.rating);
        ratingTextView.setText(String.valueOf(rating.getSumm()));
    }

    @Override
    protected int getLayoutId(int position) {
        return R.layout.rating_list_item;
    }
}
