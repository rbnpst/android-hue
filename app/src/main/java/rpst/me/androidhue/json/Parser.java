package rpst.me.androidhue.json;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rpst.me.androidhue.models.Light;

/**
 * Created by Robin on 11-10-2017.
 */

public class Parser {

    public List<Light> getLights(String response) {
        List<Light> lights = new ArrayList<>();
        JSONObject object;
        try {
            object = new JSONObject(response);
            for (Iterator<String> iterator = object.keys(); iterator.hasNext();) {
                String id = iterator.next();
                Log.d("LIGHT", "Light name: " + object.getJSONObject(id).getString("name"));
                lights.add(new Light().withId(id)
                        .withName(object.getJSONObject(id).getString("name"))
                        .withSaturation(object.getJSONObject(id).getJSONObject("state").getInt("sat"))
                        .withBrightness(object.getJSONObject(id).getJSONObject("state").getInt("bri"))
                        .withHue(object.getJSONObject(id).getJSONObject("state").getInt("hue"))
                        .isOn(object.getJSONObject(id).getJSONObject("state").getBoolean("on")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lights;
    }

}
