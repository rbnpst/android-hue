package rpst.me.androidhue.networking;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rpst.me.androidhue.listeners.OnCompletionListener;
import rpst.me.androidhue.models.Light;

/**
 * Created by Robin on 10-10-2017.
 */

public class HueApiHandler extends ContextWrapper {

    private static final String TAG = "HueApiHandler";

    private String response;

    public HueApiHandler(Context base) {
        super(base);
    }

    public void request(String url, final String parameters, int method, @Nullable final OnCompletionListener listener) {
        Log.d(TAG, "Requesting: " + url);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                HueApiHandler.this.response = response;
                if (listener != null) {
                    listener.onComplete(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return parameters == null ? null : parameters.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while getting the bytes of %s using %s",
                            parameters, "utf-8");
                    uee.printStackTrace();
                    return null;
                }
            }
        };
        queue.add(request);
    }

    public void toggleLight(boolean on, String url) {
        request(url, "{\"on\":" + on + "}", Request.Method.PUT, null);
    }

    public void setHue(int hue, String url) {
        request(url, "{\"hue\":" + hue + "}", Request.Method.PUT, null);
    }

    public void setSaturation(int saturation, String url) {
        request(url, "{\"sat\":" + saturation + "}", Request.Method.PUT, null);
    }

    public void setBrightness(int brightness, String url) {
        request(url, "{\"bri\":" + brightness + "}", Request.Method.PUT, null);
    }

}
