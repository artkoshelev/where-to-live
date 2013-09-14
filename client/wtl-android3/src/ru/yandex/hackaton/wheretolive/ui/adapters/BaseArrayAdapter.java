package ru.yandex.hackaton.wheretolive.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by rustamgaifullin on 9/14/13.
 */
public abstract class BaseArrayAdapter<Item extends Object> extends ArrayAdapter<Item> {

    private Activity mActivity;

    public static class ViewHolder {
        private final SparseArray<View> mViews = new SparseArray<View>();
        private final int mLayoutId;

        public ViewHolder(final int layoutId) {
            mLayoutId = layoutId;
        }

        public void setView(final int id, final View view) {
            mViews.put(id, view);
        }

        public View getView(final int id) {
            return mViews.get(id);
        }

        public int getLayoutId() {
            return mLayoutId;
        }
    }

    public BaseArrayAdapter(final Activity context, final List<Item> items) {
        super(context, 0, items);
        mActivity = context;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = null;
        final int layoutId = getLayoutId(position);
        // Check if recycled cell has the same layout id
        if (convertView != null) {
            final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            if (viewHolder.getLayoutId() == layoutId) {
                view = convertView;
            }
        }
        if (view == null) {
            final LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Create view
            view = inflater.inflate(layoutId, parent, false);
            // Create holder
            final ViewHolder viewHolder = createHolder(layoutId);
            initViewHolder(view, position, viewHolder);
            // Save holder
            view.setTag(viewHolder);
        }
        // Fill controls
        initControls(view, position, (ViewHolder) view.getTag());
        return view;
    }

    public void clearBitmaps() {
    }

    protected void addViewToHolder(final View view, final ViewHolder viewHolder, final int resId) {
        viewHolder.setView(resId, view.findViewById(resId));
    }

    protected ViewHolder createHolder(final int layoutId) {
        return new ViewHolder(layoutId);
    }

    protected void initViewHolder(final View view, final int position, final ViewHolder viewHolder) {
        final int[] resources = getCachedResources();
        for (final int resId : resources) {
            addViewToHolder(view, viewHolder, resId);
        }
    }

    protected abstract int[] getCachedResources();

    protected abstract void initControls(final View view, final int position,
                                         final ViewHolder viewHolder);

    protected abstract int getLayoutId(final int position);

    protected Activity getActivity() {
        return mActivity;
    }

}
