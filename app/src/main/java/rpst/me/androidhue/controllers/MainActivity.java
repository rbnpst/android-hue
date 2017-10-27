package rpst.me.androidhue.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import rpst.me.androidhue.R;
import rpst.me.androidhue.adapters.PlaceAdapter;
import rpst.me.androidhue.models.Place;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private PlaceAdapter mAdapter;
    private List<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_view_places);
        places = new ArrayList<>();
        places.add(new Place().withName("LA134").withHostname("http://192.168.1.179/api/").withUsername(""));
        places.add(new Place().withName("Emulator").withHostname("http://192.168.178.18:8000/api/").withUsername("newdeveloper"));

        mAdapter = new PlaceAdapter(this, places);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(MainActivity.this, LightsOverviewActivity.class);
                i.putExtra("place", places.get(position));
                startActivity(i);
            }
        });
    }
}