package rpst.me.androidhue.models;

import java.io.Serializable;

/**
 * Created by Robin on 3-10-2017.
 */

public class Light implements Serializable {

    private String id;
    private String name;
    private boolean isOn;
    private int brightness;
    private int hue;
    private int saturation;

    public Light withId(String id) {
        this.id = id;
        return this;
    }

    public Light withName(String name) {
        this.name = name;
        return this;
    }

    public Light isOn(boolean on) {
        this.isOn = on;
        return this;
    }

    public Light withBrightness(int brightness) {
        this.brightness = brightness;
        return this;
    }

    public Light withHue(int hue) {
        this.hue = hue;
        return this;
    }

    public Light withSaturation(int saturation) {
        this.saturation = saturation;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getHue() {
        return hue;
    }

    public int getSaturation() {
        return saturation;
    }

}
