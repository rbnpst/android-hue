package rpst.me.androidhue.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rpst.me.androidhue.R;
import rpst.me.androidhue.models.Place;

/**
 * Created by Robin on 10-10-2017.
 */

public class PlaceAdapter extends ArrayAdapter {

    private List<Place> places;

    public PlaceAdapter(@NonNull Context context, List<Place> places) {
        super(context, 0, places);
        this.places = places;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_place, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.text_name);
        TextView textViewHostname = convertView.findViewById(R.id.text_hostname);

        Place place = places.get(position);

        textViewName.setText(place.getName());
        textViewHostname.setText(place.getHostname());

        return convertView;
    }
}
