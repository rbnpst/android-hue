package rpst.me.androidhue.models;

import java.io.Serializable;

/**
 * Created by Robin on 3-10-2017.
 */

public class Place implements Serializable {

    private String name;
    private String hostname;
    private String username;

    public Place withName(String name) {
        this.name = name;
        return this;
    }

    public Place withHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public Place withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return hostname + username;
    }

    public String getFullUrlForLights() {
        return hostname + username + "/lights/";
    }
}
