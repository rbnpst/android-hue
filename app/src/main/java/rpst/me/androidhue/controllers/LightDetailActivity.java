package rpst.me.androidhue.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import rpst.me.androidhue.Constants;
import rpst.me.androidhue.R;
import rpst.me.androidhue.models.Light;
import rpst.me.androidhue.models.Place;
import rpst.me.androidhue.networking.HueApiHandler;

public class LightDetailActivity extends AppCompatActivity {

    private Place place;
    private Light light;

    private HueApiHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_detail);

        place = (Place) getIntent().getSerializableExtra("place");
        light = (Light) getIntent().getSerializableExtra("light");

        handler = new HueApiHandler(this);

        Switch switchLight = findViewById(R.id.switch_light);
        SeekBar seekBarHue = findViewById(R.id.seekbar_hue);
        SeekBar seekBarSaturation = findViewById(R.id.seekbar_saturation);
        SeekBar seekBarBrightness = findViewById(R.id.seekbar_brightness);

        // Set current state of elements
        switchLight.setChecked(light.isOn());
        seekBarHue.setProgress(light.getHue());
        seekBarSaturation.setProgress(light.getSaturation());
        seekBarBrightness.setProgress(light.getBrightness());

        // Listeners
        switchLight.setOnCheckedChangeListener(onSwitchChange);
        seekBarHue.setOnSeekBarChangeListener(onSeekbarHueChanged);
        seekBarSaturation.setOnSeekBarChangeListener(onSeekbarSaturationChanged);
        seekBarBrightness.setOnSeekBarChangeListener(onSeekbarBrightnessChanged);
    }

    CompoundButton.OnCheckedChangeListener onSwitchChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Log.d("Check changed", "onCheckedChanged");
            Log.d(getLocalClassName(), place.getUrl() + Constants.STATE_URL);
            handler.toggleLight(b, String.format(place.getUrl() + Constants.STATE_URL, light.getId()));
        }
    };

    SeekBar.OnSeekBarChangeListener onSeekbarHueChanged = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            handler.setHue(i, String.format(place.getUrl() + Constants.STATE_URL, light.getId()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener onSeekbarSaturationChanged = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            handler.setSaturation(i, String.format(place.getUrl() + Constants.STATE_URL, light.getId()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener onSeekbarBrightnessChanged = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            handler.setBrightness(i, String.format(place.getUrl() + Constants.STATE_URL, light.getId()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
