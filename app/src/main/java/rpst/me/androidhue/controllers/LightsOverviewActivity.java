package rpst.me.androidhue.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;

import java.util.List;

import rpst.me.androidhue.R;
import rpst.me.androidhue.adapters.LightAdapter;
import rpst.me.androidhue.json.Parser;
import rpst.me.androidhue.listeners.OnCompletionListener;
import rpst.me.androidhue.models.Light;
import rpst.me.androidhue.models.Place;
import rpst.me.androidhue.networking.HueApiHandler;

public class LightsOverviewActivity extends AppCompatActivity {

    private Place place;
    private ListView mListView;
    private LightAdapter mAdapter;
    private HueApiHandler hueApiHandler;
    private List<Light> lights;
    private Parser mParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights_overview);

        place = (Place) getIntent().getSerializableExtra("place");

        hueApiHandler = new HueApiHandler(this);
        mListView = findViewById(R.id.list_view_lights);

        hueApiHandler.request(place.getFullUrlForLights(), "", Request.Method.GET, new OnCompletionListener() {
            @Override
            public void onComplete(String response) {
                Log.d("RESPONSE", "Response is: " + response);
                mParser = new Parser();
                lights = mParser.getLights(response);
                mAdapter = new LightAdapter(LightsOverviewActivity.this, lights);
                mListView.setAdapter(mAdapter);
                mListView.setOnItemClickListener(onItemClick);
            }
        });
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Light light = lights.get(i);
            Intent intent = new Intent(LightsOverviewActivity.this, LightDetailActivity.class);
            intent.putExtra("light", light);
            intent.putExtra("place", place);
            startActivity(intent);
        }
    };
}
