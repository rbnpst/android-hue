package rpst.me.androidhue.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import rpst.me.androidhue.R;
import rpst.me.androidhue.models.Light;

/**
 * Created by Robin on 10-10-2017.
 */

public class LightAdapter extends ArrayAdapter {

    private List<Light> lights;
    private CompoundButton.OnCheckedChangeListener listener;

    public LightAdapter(@NonNull Context context, @NonNull List<Light> lights) {
        super(context, 0, lights);
        this.lights = lights;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_light, parent, false);
        }

        TextView textName = convertView.findViewById(R.id.text_name);
//        Switch lightSwitch = convertView.findViewById(R.id.switch_light);

        Light light = lights.get(position);

        textName.setText(light.getName());
//        lightSwitch.setChecked(light.isOn());
//        lightSwitch.setOnCheckedChangeListener(listener);

        return convertView;
    }

    public void onSwitchClickListener(CompoundButton.OnCheckedChangeListener listener) {
        this.listener = listener;
    }
}
