package ru.yandex.hackaton.wheretolive.ui.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ru.yandex.hackaton.wheretolive.R;
import ru.yandex.hackaton.wheretolive.server.responses.PresetsResponse;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class PresetAdapter extends BaseArrayAdapter<PresetsResponse> {

    public PresetAdapter(Activity context, List<PresetsResponse> presetsResponses) {
        super(context, presetsResponses);
    }

    @Override
    protected int[] getCachedResources() {
        return new int[] {
                R.id.presetName
        };
    }

    @Override
    protected void initControls(View view, int position, ViewHolder viewHolder) {
        PresetsResponse preset = getItem(position);
        TextView presetName = (TextView) viewHolder.getView(R.id.presetName);
        presetName.setText(preset.getName());
    }

    @Override
    protected int getLayoutId(int position) {
        return R.layout.presets_list_item;
    }



}
