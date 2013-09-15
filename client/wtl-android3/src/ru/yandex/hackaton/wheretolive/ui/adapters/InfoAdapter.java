package ru.yandex.hackaton.wheretolive.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.db.utils.WtlUtils;
import ru.yandex.hackaton.wheretolive.server.entity.Category;
import ru.yandex.hackaton.wheretolive.server.responses.RatingResponse;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class InfoAdapter extends BaseAdapter {
    private Map<String, Integer> mData = new HashMap<String, Integer>();
    private Context mContext;
    private String[] mKeys;
    private String name;
    public InfoAdapter(Map<String, Integer> data, Context context){
        mData  = data;
        mKeys = mData.keySet().toArray(new String[data.size()]);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(mKeys[position]);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        String key = mKeys[pos];
        String Value = getItem(pos).toString();

        RelativeLayout layout;

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (RelativeLayout) mInflater.inflate(R.layout.category_list_item, null);
        } else {
            layout = (RelativeLayout) convertView;
        }

        TextView nameTextView = (TextView) layout.findViewById(R.id.name);
        Category category = WtlUtils.Factory.get(mContext).getCategoryBySearchParam(key);
        nameTextView.setText(category.getName());

        RatingBar ratingBar = (RatingBar) layout.findViewById(R.id.ratingBar);
        ratingBar.setIsIndicator(true);
        ratingBar.setRating(mData.get(key));

        return layout;
    }
}
